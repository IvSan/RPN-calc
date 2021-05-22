package xyz.hardliner.calc;

import xyz.hardliner.calc.service.CLIInputProvider;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Parser;

public class App {

    public static void main(String[] args) {
        final var calc = new Calculator(new CLIInputProvider(), new Parser());
        calc.run();
    }

}
