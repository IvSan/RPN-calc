package xyz.hardliner.calc.operators.math;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.hardliner.calc.TestUtils.calculator;

public class SquareRootTest {

    @Test
    public void should_take_square_root() {
        final var calc = calculator();

        calc.process(new NumericOperand("15.551"));

        assertEquals("15.551", calc.print());
        assertEquals("15.551", calc.printHistory());

        calc.process(new SquareRoot());

        assertEquals("3.9434756243", calc.print());
        assertEquals("15.551 sqrt", calc.printHistory());

        calc.process(new SquareRoot());

        assertEquals("1.9858186282", calc.print());
        assertEquals("15.551 sqrt sqrt", calc.printHistory());
    }

}