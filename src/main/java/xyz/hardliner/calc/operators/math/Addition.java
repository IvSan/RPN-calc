package xyz.hardliner.calc.operators.math;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.ADDITION;

public class Addition implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return ADDITION.alias;
    }

    @Override
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction() {
        return BigDecimal::add;
    }
}
