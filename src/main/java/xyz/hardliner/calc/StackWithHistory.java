package xyz.hardliner.calc;

import xyz.hardliner.calc.exception.InsufficientParametersException;
import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.Operator;

import java.util.ArrayList;
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
        history.push(item);

        if (item instanceof Operand) stack.push(item);
        if (item instanceof Operator) processOperator((Operator) item);

        return this;
    }

    private void processOperator(Operator operator) {
        checkAvailableOperandsNumber(operator);
        apply(operator);
    }

    private void checkAvailableOperandsNumber(Operator operator) {
        var inputsCounter = 0;
        Stack<?> stack = (Stack<?>) this.stack.clone();

        while (!stack.empty()) {
            if (stack.pop() instanceof Operand) {
                inputsCounter++;
                if (inputsCounter >= operator.arity()) {
                    return;
                }
            }
        }

        throw new InsufficientParametersException(
            String.format("operator '%s' (position: %d): insufficient parameters", print(), this.stack.size() + 1)
        );
    }

    private void apply(Operator operator) {
        final var operands = new ArrayList<Operand>();
        for (int i = 0; i < operator.arity(); i++) {
            final var operand = stack.pop();
            if (operand instanceof Operand) {
                operands.add((Operand) operand);
            } else {
                throw new NonApplicableOperator(String.format("invalid stack state: cannot apply operator '%s' on '%s'", operator.print(), print()));
            }
        }
        stack.push(operator.effect().apply(operands));
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }
}
