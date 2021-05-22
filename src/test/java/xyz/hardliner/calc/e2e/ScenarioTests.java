package xyz.hardliner.calc.e2e;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioTests {

    @Test
    public void scenario_1() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("5 2");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 5 2"), out.getOutputs());
    }

    @Test
    public void scenario_2() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("2 sqrt");
        in.addInput("clear 9 sqrt");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 1.4142135623", "stack: 3"), out.getOutputs());
    }

    @Test
    public void scenario_3() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("5 2 -");
        in.addInput("3 -");
        in.addInput("clear");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 3", "stack: 0", "stack: "), out.getOutputs());
    }

    @Test
    public void scenario_4() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("5 4 3 2");
        in.addInput("undo undo *");
        in.addInput("5 *");
        in.addInput("undo");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 5 4 3 2", "stack: 20", "stack: 100", "stack: 20 5"), out.getOutputs());
    }

    @Test
    public void scenario_5() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("7 12 2 /");
        in.addInput("*");
        in.addInput("4 /");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 7 6", "stack: 42", "stack: 10.5"), out.getOutputs());
    }

    @Test
    public void scenario_6() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("1 2 3 4 5");
        in.addInput("*");
        in.addInput("clear 3 4 -");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 1 2 3 4 5", "stack: 1 2 3 20", "stack: -1"), out.getOutputs());
    }

    @Test
    public void scenario_7() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("1 2 3 4 5");
        in.addInput("* * * *");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("stack: 1 2 3 4 5", "stack: 120"), out.getOutputs());
    }

    @Test
    public void scenario_8() {
        final var in = new TestInput();
        final var out = new TestOutput();

        in.addInput("1 2 3 * 5 + * * 6 5");
        final var sut = new Calculator(in, out, new Parser());

        sut.run();

        assertEquals(List.of("operator '*' (position: 15): insufficient parameters", "stack: 11"), out.getOutputs());
    }

}
