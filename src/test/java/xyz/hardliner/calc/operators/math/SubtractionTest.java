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
            .process(new NumericOperand("81"))
            .process(new NumericOperand("15.551"))
            .process(new NumericOperand("10"));

        assertEquals("81 15.551 10", calc.print());
        assertEquals("81 15.551 10", calc.printHistory());

        calc.process(new Subtraction());

        assertEquals("81 5.551", calc.print());
        assertEquals("81 15.551 10 -", calc.printHistory());

        calc.process(new Subtraction());

        assertEquals("75.449", calc.print());
        assertEquals("81 15.551 10 - -", calc.printHistory());
    }

}