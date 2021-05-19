package xyz.hardliner.calc.operands;

import java.text.DecimalFormat;

public class NumericOperand implements Operand {

    public static final DecimalFormat NUMBERS_FORMAT = new DecimalFormat("0.#");

    public Number number;

    public NumericOperand(Number number) {
        this.number = number;
    }

    @Override
    public String print() {
        return NUMBERS_FORMAT.format(number.doubleValue());
    }
}
