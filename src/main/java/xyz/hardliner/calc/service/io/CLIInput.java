package xyz.hardliner.calc.service.io;

import java.util.Scanner;

public class CLIInput implements InputProvider {

    Scanner in = new Scanner(System.in);

    @Override
    public String nextLine() {
        return in.nextLine();
    }
}
