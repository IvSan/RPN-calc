package xyz.hardliner.calc.service.io;

import java.util.Scanner;

public class CLIInput implements InputProvider {

    private final Scanner in = new Scanner(System.in);
    private String buffer = "";

    @Override
    public String nextLine() {
        buffer = in.nextLine();
        return buffer;
    }

    @Override
    public String repeatLastLine() {
        return buffer;
    }
}
