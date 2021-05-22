package xyz.hardliner.calc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.service.io.InputProvider;
import xyz.hardliner.calc.service.io.OutputProvider;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    private final InputProvider in = mock(InputProvider.class);
    private final OutputProvider out = mock(OutputProvider.class);
    private final Parser parser = mock(Parser.class);

    @BeforeEach
    public void setup() {
        reset(in, out, parser);
    }

    @Test
    public void should_run_calc() {
        final var inputFirst = "4.5 5 + sqrt";
        when(parser.parseLine(eq(inputFirst))).thenReturn(List.of(
            new NumericOperand("4.5"),
            new NumericOperand("5"),
            new Addition(),
            new SquareRoot()
        ));
        final var inputSecond = "4 *";
        when(parser.parseLine(eq(inputSecond))).thenReturn(List.of(
            new NumericOperand("4"),
            new Multiplication()
        ));
        when(in.nextLine()).thenReturn(inputFirst).thenReturn(inputSecond).thenReturn("exit");

        final var sut = new Calculator(in, out, parser);
        sut.run();

        verify(in, times(3)).nextLine();
        verify(parser, times(2)).parseLine(any(String.class));
        verify(out, times(1)).outputLine(eq("stack: 3.0822070015"));
        verify(out, times(1)).outputLine(eq("stack: 12.3288280059"));
        verifyNoMoreInteractions(in, out, parser);
    }

    @Test
    public void should_handle_error() {
        final var input = "4.5 5 - sqrt";
        when(in.nextLine()).thenReturn(input).thenReturn("exit");
        when(parser.parseLine(eq(input))).thenReturn(List.of(
            new NumericOperand("4.5"),
            new NumericOperand("5"),
            new Subtraction(),
            new SquareRoot()
        ));

        final var sut = new Calculator(in, out, parser);
        sut.run();

        verify(in, times(2)).nextLine();
        verify(parser, times(1)).parseLine(eq(input));
        verify(out, times(1)).outputLine(eq("operator 'sqrt' (position: 9): cannot apply to '-0.5'"));
        verify(out, times(1)).outputLine(eq("stack: -0.5"));
        verifyNoMoreInteractions(in, out, parser);
    }
}