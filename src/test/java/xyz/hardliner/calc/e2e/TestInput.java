package xyz.hardliner.calc.e2e;

import xyz.hardliner.calc.service.io.InputProvider;

import java.util.LinkedList;

public class TestInput implements InputProvider {

    private LinkedList<String> inputs = new LinkedList<>();

    @Override
    public String nextLine() {
        if (inputs.isEmpty()) {
            return "exit";
        }
        return inputs.removeFirst();
    }

    public void addInput(String input) {
        inputs.add(input);
    }

    public void reset() {
        inputs.clear();
    }

}
