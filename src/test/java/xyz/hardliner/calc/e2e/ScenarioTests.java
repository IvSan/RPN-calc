package xyz.hardliner.calc.e2e;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioTests {

    @Test
    public void scenario_1() {
        final var sut = new Calculator(new Parser());
        sut.add("5 2");
        assertEquals("stack: 5 2", sut.print());
    }

    @Test
    public void scenario_2() {
        final var sut = new Calculator(new Parser());

        sut.add("2 sqrt");
        assertEquals("stack: 1.4142135623", sut.print());
        sut.add("clear 9 sqrt");
        assertEquals("stack: 3", sut.print());
    }

    @Test
    public void scenario_3() {
        final var sut = new Calculator(new Parser());

        sut.add("5 2 -");
        assertEquals("stack: 3", sut.print());
        sut.add("3 -");
        assertEquals("stack: 0", sut.print());
        sut.add("clear");
        assertEquals("stack: ", sut.print());
    }

    @Test
    public void scenario_4() {
        final var sut = new Calculator(new Parser());

        sut.add("5 4 3 2");
        assertEquals("stack: 5 4 3 2", sut.print());
        sut.add("undo undo *");
        assertEquals("stack: 20", sut.print());
        sut.add("5 *");
        assertEquals("stack: 100", sut.print());
        sut.add("undo");
        assertEquals("stack: 20 5", sut.print());
    }

    @Test
    public void scenario_5() {
        final var sut = new Calculator(new Parser());

        sut.add("7 12 2 /");
        assertEquals("stack: 7 6", sut.print());
        sut.add("*");
        assertEquals("stack: 42", sut.print());
        sut.add("4 /");
        assertEquals("stack: 10.5", sut.print());
    }

    @Test
    public void scenario_6() {
        final var sut = new Calculator(new Parser());

        sut.add("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", sut.print());
        sut.add("*");
        assertEquals("stack: 1 2 3 20", sut.print());
        sut.add("clear 3 4 -");
        assertEquals("stack: -1", sut.print());
    }

    @Test
    public void scenario_7() {
        final var sut = new Calculator(new Parser());

        sut.add("1 2 3 4 5");
        assertEquals("stack: 1 2 3 4 5", sut.print());
        sut.add("* * * *");
        assertEquals("stack: 120", sut.print());
    }

    @Test
    public void scenario_8() {
        final var sut = new Calculator(new Parser());

        sut.add("1 2 3 * 5 + * * 6 5");
        assertEquals("operator '*' (position: 15): insufficient parameters\nstack: 11", sut.print());
    }

}
