package xyz.hardliner.calc.service;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ItemResolvingRule {

    private final Predicate<Item> checker;
    private final BiConsumer<Item, Pair<Stack<Item>, Stack<Item>>> processor;

    public ItemResolvingRule(Predicate<Item> checker,
                             BiConsumer<Item, Pair<Stack<Item>, Stack<Item>>> processor) {
        this.checker = checker;
        this.processor = processor;
    }

    public boolean isApplicable(Item operator) {
        return checker.test(operator);
    }

    public void apply(Item item, Stack<Item> stack, Stack<Item> history) {
        processor.accept(item, Pair.of(stack, history));
    }
}
