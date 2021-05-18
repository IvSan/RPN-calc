package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.operands.Operand;

import java.util.List;
import java.util.function.Function;

public interface Operator extends Item {

    int inputsNumber();

    Function<List<Operand>, Operand> function();

}
