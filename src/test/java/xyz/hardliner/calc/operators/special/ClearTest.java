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
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.5"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.5 10", calc.print());
        assertEquals("81 15.5 10", calc.printHistory());

        calc.add(new Clear());

        assertEquals("stack: ", calc.print());
        assertEquals("81 15.5 10 clear", calc.printHistory());
    }

}