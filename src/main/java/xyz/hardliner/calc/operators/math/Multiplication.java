package xyz.hardliner.calc.operators.math;

import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.OperatorUtils.MULTIPLICATION;

public class Multiplication implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return MULTIPLICATION.alias;
    }

    @Override
    public BiFunction<Double, Double, Double> numericBinaryFunction() {
        return (first, second) -> first * second;
    }
}
