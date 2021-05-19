package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.exception.NonApplicableOperation;

import java.util.function.Function;

import static xyz.hardliner.calc.operands.NumericOperand.NUMBERS_FORMAT;

public class SquareRoot implements UnaryMathematicalOperator {

    @Override
    public String print() {
        return "sqrt";
    }

    @Override
    public Function<Double, Double> numericUnaryFunction() {
        return num -> {
            if (num < 0) throw new NonApplicableOperation(
                String.format("Cannot apply operator '%s' to negative number %s", print(), NUMBERS_FORMAT.format(num))
            );
            return Math.sqrt(num);
        };
    }
}
