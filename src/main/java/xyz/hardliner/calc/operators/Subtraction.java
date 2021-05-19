package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Subtraction implements Operator {

    @Override
    public String print() {
        return "-";
    }

    @Override
    public int inputsNumber() {
        return 2;
    }

    @Override
    public Function<List<Operand>, Operand> effect() {
        return numericBiFunctionWrapper();
    }

    @Override
    public BiFunction<Double, Double, Double> numericBiFunction() {
        return (first, second) -> first - second;
    }
}
