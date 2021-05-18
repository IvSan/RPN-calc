package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.exception.InsufficientParametersException;
import xyz.hardliner.calc.operands.Operand;

import java.util.Stack;

public interface Operator extends Item {

    int inputsNumber();

    default boolean validateStack(Stack<Item> originalStack) {
        var inputsCounter = 0;
        Stack<?> stack = (Stack<?>) originalStack.clone();

        while (!stack.empty()) {
            if (stack.pop() instanceof Operand) {
                inputsCounter++;
                if (inputsCounter == inputsNumber()) {
                    return true;
                }
            }
        }

        throw new InsufficientParametersException(
            String.format("operator %s (position: %d): insufficient parameters", print(), originalStack.size())
        );
    }

}
