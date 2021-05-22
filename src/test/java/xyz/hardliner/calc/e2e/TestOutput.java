package xyz.hardliner.calc.e2e;

import xyz.hardliner.calc.service.io.OutputProvider;

import java.util.ArrayList;
import java.util.List;

public class TestOutput implements OutputProvider {

    private List<String> outputs = new ArrayList<>();

    @Override
    public void outputLine(String line) {
        outputs.add(line);
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public void reset() {
        outputs.clear();
    }

}
