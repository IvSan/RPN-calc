package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.Function;

public class Addition implements Operator {

    @Override
    public String print() {
        return "+";
    }

    @Override
    public int inputsNumber() {
        return 2;
    }

    @Override
    public Function<List<Operand>, Operand> function() {
        return operands -> {
            final var first = operands.iterator().next();
            final var second = operands.iterator().next();
            if (first instanceof NumericOperand && second instanceof NumericOperand) {
                return new NumericOperand(((NumericOperand) first).number.doubleValue() + ((NumericOperand) second).number.doubleValue());
            } else {
                throw new NonApplicableOperator(
                    String.format("operator %s: cannot apply to '%s' and '%s'", print(), first.print(), second.print())
                );
            }
        };
    }
}
