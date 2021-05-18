package xyz.hardliner.calc.operators;

public class Addition implements Operator {

    @Override
    public String print() {
        return "+";
    }

    @Override
    public int inputsNumber() {
        return 2;
    }
}
