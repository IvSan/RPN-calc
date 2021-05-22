package xyz.hardliner.calc.service;

import xyz.hardliner.calc.exception.CalculatorException;
import xyz.hardliner.calc.service.io.InputProvider;
import xyz.hardliner.calc.service.io.OutputProvider;

import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

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

    public Calculator process(Item item) {
        item.resolvingRule().apply(item, stack, history);
        return this;
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    private String populatePositionPlaceholder(String errorMessage, int pointerPosition) {
        return errorMessage.replaceFirst("%pos", Integer.toString(pointerPosition));
    }
}
