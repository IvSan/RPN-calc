package xyz.hardliner.calc;

import xyz.hardliner.calc.operators.Operator;

import java.util.Stack;
import java.util.stream.Collectors;

public class StackWithHistory {

    public final Stack<Item> stack;
    public final Stack<Item> history;

    public StackWithHistory() {
        stack = new Stack<>();
        history = new Stack<>();
    }

    public StackWithHistory push(Item item) {
        stack.push(item);
        history.push(item);

        processStack();

        return this;
    }

    private void processStack() {
        final var item = stack.peek();

        if (!(item instanceof Operator)) {
            return;
        }

        ((Operator) item).validateStack(stack);
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }
}
