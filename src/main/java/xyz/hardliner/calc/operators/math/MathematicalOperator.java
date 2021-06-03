package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.Operator;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.ItemResolvingRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import static java.lang.String.format;
import static xyz.hardliner.calc.service.ApplicableCheck.failedCheck;
import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.StackUtils.cloneStack;

public interface MathematicalOperator extends Operator {

    int arity();

    Function<List<Operand>, Operand> effect();

    default Function<Stack<Operand>, ApplicableCheck> applicableChecker() {
        return availableOperandsNumberChecker();
    }

    default ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule((item, stack) -> {
            final var mathOperator = (MathematicalOperator) item;

            final var check = applicableChecker().apply(stack);
            if (check.isFail()) {
                throw new NonApplicableOperation(check.getFailMessage());
            }

            final var operands = new ArrayList<Operand>();
            for (int i = 0; i < mathOperator.arity(); i++) {
                final var operand = stack.pop();
                operands.add(operand);
            }

            stack.push(mathOperator.effect().apply(operands));
        });
    }

    default Function<Stack<Operand>, ApplicableCheck> availableOperandsNumberChecker() {
        return actualStack -> {
            var inputsCounter = 0;
            final var stack = cloneStack(actualStack);

            while (!stack.empty()) {
                stack.pop();
                inputsCounter++;
                if (inputsCounter >= arity()) {
                    return successfulCheck();
                }
            }

            return failedCheck(
                format("operator '%s' (position: %%pos): insufficient parameters", print())
            );
        };
    }

}
