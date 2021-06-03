package xyz.hardliner.calc.operators.special;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class UndoTest {

    @Test
    public void should_undo_plain_operand() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.5"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 81 15.5", calc.print());
        assertEquals("81 15.5 10 undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 81", calc.print());
        assertEquals("81 15.5 10 undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: ", calc.print());
        assertEquals("81 15.5 10 undo undo undo", calc.printHistory());
    }

    @Test
    public void should_undo_unary_operator() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new SquareRoot())
            .add(new SquareRoot());

        assertEquals("stack: 3", calc.print());
        assertEquals("81 sqrt sqrt", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 9", calc.print());
        assertEquals("81 sqrt sqrt undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 81", calc.print());
        assertEquals("81 sqrt sqrt undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: ", calc.print());
        assertEquals("81 sqrt sqrt undo undo undo", calc.printHistory());
    }

    @Test
    public void should_undo_binary_operator() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("4"))
            .add(new NumericOperand("3"))
            .add(new NumericOperand("2"))
            .add(new Subtraction())
            .add(new Addition());

        assertEquals("stack: 5", calc.print());
        assertEquals("4 3 2 - +", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 4 1", calc.print());
        assertEquals("4 3 2 - + undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 4 3 2", calc.print());
        assertEquals("4 3 2 - + undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 4 3", calc.print());
        assertEquals("4 3 2 - + undo undo undo", calc.printHistory());
    }

    @Test
    public void should_undo_mixed_operators() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("10"))
            .add(new NumericOperand("9"))
            .add(new SquareRoot())
            .add(new Addition())
            .add(new NumericOperand("4"))
            .add(new SquareRoot())
            .add(new Multiplication());

        assertEquals("stack: 26", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt *", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 13 2", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 13 4", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 13", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 10 3", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 10 9", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo undo undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 10", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo undo undo undo undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: ", calc.print());
        assertEquals("10 9 sqrt + 4 sqrt * undo undo undo undo undo undo undo", calc.printHistory());
    }

    @Test
    public void should_undo_nothing() {
        final var calc = calculator();

        calc.add(new NumericOperand("10"));

        assertEquals("stack: 10", calc.print());
        assertEquals("10", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: ", calc.print());
        assertEquals("10 undo", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: ", calc.print());
        assertEquals("10 undo undo", calc.printHistory());
    }

}