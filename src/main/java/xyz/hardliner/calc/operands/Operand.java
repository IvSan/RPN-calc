package xyz.hardliner.calc.operands;

import xyz.hardliner.calc.service.Item;
import xyz.hardliner.calc.service.ItemResolvingRule;

public interface Operand extends Item {

    default ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule((item, stack) -> stack.add((Operand) item));
    }

}
