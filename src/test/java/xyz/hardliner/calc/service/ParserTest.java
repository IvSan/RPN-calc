package xyz.hardliner.calc.service;

import org.junit.jupiter.api.Test;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Division;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.operators.special.Clear;
import xyz.hardliner.calc.operators.special.Undo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void should_parse() {
        final var sut = new Parser();
        final var line = "1 -45 4.5 999999999999999999999.000000000000000000001 + - * / sqrt undo clear";

        final var actual = sut.parseLine(line);
        final var expected = List.of(
            new NumericOperand("1"),
            new NumericOperand("-45"),
            new NumericOperand("4.5"),
            new NumericOperand("999999999999999999999.000000000000000000001"),
            new Addition(),
            new Subtraction(),
            new Multiplication(),
            new Division(),
            new SquareRoot(),
            new Undo(),
            new Clear()
        );

        assertEquals(expected, actual);
    }

}