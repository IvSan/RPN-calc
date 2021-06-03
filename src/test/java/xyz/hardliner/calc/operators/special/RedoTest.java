package xyz.hardliner.calc.operators.special;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class RedoTest {

    @Test
    public void should_redo_nothing() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.5"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.add(new Redo());

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10 redo", calc.printHistory());

        calc.add(new Redo());

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10 redo redo", calc.printHistory());
    }

    @Test
    public void should_redo_single_undo() {
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

        calc.add(new Redo());

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10 undo redo", calc.printHistory());
    }

    @Test
    public void should_redo_multiple_undo() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.5"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.add(new Undo());
        calc.add(new Undo());

        assertEquals("stack: 81", calc.print());
        assertEquals("81 15.5 10 undo undo", calc.printHistory());

        calc.add(new Redo());
        calc.add(new Redo());

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10 undo undo redo redo", calc.printHistory());

        calc.add(new Addition());

        assertEquals("stack: 81 25.5", calc.print());
        assertEquals("81 15.5 10 undo undo redo redo +", calc.printHistory());
    }

    @Test
    public void should_redo_clear() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.5"))
            .add(new NumericOperand("10"))
            .add(new Clear());

        assertEquals("stack: ", calc.print());
        assertEquals("81 15.5 10 clear", calc.printHistory());

        calc.add(new Undo());

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10 clear undo", calc.printHistory());

        calc.add(new Redo());

        assertEquals("stack: ", calc.print());
        assertEquals("81 15.5 10 clear undo redo", calc.printHistory());
    }
}