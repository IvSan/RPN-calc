package xyz.hardliner.calc.operators.math;

import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.DIVISION;

public class Division implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return DIVISION.alias;
    }

    @Override
    public BiFunction<Double, Double, Double> numericBinaryFunction() {
        return (first, second) -> first / second;
    }
}
