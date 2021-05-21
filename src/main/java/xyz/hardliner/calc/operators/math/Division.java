package xyz.hardliner.calc.operators.math;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.DIVISION;

public class Division implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return DIVISION.alias;
    }

    @Override
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction() {
        return BigDecimal::divide;
    }
}
