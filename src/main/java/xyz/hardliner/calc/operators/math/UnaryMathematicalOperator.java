package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;

public interface UnaryMathematicalOperator extends MathematicalOperator {

    default int arity() {
        return 1;
    }

    default Function<List<Operand>, Operand> effect() {
        return operands -> {
            final var num = operands.iterator().next();
            if (num instanceof NumericOperand) {
                return new NumericOperand(
                    numericUnaryFunction().apply(((NumericOperand) num).number)
                );
            } else {
                throw new NonApplicableOperation(
                    format("operator '%s': cannot apply to '%s'", print(), num.print())
                );
            }
        };
    }

    Function<BigDecimal, BigDecimal> numericUnaryFunction();

}
