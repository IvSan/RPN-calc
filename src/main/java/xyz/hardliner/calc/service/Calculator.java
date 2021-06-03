package xyz.hardliner.calc.service;

import xyz.hardliner.calc.exception.CalculatorException;
import xyz.hardliner.calc.exception.CalculatorExceptionWithLastSuccessfulState;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.utils.Counter;
import xyz.hardliner.calc.utils.Operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public String calculate(String line) {
        if ("exit".equals(line)) {
            System.exit(0);
        }
        add(parser.parseLine(line));
        return print();
    }

    /**
     * Make {@link Calculator} consume next input {@link Item}.
     *
     * @param item - input.
     * @return - itself with updated state.
     */
    public Calculator add(Item item) {
        items.add(item);
        return this;
    }

    /**
     * Make {@link Calculator} consume collection of input {@link Item}s.
     *
     * @param items - input.
     * @return - itself with updated state.
     */
    public Calculator add(Collection<Item> items) {
        this.items.addAll(items);
        return this;
    }

    public Stack<Operand> getResult() {
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
            throw new CalculatorExceptionWithLastSuccessfulState(populatePositionPlaceholder(ex.getMessage(), positionPointer.get() + 1), result);
        }

        return result;
    }

    /**
     * @return - String representation of actual operational stack.
     */
    public String print() {
        try {
            return "stack: " + getResult().stream().map(Item::print).collect(Collectors.joining(" "));
        } catch (CalculatorExceptionWithLastSuccessfulState ex) {
            final var calcOutput = ex.lastSuccessfulState.stream().map(Item::print).collect(Collectors.joining(" "));
            return ex.getMessage() + "\nstack: " + calcOutput;
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
