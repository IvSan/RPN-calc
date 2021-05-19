package xyz.hardliner.calc.operands;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.ItemResolvingRule;

public interface Operand extends Item {

    static ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof Operand,
            (item, state) -> {
                state.getLeft().push(item);
                state.getRight().push(item);
            });
    }

}
