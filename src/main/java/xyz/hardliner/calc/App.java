package xyz.hardliner.calc;

import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;
import xyz.hardliner.calc.service.io.CLIInput;
import xyz.hardliner.calc.service.io.CLIOutput;

public class App {

    public static void main(String[] args) {
        final var input = new CLIInput();
        final var output = new CLIOutput();
        final var calc = new Calculator(new Parser());
        while (true) {
            output.outputLine(calc.calculate(input.nextLine()));
        }
    }

}
