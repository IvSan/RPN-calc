package xyz.hardliner.calc.operators.math;

import java.util.function.BiFunction;

public class Division implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return "/";
    }

    @Override
    public BiFunction<Double, Double, Double> numericBinaryFunction() {
        return (first, second) -> first / second;
    }
}
