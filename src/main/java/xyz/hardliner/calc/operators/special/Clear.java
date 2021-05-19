package xyz.hardliner.calc.operators.special;

import xyz.hardliner.calc.ItemResolvingRule;

public class Clear implements SpecialOperator {

    @Override
    public String print() {
        return "clear";
    }

    public static ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof Clear,
            (item, state) -> {
                state.getLeft().clear();
                state.getRight().clear();
            });
    }

}
