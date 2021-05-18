package xyz.hardliner.calc.operands;

import java.text.DecimalFormat;

public class NumericOperand implements Operand {

    private static final DecimalFormat FORMAT = new DecimalFormat("0.#");

    public Number number;

    public NumericOperand(Number number) {
        this.number = number;
    }

    @Override
    public String print() {
        return FORMAT.format(number.doubleValue());
    }
}
