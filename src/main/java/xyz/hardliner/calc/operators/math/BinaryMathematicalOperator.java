package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
                    numericBinaryFunction().apply(((NumericOperand) second).number.doubleValue(), ((NumericOperand) first).number.doubleValue())
                );
            } else {
                throw new NonApplicableOperator(
                    String.format("operator '%s': cannot apply to '%s' and '%s'", print(), first.print(), second.print())
                );
            }
        };
    }

    BiFunction<Double, Double, Double> numericBinaryFunction();

}
