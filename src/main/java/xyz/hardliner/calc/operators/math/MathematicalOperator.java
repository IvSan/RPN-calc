package xyz.hardliner.calc.operators.math;

import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.Operator;

import java.util.List;
import java.util.function.Function;

public interface MathematicalOperator extends Operator {

    int arity();

    Function<List<Operand>, Operand> effect();

}
