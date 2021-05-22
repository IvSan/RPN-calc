package xyz.hardliner.calc.service;

import xyz.hardliner.calc.exception.CalculatorException;
import xyz.hardliner.calc.service.io.InputProvider;
import xyz.hardliner.calc.service.io.OutputProvider;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Core logic entity with it's state, which contain actual operational stack and historical stack of instructions.
 */
public class Calculator implements Runnable {

    private final InputProvider in;
    private final OutputProvider out;
    private final Parser parser;

    private final Stack<Item> stack;
    private final Stack<Item> history;

    public Calculator(InputProvider in, OutputProvider out, Parser parser) {
        this.in = in;
        this.out = out;
        this.parser = parser;
        stack = new Stack<>();
        history = new Stack<>();
    }

    public void run() {
        var line = in.nextLine();
        while (!"exit".equals(line)) {
            var pointerPosition = 1;
            try {
                for (Item item : parser.parseLine(line)) {
                    process(item);
                    pointerPosition += item.print().length() + 1;
                }
            } catch (CalculatorException ex) {
                final var errorMessage = ex.getMessage();
                out.outputLine(populatePositionPlaceholder(errorMessage, pointerPosition));
            }
            out.outputLine("stack: " + print());
            line = in.nextLine();
        }
    }

    /**
     * Make {@link Calculator} process next input {@link Item}.
     *
     * @param item - input.
     * @return - itself with new state.
     */
    public Calculator process(Item item) {
        item.resolvingRule().apply(item, stack, history);
        return this;
    }

    /**
     * @return - String representation of actual operational stack.
     */
    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    /**
     * @return - String representation of historical stack of instructions.
     */
    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    private String populatePositionPlaceholder(String errorMessage, int pointerPosition) {
        return errorMessage.replaceFirst("%pos", Integer.toString(pointerPosition));
    }
}
