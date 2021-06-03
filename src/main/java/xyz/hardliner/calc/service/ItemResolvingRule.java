package xyz.hardliner.calc.service;

import xyz.hardliner.calc.operands.Operand;

import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * Represent an instruction how {@link Calculator} should process an {@link Item}.
 */
public class ItemResolvingRule {

    private final BiConsumer<Item, Stack<Operand>> processor;

    public ItemResolvingRule(BiConsumer<Item, Stack<Operand>> processor) {
        this.processor = processor;
    }

    /**
     * Apply the rule and mutate {@link Calculator}'s state.
     *
     * @param item  - processed {@link Item}.
     * @param stack - {@link Calculator}'s actual stack.
     */
    public void apply(Item item, Stack<Operand> stack) {
        processor.accept(item, stack);
    }
}
