package xyz.hardliner.calc.operators.math;

import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.SUBTRACTION;

public class Subtraction implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return SUBTRACTION.alias;
    }

    @Override
    public BiFunction<Double, Double, Double> numericBinaryFunction() {
        return (first, second) -> first - second;
    }
}
