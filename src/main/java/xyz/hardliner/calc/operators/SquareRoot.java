package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.exception.NonApplicableOperator;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.Function;

import static xyz.hardliner.calc.operands.NumericOperand.NUMBERS_FORMAT;

public class SquareRoot implements Operator {

    @Override
    public String print() {
        return "sqrt";
    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public Function<List<Operand>, Operand> effect() {
        return numericUnaryFunctionWrapper();
    }

    @Override
    public Function<Double, Double> numericUnaryFunction() {
        return num -> {
            if (num < 0) throw new NonApplicableOperator(
                String.format("Cannot apply operator '%s' to negative number %s", print(), NUMBERS_FORMAT.format(num))
            );
            return Math.sqrt(num);
        };
    }
}
