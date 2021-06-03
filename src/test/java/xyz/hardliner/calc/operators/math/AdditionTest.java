package xyz.hardliner.calc.operators.math;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class AdditionTest {

    @Test
    public void should_add() {
        final var calc = calculator();

        calc
            .add(new NumericOperand("81"))
            .add(new NumericOperand("15.551"))
            .add(new NumericOperand("10"));

        assertEquals("stack: 81 15.551 10", calc.print());
        assertEquals("81 15.551 10", calc.printHistory());

        calc.add(new Addition());

        assertEquals("stack: 81 25.551", calc.print());
        assertEquals("81 15.551 10 +", calc.printHistory());

        calc.add(new Addition());

        assertEquals("stack: 106.551", calc.print());
        assertEquals("81 15.551 10 + +", calc.printHistory());
    }

}