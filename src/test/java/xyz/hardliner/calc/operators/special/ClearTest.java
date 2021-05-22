package xyz.hardliner.calc.operators.special;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class ClearTest {

    @Test
    public void should_clear() {
        final var calc = calculator();

        calc
            .process(new NumericOperand("81"))
            .process(new NumericOperand("15.5"))
            .process(new NumericOperand("10"));

        assertEquals("81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.process(new Clear());

        assertEquals("", calc.print());
        assertEquals("", calc.printHistory());
    }

}