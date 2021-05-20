package xyz.hardliner.calc;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.operators.special.Undo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackWithHistoryTest {

    @Test
    public void operand_undo() {
        final var stack = new StackWithHistory();

        stack
            .process(new NumericOperand(81))
            .process(new NumericOperand(15.5))
            .process(new NumericOperand(10));

        assertEquals("81 15.5 10", stack.print());
        assertEquals("81 15.5 10", stack.printHistory());

        stack.process(new Undo());

        assertEquals("81 15.5", stack.print());
        assertEquals("81 15.5", stack.printHistory());

        stack.process(new Undo());

        assertEquals("81", stack.print());
        assertEquals("81", stack.printHistory());

        stack.process(new Undo());

        assertEquals("", stack.print());
        assertEquals("", stack.printHistory());
    }

    @Test
    public void unary_operator_undo() {
        final var stack = new StackWithHistory();

        stack
            .process(new NumericOperand(81))
            .process(new SquareRoot())
            .process(new SquareRoot());

        assertEquals("3", stack.print());
        assertEquals("81 sqrt sqrt", stack.printHistory());

        stack.process(new Undo());

        assertEquals("9", stack.print());
        assertEquals("81 sqrt", stack.printHistory());

        stack.process(new Undo());

        assertEquals("81", stack.print());
        assertEquals("81", stack.printHistory());

        stack.process(new Undo());

        assertEquals("", stack.print());
        assertEquals("", stack.printHistory());
    }

    @Test
    public void binary_operator_undo() {
        final var stack = new StackWithHistory();

        stack
            .process(new NumericOperand(4))
            .process(new NumericOperand(3))
            .process(new NumericOperand(2))
            .process(new Subtraction())
            .process(new Addition());

        assertEquals("5", stack.print());
        assertEquals("4 3 2 - +", stack.printHistory());

        stack.process(new Undo());

        assertEquals("4 1", stack.print());
        assertEquals("4 3 2 -", stack.printHistory());

        stack.process(new Undo());

        assertEquals("4 3 2", stack.print());
        assertEquals("4 3 2", stack.printHistory());

        stack.process(new Undo());

        assertEquals("4 3", stack.print());
        assertEquals("4 3", stack.printHistory());
    }
}