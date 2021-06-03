package xyz.hardliner.calc.operators.math;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class SubtractionTest {

    @Test
    public void should_subtract() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.551"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.551 10", calc.print());
        assertEquals("81 15.551 10", calc.printHistory());

        calc.add(new Subtraction());

        assertEquals("stack: 81 5.551", calc.print());
        assertEquals("81 15.551 10 -", calc.printHistory());

        calc.add(new Subtraction());

        assertEquals("stack: 75.449", calc.print());
        assertEquals("81 15.551 10 - -", calc.printHistory());
    }

}