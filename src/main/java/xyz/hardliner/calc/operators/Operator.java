package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Operator extends Item {

    int inputsNumber();

    Function<List<Operand>, Operand> effect();

    default BiFunction<Double, Double, Double> numericBiFunction() {
        throw new NonApplicableOperator(
            String.format("numeric binary function for operator '%s' hasn't been implemented", print())
        );
    }

    default Function<List<Operand>, Operand> numericBiFunctionWrapper() {
        return operands -> {
            final var iterator = operands.iterator();
            final var first = iterator.next();
            final var second = iterator.next();
            if (first instanceof NumericOperand && second instanceof NumericOperand) {
                return new NumericOperand(
                    numericBiFunction().apply(((NumericOperand) second).number.doubleValue(), ((NumericOperand) first).number.doubleValue())
                );
            } else {
                throw new NonApplicableOperator(
                    String.format("operator %s: cannot apply to '%s' and '%s'", print(), first.print(), second.print())
                );
            }
        };
    }

}
