package xyz.hardliner.calc;

import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;
import xyz.hardliner.calc.service.io.CLIInput;
import xyz.hardliner.calc.service.io.OutputProvider;

import static org.mockito.Mockito.mock;

public class TestUtils {

    public static Calculator calculator() {
        return new Calculator(mock(CLIInput.class), mock(OutputProvider.class), mock(Parser.class));
    }
}
