package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;

import java.util.Stack;
import java.util.function.Function;

import static xyz.hardliner.calc.service.ApplicableCheck.failedCheck;
import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.StackUtils.cloneStack;

public class SquareRoot implements UnaryMathematicalOperator {

    @Override
    public String print() {
        return "sqrt";
    }

    @Override
    public Function<Stack<Item>, ApplicableCheck> applicableChecker() {
        return stack -> {
            final var operandsNumberCheck = availableOperandsNumberChecker().apply(stack);
            if (operandsNumberCheck.isFail()) {
                return operandsNumberCheck;
            }
            return positiveNumberChecker().apply(stack);
        };
    }

    @Override
    public Function<Double, Double> numericUnaryFunction() {
        return Math::sqrt;
    }

    private Function<Stack<Item>, ApplicableCheck> positiveNumberChecker() {
        return actualStack -> {
            final var topItem = cloneStack(actualStack).pop();
            if ((topItem instanceof NumericOperand) && ((NumericOperand) topItem).number.doubleValue() >= 0) {
                return successfulCheck();
            }
            return failedCheck(
                String.format("operator '%s' (position: %d): cannot apply to '%s'", print(), actualStack.size() + 1, topItem.print())
            );
        };
    }
}
