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
import static xyz.hardliner.calc.utils.Operators.REDO;

@EqualsAndHashCode
public class Redo implements SpecialOperator {

    @Override
    public String print() {
        return REDO.alias;
    }

    @Override
    public Function<Stack<Operand>, ApplicableCheck> applicableChecker() {
        return items -> successfulCheck();
    }

    public static Function<List<Item>, List<Item>> redoResolvingRule() {
        return items -> {
            var itemsEffectiveCopy = new LinkedList<Item>();
            for (int i = 0; i < items.size(); i++) {
                if (i > 0 && items.get(i) instanceof Redo) {
                    if (itemsEffectiveCopy.getLast() instanceof Undo) {
                        itemsEffectiveCopy.removeLast();
                    }
                } else itemsEffectiveCopy.add(items.get(i));
            }
            return itemsEffectiveCopy;
        };
    }
}
