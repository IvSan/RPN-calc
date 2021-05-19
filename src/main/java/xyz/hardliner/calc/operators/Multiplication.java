package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Multiplication implements Operator {

    @Override
    public String print() {
        return "*";
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public Function<List<Operand>, Operand> effect() {
        return numericBinaryFunctionWrapper();
    }

    @Override
    public BiFunction<Double, Double, Double> numericBinaryFunction() {
        return (first, second) -> first * second;
    }
}
