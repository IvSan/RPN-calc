package xyz.hardliner.calc.service;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;
import java.util.function.BiConsumer;

/**
 * Represent an instruction how {@link Calculator} should process an {@link Item}.
 */
public class ItemResolvingRule {

    private final BiConsumer<Item, Pair<Stack<Item>, Stack<Item>>> processor;

    public ItemResolvingRule(BiConsumer<Item, Pair<Stack<Item>, Stack<Item>>> processor) {
        this.processor = processor;
    }

    /**
     * Apply the rule and mutate {@link Calculator}'s state.
     *
     * @param item    - processed {@link Item}.
     * @param stack   - {@link Calculator}'s actual stack.
     * @param history - {@link Calculator}'s history stack.
     */
    public void apply(Item item, Stack<Item> stack, Stack<Item> history) {
        processor.accept(item, Pair.of(stack, history));
    }
}
