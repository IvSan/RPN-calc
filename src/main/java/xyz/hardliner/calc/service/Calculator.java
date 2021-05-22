package xyz.hardliner.calc.service;

import xyz.hardliner.calc.exception.CalculatorException;

import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

    private final InputProvider in;
    private final Parser parser;

    private final Stack<Item> stack;
    private final Stack<Item> history;

    public Calculator(InputProvider in, Parser parser) {
        this.in = in;
        this.parser = parser;
        stack = new Stack<>();
        history = new Stack<>();
    }

    public void run() {
        var line = in.nextLine();
        while (!"exit".equals(line)) {
            try {
                for (Item item : parser.parseLine(line)) {
                    process(item);
                }
            } catch (CalculatorException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("stack: " + print());
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
}
