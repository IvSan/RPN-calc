package xyz.hardliner.calc;

import xyz.hardliner.calc.exception.CalculatorException;
import xyz.hardliner.calc.service.Calculator;
import xyz.hardliner.calc.service.Item;
import xyz.hardliner.calc.service.Parser;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final var parser = new Parser();
        final var calc = new Calculator();

        var line = in.nextLine();

        while (!"exit".equals(line)) {

            try {
                for (Item item : parser.parse(line)) {
                    calc.process(item);
                }
            } catch (CalculatorException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(calc.print());
            line = in.nextLine();

        }
    }

}
