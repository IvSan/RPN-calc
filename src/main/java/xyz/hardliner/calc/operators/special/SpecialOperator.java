package xyz.hardliner.calc.operators.special;

import xyz.hardliner.calc.operators.Operator;
import xyz.hardliner.calc.service.ItemResolvingRule;

public interface SpecialOperator extends Operator {

    @Override
    default ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule((item, state) -> {
        });
    }

}
