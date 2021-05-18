package xyz.hardliner.calc.operands;

public class NumericOperand implements Operand {

    public Number number;

    public NumericOperand(Number number) {
        this.number = number;
    }

    @Override
    public String print() {
        return number.toString();
    }
}
