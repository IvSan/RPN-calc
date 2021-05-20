package xyz.hardliner.calc;

import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.MathematicalOperator;
import xyz.hardliner.calc.operators.special.Clear;
import xyz.hardliner.calc.operators.special.Undo;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {

    public final Stack<Item> stack;
    public final Stack<Item> history;
    private final List<ItemResolvingRule> rules;

    public Calculator() {
        stack = new Stack<>();
        history = new Stack<>();

        rules = List.of(
            Operand.resolvingRule(),
            MathematicalOperator.resolvingRule(),
            Clear.resolvingRule(),
            Undo.resolvingRule(),
            unrecognizedItemRule()
        );
    }

    public Calculator process(Item item) {
        rules.stream().filter(rule -> rule.isApplicable(item)).findFirst().get().apply(item, stack, history);
        return this;
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    private ItemResolvingRule unrecognizedItemRule() {
        return new ItemResolvingRule(
            item -> true,
            (item, state) -> {
                throw new NonApplicableOperation(String.format("Item not recognized: '%s'", item.print()));
            });
    }
}
