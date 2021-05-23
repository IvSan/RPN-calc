package xyz.hardliner.calc.service;

import java.util.ArrayList;
import java.util.List;

import static xyz.hardliner.calc.utils.Operands.isOperand;
import static xyz.hardliner.calc.utils.Operands.parseOperand;
import static xyz.hardliner.calc.utils.Operators.parseOperator;

public class Parser {

    public List<Item> parseLine(String line) {
        String[] rawItems = line.trim().replaceAll(" +", " ").split(" ");
        List<Item> result = new ArrayList<>();

        for (String rawItem : rawItems) {
            if (isOperand(rawItem)) {
                result.add(parseOperand(rawItem).instantiate(rawItem));
                continue;
            }
            result.add(parseOperator(rawItem));
        }

        return result;
    }

}
