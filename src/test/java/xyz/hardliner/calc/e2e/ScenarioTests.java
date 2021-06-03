package xyz.hardliner.calc.e2e;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioTests {

    @Test
    public void scenario_1() {
        final var sut = new Calculator(new Parser());
        assertEquals("stack: 5 2", sut.calculate("5 2"));
    }

    @Test
    public void scenario_2() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 1.4142135623", sut.calculate("2 sqrt"));
        assertEquals("stack: 3", sut.calculate("clear 9 sqrt"));
    }

    @Test
    public void scenario_3() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 3", sut.calculate("5 2 -"));
        assertEquals("stack: 0", sut.calculate("3 -"));
        assertEquals("stack: ", sut.calculate("clear"));
    }

    @Test
    public void scenario_4() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 5 4 3 2", sut.calculate("5 4 3 2"));
        assertEquals("stack: 20", sut.calculate("undo undo *"));
        assertEquals("stack: 100", sut.calculate("5 *"));
        assertEquals("stack: 20 5", sut.calculate("undo"));
    }

    @Test
    public void scenario_5() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 7 6", sut.calculate("7 12 2 /"));
        assertEquals("stack: 42", sut.calculate("*"));
        assertEquals("stack: 10.5", sut.calculate("4 /"));
    }

    @Test
    public void scenario_6() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 1 2 3 4 5", sut.calculate("1 2 3 4 5"));
        assertEquals("stack: 1 2 3 20", sut.calculate("*"));
        assertEquals("stack: -1", sut.calculate("clear 3 4 -"));
    }

    @Test
    public void scenario_7() {
        final var sut = new Calculator(new Parser());

        assertEquals("stack: 1 2 3 4 5", sut.calculate("1 2 3 4 5"));
        assertEquals("stack: 120", sut.calculate("* * * *"));
    }

    @Test
    public void scenario_8() {
        final var sut = new Calculator(new Parser());

        assertEquals("operator '*' (position: 15): insufficient parameters\nstack: 11", sut.calculate("1 2 3 * 5 + * * 6 5"));
    }

}
