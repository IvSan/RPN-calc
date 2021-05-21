package xyz.hardliner.calc.service;

import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

    public final Stack<Item> stack;
    public final Stack<Item> history;

    public Calculator() {
        stack = new Stack<>();
        history = new Stack<>();
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
