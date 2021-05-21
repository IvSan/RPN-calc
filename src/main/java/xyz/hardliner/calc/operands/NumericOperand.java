package xyz.hardliner.calc.operands;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumericOperand implements Operand {

    public static final DecimalFormat NUMBERS_FORMAT = new DecimalFormat("0.##########");

    public BigDecimal number;

    public NumericOperand(BigDecimal number) {
        this.number = number;
    }

    public NumericOperand(String number) {
        this.number = new BigDecimal(number);
    }

    @Override
    public String print() {
        return NUMBERS_FORMAT.format(number);
    }
}
