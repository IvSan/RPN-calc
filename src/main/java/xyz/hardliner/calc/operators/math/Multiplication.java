package xyz.hardliner.calc.operators.math;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static xyz.hardliner.calc.utils.Operators.MULTIPLICATION;

public class Multiplication implements BinaryMathematicalOperator {

    @Override
    public String print() {
        return MULTIPLICATION.alias;
    }

    @Override
    public BiFunction<BigDecimal, BigDecimal, BigDecimal> numericBinaryFunction() {
        return BigDecimal::multiply;
    }
}
