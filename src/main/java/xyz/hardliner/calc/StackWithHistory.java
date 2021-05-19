package xyz.hardliner.calc;

import xyz.hardliner.calc.exception.InsufficientParametersException;
import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.MathematicalOperator;
import xyz.hardliner.calc.operators.Operator;
import xyz.hardliner.calc.operators.special.SpecialOperator;

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
        if (operator instanceof SpecialOperator) {
//            stack, history = BiFunction
        }

        if (operator instanceof MathematicalOperator) {
            final var mathOperator = (MathematicalOperator) operator;

            checkAvailableOperandsNumber(mathOperator);

            final var operands = new ArrayList<Operand>();
            for (int i = 0; i < mathOperator.arity(); i++) {
                final var operand = stack.pop();
                if (operand instanceof Operand) {
                    operands.add((Operand) operand);
                } else {
                    throw new NonApplicableOperator(String.format("invalid stack state: cannot apply operator '%s' on '%s'", operator.print(), print()));
                }
            }
            stack.push(mathOperator.effect().apply(operands));
        }
    }

    private void checkAvailableOperandsNumber(MathematicalOperator mathematicalOperator) {
        var inputsCounter = 0;
        Stack<?> stack = (Stack<?>) this.stack.clone();

        while (!stack.empty()) {
            if (stack.pop() instanceof Operand) {
                inputsCounter++;
                if (inputsCounter >= mathematicalOperator.arity()) {
                    return;
                }
            }
        }

        throw new InsufficientParametersException(
            String.format("operator '%s' (position: %d): insufficient parameters", print(), this.stack.size() + 1)
        );
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }
}
