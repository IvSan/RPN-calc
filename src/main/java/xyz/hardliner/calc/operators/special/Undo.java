package xyz.hardliner.calc.operators.special;

import lombok.EqualsAndHashCode;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.Operators.UNDO;

@EqualsAndHashCode
public class Undo implements SpecialOperator {

    @Override
    public String print() {
        return UNDO.alias;
    }

    @Override
    public Function<Stack<Operand>, ApplicableCheck> applicableChecker() {
        return items -> successfulCheck();
    }

    public static Function<List<Item>, List<Item>> undoResolvingRule() {
        return items -> {
            var itemsEffectiveCopy = new ArrayList<Item>();
            for (Item item : items) {
                if (!itemsEffectiveCopy.isEmpty() && item instanceof Undo) {
                    itemsEffectiveCopy.remove(itemsEffectiveCopy.size() - 1);
                } else itemsEffectiveCopy.add(item);
            }
            return itemsEffectiveCopy;
        };
    }
}
