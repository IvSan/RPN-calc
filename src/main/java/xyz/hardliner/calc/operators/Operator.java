package xyz.hardliner.calc.operators;

import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;

import java.util.Stack;
import java.util.function.Function;

public interface Operator extends Item {

    Function<Stack<Operand>, ApplicableCheck> applicableChecker();

}
