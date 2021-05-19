package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.ItemResolvingRule;
import xyz.hardliner.calc.exception.InsufficientParametersException;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public interface MathematicalOperator extends Operator {

    int arity();

    Function<List<Operand>, Operand> effect();

    static ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof MathematicalOperator,
            (item, state) -> {
                final var stack = state.getLeft();
                final var history = state.getRight();
                final var mathOperator = (MathematicalOperator) item;

                checkAvailableOperandsNumber(mathOperator, stack);

                final var operands = new ArrayList<Operand>();
                for (int i = 0; i < mathOperator.arity(); i++) {
                    final var operand = stack.pop();
                    if (operand instanceof Operand) {
                        operands.add((Operand) operand);
                    } else {
                        throw new NonApplicableOperation(String.format("invalid stack state: cannot apply operator '%s' on '%s'", item.print(), operand.print()));
                    }
                }

                stack.push(mathOperator.effect().apply(operands));
                history.push(mathOperator);
            });
    }

    private static void checkAvailableOperandsNumber(MathematicalOperator mathematicalOperator, Stack<Item> actualStack) {
        var inputsCounter = 0;
        Stack<?> stack = (Stack<?>) actualStack.clone();

        while (!stack.empty()) {
            if (stack.pop() instanceof Operand) {
                inputsCounter++;
                if (inputsCounter >= mathematicalOperator.arity()) {
                    return;
                }
            }
        }

        throw new InsufficientParametersException(
            String.format("operator '%s' (position: %d): insufficient parameters", mathematicalOperator.print(), actualStack.size() + 1)
        );
    }

}
