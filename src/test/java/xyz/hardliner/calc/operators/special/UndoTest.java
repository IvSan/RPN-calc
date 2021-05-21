package xyz.hardliner.calc.operators.special;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.service.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoTest {

    @Test
    public void plain_operand_undo() {
        final var calc = new Calculator();

        calc
            .process(new NumericOperand("81"))
            .process(new NumericOperand("15.5"))
            .process(new NumericOperand("10"));

        assertEquals("81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.process(new Undo());

        assertEquals("81 15.5", calc.print());
        assertEquals("81 15.5", calc.printHistory());

        calc.process(new Undo());

        assertEquals("81", calc.print());
        assertEquals("81", calc.printHistory());

        calc.process(new Undo());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());
    }

    @Test
    public void unary_operator_undo() {
        final var calc = new Calculator();

        calc
            .process(new NumericOperand("81"))
            .process(new SquareRoot())
            .process(new SquareRoot());

        assertEquals("3", calc.print());
        assertEquals("81 sqrt sqrt", calc.printHistory());

        calc.process(new Undo());

        assertEquals("9", calc.print());
        assertEquals("81 sqrt", calc.printHistory());

        calc.process(new Undo());

        assertEquals("81", calc.print());
        assertEquals("81", calc.printHistory());

        calc.process(new Undo());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());
    }

    @Test
    public void binary_operator_undo() {
        final var calc = new Calculator();

        calc
            .process(new NumericOperand("4"))
            .process(new NumericOperand("3"))
            .process(new NumericOperand("2"))
            .process(new Subtraction())
            .process(new Addition());

        assertEquals("5", calc.print());
        assertEquals("4 3 2 - +", calc.printHistory());

        calc.process(new Undo());

        assertEquals("4 1", calc.print());
        assertEquals("4 3 2 -", calc.printHistory());

        calc.process(new Undo());

        assertEquals("4 3 2", calc.print());
        assertEquals("4 3 2", calc.printHistory());

        calc.process(new Undo());

        assertEquals("4 3", calc.print());
        assertEquals("4 3", calc.printHistory());
    }

    @Test
    public void mixed_undo() {
        final var calc = new Calculator();

        calc
            .process(new NumericOperand("10"))
            .process(new NumericOperand("9"))
            .process(new SquareRoot())
            .process(new Addition())
            .process(new NumericOperand("4"))
            .process(new SquareRoot())
            .process(new Multiplication());

        assertEquals("26", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt *", calc.printHistory());

        calc.process(new Undo());

        assertEquals("13 2", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt", calc.printHistory());

        calc.process(new Undo());

        assertEquals("13 4", calc.print());
        assertEquals("10 9 sqrt + 4", calc.printHistory());

        calc.process(new Undo());

        assertEquals("13", calc.print());
        assertEquals("10 9 sqrt +", calc.printHistory());

        calc.process(new Undo());

        assertEquals("10 3", calc.print());
        assertEquals("10 9 sqrt", calc.printHistory());

        calc.process(new Undo());

        assertEquals("10 9", calc.print());
        assertEquals("10 9", calc.printHistory());

        calc.process(new Undo());

        assertEquals("10", calc.print());
        assertEquals("10", calc.printHistory());

        calc.process(new Undo());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());
    }

    @Test
    public void undo_nothing() {
        final var calc = new Calculator();

        calc.process(new NumericOperand("10"));

        assertEquals("10", calc.print());
        assertEquals("10", calc.printHistory());

        calc.process(new Undo());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());

        calc.process(new Undo());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());
    }

}