package xyz.hardliner.calc.service;

import java.util.Scanner;

public class CLIInputProvider implements InputProvider {

    Scanner in = new Scanner(System.in);

    @Override
    public String nextLine() {
        return in.nextLine();
    }
}
