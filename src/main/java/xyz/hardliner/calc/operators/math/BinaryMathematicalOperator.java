package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.String.format;

public interface BinaryMathematicalOperator extends MathematicalOperator {

    default int arity() {
        return 2;
    }

    default Function<List<Operand>, Operand> effect() {
        return operands -> {
            final var iterator = operands.iterator();
            final var first = iterator.next();
            final var second = iterator.next();
            if (first instanceof NumericOperand && second instanceof NumericOperand) {
                return new NumericOperand(
                    numericBinaryFunction().apply(((NumericOperand) second).number, ((NumericOperand) first).number)
                );
            } else {
                throw new NonApplicableOperation(
                    format("operator '%s' (position: %%pos): cannot apply to '%s' and '%s'", print(), first.print(), second.print())
                );
            }
        };
    }

    BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction();

}
