package xyz.hardliner.calc.operators.special;

import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;
import xyz.hardliner.calc.service.ItemResolvingRule;

import java.util.Stack;
import java.util.function.Function;

import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;

public class Clear implements SpecialOperator {

    @Override
    public String print() {
        return "clear";
    }

    @Override
    public Function<Stack<Item>, ApplicableCheck> applicableChecker() {
        return items -> successfulCheck();
    }

    @Override
    public ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof Clear,
            (item, state) -> {
                state.getLeft().clear();
                state.getRight().clear();
            });
    }

}
