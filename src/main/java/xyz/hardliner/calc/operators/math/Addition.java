package xyz.hardliner.calc.operators.math;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.ADDITION;

@EqualsAndHashCode
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
