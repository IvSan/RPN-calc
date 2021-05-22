package xyz.hardliner.calc;

import xyz.hardliner.calc.service.CLIInputProvider;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;

import static org.mockito.Mockito.mock;

public class TestUtils {

    public static Calculator calculator() {
        return new Calculator(mock(CLIInputProvider.class), mock(Parser.class));
    }
}
