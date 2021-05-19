package xyz.hardliner.calc;

import org.apache.commons.lang3.tuple.Pair;
import xyz.hardliner.calc.exception.InsufficientParametersException;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.MathematicalOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StackWithHistory {

    public final Stack<Item> stack;
    public final Stack<Item> history;
    private final List<ItemResolvingRule> rules;

    public StackWithHistory() {
        stack = new Stack<>();
        history = new Stack<>();

        rules = List.of(
            operandRule(),
            mathOperatorRule(),
            unrecognizedItemRule()
        );
    }

    public StackWithHistory process(Item item) {
        rules.stream().filter(rule -> rule.isApplicable(item)).findFirst().get().apply(item, stack, history);
        return this;
    }

    private ItemResolvingRule operandRule() {
        return new ItemResolvingRule(
            item -> item instanceof Operand,
            (item, state) -> {
                stack.push(item);
                history.push(item);
            });
    }

    private ItemResolvingRule mathOperatorRule() {
        return new ItemResolvingRule(
            item -> item instanceof MathematicalOperator,
            (item, state) -> {
                final var stack = state.getLeft();
                final var history = state.getRight();
                final var mathOperator = (MathematicalOperator) item;

                checkAvailableOperandsNumber(mathOperator);

                final var operands = new ArrayList<Operand>();
                for (int i = 0; i < mathOperator.arity(); i++) {
                    final var operand = stack.pop();
                    if (operand instanceof Operand) {
                        operands.add((Operand) operand);
                    } else {
                        throw new NonApplicableOperation(String.format("invalid stack state: cannot apply operator '%s' on '%s'", item.print(), print()));
                    }
                }

                stack.push(mathOperator.effect().apply(operands));
                history.push(mathOperator);
            });
    }

    private ItemResolvingRule unrecognizedItemRule() {
        return new ItemResolvingRule(
            item -> true,
            (item, state) -> {
                throw new NonApplicableOperation(String.format("Item not recognized: '%s'", item.print()));
            });
    }

    private void checkAvailableOperandsNumber(MathematicalOperator mathematicalOperator) {
        var inputsCounter = 0;
        Stack<?> stack = (Stack<?>) this.stack.clone();

        while (!stack.empty()) {
            if (stack.pop() instanceof Operand) {
                inputsCounter++;
                if (inputsCounter >= mathematicalOperator.arity()) {
                    return;
                }
            }
        }

        throw new InsufficientParametersException(
            String.format("operator '%s' (position: %d): insufficient parameters", print(), this.stack.size() + 1)
        );
    }

    public String print() {
        return stack.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    public String printHistory() {
        return history.stream().map(Item::print).collect(Collectors.joining(" "));
    }

    private static final class ItemResolvingRule {

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
}
