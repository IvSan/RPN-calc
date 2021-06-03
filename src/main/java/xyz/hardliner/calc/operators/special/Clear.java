package xyz.hardliner.calc.operators.special;

import lombok.EqualsAndHashCode;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.Operators.CLEAR;

@EqualsAndHashCode
public class Clear implements SpecialOperator {

    @Override
    public String print() {
        return CLEAR.alias;
    }

    @Override
    public Function<Stack<Operand>, ApplicableCheck> applicableChecker() {
        return items -> successfulCheck();
    }

    public static Function<List<Item>, List<Item>> clearResolvingRule() {
        return items -> {
            var itemsEffectiveCopy = new LinkedList<Item>();
            for (Item item : items) {
                if (item instanceof Clear) {
                    itemsEffectiveCopy.clear();
                } else itemsEffectiveCopy.add(item);
            }
            return itemsEffectiveCopy;
        };
    }

}
