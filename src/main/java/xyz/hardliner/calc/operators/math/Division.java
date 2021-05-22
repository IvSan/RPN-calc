package xyz.hardliner.calc.operators.math;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static java.math.MathContext.DECIMAL64;
import static xyz.hardliner.calc.utils.Operators.DIVISION;

@EqualsAndHashCode
public class Division implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return DIVISION.alias;
    }

    @Override
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction() {
        return (dividend, divisor) -> dividend.divide(divisor, DECIMAL64);
    }
}
