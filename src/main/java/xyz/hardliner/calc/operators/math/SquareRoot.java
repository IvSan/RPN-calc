package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.function.Function;

import static java.lang.String.format;
import static java.math.MathContext.DECIMAL64;
import static xyz.hardliner.calc.service.ApplicableCheck.failedCheck;
import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.Operators.SQUARE_ROOT;
import static xyz.hardliner.calc.utils.StackUtils.cloneStack;

public class SquareRoot implements UnaryMathematicalOperator {

    @Override
    public String print() {
        return SQUARE_ROOT.alias;
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
    public Function<BigDecimal, BigDecimal> numericUnaryFunction() {
        return num -> num.sqrt(DECIMAL64);
    }

    private Function<Stack<Item>, ApplicableCheck> positiveNumberChecker() {
        return actualStack -> {
            final var topItem = cloneStack(actualStack).pop();
            if ((topItem instanceof NumericOperand) && ((NumericOperand) topItem).number.doubleValue() >= 0) {
                return successfulCheck();
            }
            return failedCheck(
                format("operator '%s' (position: %%pos): cannot apply to '%s'", print(), topItem.print())
            );
        };
    }
}
