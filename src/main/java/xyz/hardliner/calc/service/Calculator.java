package xyz.hardliner.calc.service;

import xyz.hardliner.calc.exception.CalculatorException;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.service.dto.CalculationResult;
import xyz.hardliner.calc.service.dto.CalculationStatus;
import xyz.hardliner.calc.utils.Counter;
import xyz.hardliner.calc.utils.Operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import static xyz.hardliner.calc.service.dto.CalculationStatus.SUCCESS;

/**
 * Core logic entity with it's state, which contain actual operational stack and historical stack of instructions.
 */
public class Calculator {

    private final Parser parser;

    private final List<Item> items;

    public Calculator(Parser parser) {
        this.parser = parser;
        items = new ArrayList<>();
    }

    /**
     * Make {@link Calculator} consume next input line.
     *
     * @param line - input.
     * @return - itself with updated stack.
     */
    public Calculator add(String line) {
        if ("exit".equals(line)) {
            System.exit(0);
        }
        add(parser.parseLine(line));
        return this;
    }

    /**
     * Make {@link Calculator} consume next input {@link Item}.
     *
     * @param item - input.
     * @return - itself with updated stack.
     */
    public Calculator add(Item item) {
        items.add(item);
        return this;
    }

    /**
     * Make {@link Calculator} consume collection of input {@link Item}s.
     *
     * @param items - input.
     * @return - itself with updated stack.
     */
    public Calculator add(Collection<Item> items) {
        this.items.addAll(items);
        return this;
    }

    /**
     * @return - Result of stack calculation.
     */
    public CalculationResult getResult() {
        var itemsEffectiveCopy = List.copyOf(items);
        for (Function<List<Item>, List<Item>> special : Operators.specialOperatorsResolvingRules()) {
            itemsEffectiveCopy = special.apply(itemsEffectiveCopy);
        }

        final var result = new Stack<Operand>();
        final var positionPointer = new Counter();
        try {
            itemsEffectiveCopy.forEach(item -> {
                item.resolvingRule().apply(item, result);
                positionPointer.increment(item.print().length() + 1);
            });
        } catch (CalculatorException ex) {
            return new CalculationResult(CalculationStatus.ERROR, result, populatePositionPlaceholder(ex.getMessage(), positionPointer.get() + 1));
        }

        return new CalculationResult(result);
    }

    /**
     * @return - String representation of actual operational stack.
     */
    public String print() {
        final var calcResult = getResult();
        final var printLine = "stack: " + calcResult.result.stream().map(Item::print).collect(Collectors.joining(" "));

        if (SUCCESS.equals(calcResult.status)) {
            return printLine;
        } else {
            return (calcResult.errorMessage.map(s -> s + "\n").orElse("")) + printLine;
        }
    }

    /**
     * @return - String representation of historical stack of instructions.
     */
    public String printHistory() {
        return items.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    private String populatePositionPlaceholder(String errorMessage, int pointerPosition) {
        return errorMessage.replaceFirst("%pos", Integer.toString(pointerPosition));
    }
}
