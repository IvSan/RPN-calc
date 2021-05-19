package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Operator extends Item {

    int arity();

    Function<List<Operand>, Operand> effect();

    default Function<Double, Double> numericUnaryFunction() {
        throw new NonApplicableOperator(
            String.format("numeric unary function for operator '%s' hasn't been implemented", print())
        );
    }

    default Function<List<Operand>, Operand> numericUnaryFunctionWrapper() {
        return operands -> {
            final var num = operands.iterator().next();
            if (num instanceof NumericOperand) {
                return new NumericOperand(
                    numericUnaryFunction().apply(((NumericOperand) num).number.doubleValue())
                );
            } else {
                throw new NonApplicableOperator(
                    String.format("operator '%s': cannot apply to '%s'", print(), num.print())
                );
            }
        };
    }

    default BiFunction<Double, Double, Double> numericBinaryFunction() {
        throw new NonApplicableOperator(
            String.format("numeric binary function for operator '%s' hasn't been implemented", print())
        );
    }

    default Function<List<Operand>, Operand> numericBinaryFunctionWrapper() {
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

}
