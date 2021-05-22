package xyz.hardliner.calc.operators.math;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class MultiplicationTest {

    @Test
    public void should_multiplicate() {
        final var calc = calculator();

        calc
            .process(new NumericOperand("81"))
            .process(new NumericOperand("15.551"))
            .process(new NumericOperand("10"));

        assertEquals("81 15.551 10", calc.print());
        assertEquals("81 15.551 10", calc.printHistory());

        calc.process(new Multiplication());

        assertEquals("81 155.51", calc.print());
        assertEquals("81 15.551 10 *", calc.printHistory());

        calc.process(new Multiplication());

        assertEquals("12596.31", calc.print());
        assertEquals("81 15.551 10 * *", calc.printHistory());
    }

}