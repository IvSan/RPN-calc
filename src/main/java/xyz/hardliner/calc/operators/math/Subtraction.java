package xyz.hardliner.calc.operators.math;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.SUBTRACTION;

@EqualsAndHashCode
public class Subtraction implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return SUBTRACTION.alias;
    }

    @Override
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction() {
        return BigDecimal::subtract;
    }
}
