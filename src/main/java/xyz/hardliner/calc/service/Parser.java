package xyz.hardliner.calc.service;

import xyz.hardliner.calc.utils.OperandUtils;
import xyz.hardliner.calc.utils.OperatorUtils;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Item> parse(String line) {
        String[] rawItems = line.split(" ");
        List<Item> result = new ArrayList<>();

        for (String rawItem : rawItems) {
            if (OperandUtils.isOperand(rawItem)) {
                result.add(OperandUtils.parseOperand(rawItem).instantiate(rawItem));
                continue;
            }
            result.add(OperatorUtils.parse(rawItem));
        }

        return result;
    }

}
