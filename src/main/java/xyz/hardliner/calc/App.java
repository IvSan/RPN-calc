package xyz.hardliner.calc;

import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;
import xyz.hardliner.calc.service.io.CLIInput;
import xyz.hardliner.calc.service.io.CLIOutput;

public class App {

    public static void main(String[] args) {
        final var calc = new Calculator(new CLIInput(), new CLIOutput(), new Parser());
        calc.run();
    }

}
