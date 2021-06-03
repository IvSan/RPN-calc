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
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.551"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.551 10", calc.print());
        assertEquals("81 15.551 10", calc.printHistory());

        calc.add(new Multiplication());

        assertEquals("stack: 81 155.51", calc.print());
        assertEquals("81 15.551 10 *", calc.printHistory());

        calc.add(new Multiplication());

        assertEquals("stack: 12596.31", calc.print());
        assertEquals("81 15.551 10 * *", calc.printHistory());
    }

}