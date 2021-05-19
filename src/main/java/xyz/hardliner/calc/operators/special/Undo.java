package xyz.hardliner.calc.operators.special;

import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.ItemResolvingRule;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.BinaryMathematicalOperator;
import xyz.hardliner.calc.operators.math.UnaryMathematicalOperator;

import java.util.List;
import java.util.Stack;

public class Undo implements SpecialOperator {

    @Override
    public String print() {
        return "undo";
    }

    public static ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof Undo,
            (item, state) -> {
                final var stack = state.getLeft();
                final var history = state.getRight();

                if (history.empty()) return; // nothing to undo

                final var last = history.pop();
                stack.pop();

                if (last instanceof Operand) return;

                if (last instanceof UnaryMathematicalOperator) {
                    stack.push(unpackUnaryOperation((UnaryMathematicalOperator) last, history, new Stack<>()));
                }

                if (last instanceof BinaryMathematicalOperator) {
                    final var second = history.pop();
                    final var first = history.peek();
                    stack.push(first);
                    stack.push(second);
                    history.push(second);
                }
            });
    }

    private static Item unpackUnaryOperation(UnaryMathematicalOperator lastOperator, Stack<Item> history, Stack<Item> accumulator) {
        final var previous = history.pop();
        accumulator.push(previous);

        if (previous instanceof Operand) {
            while (!accumulator.isEmpty()) {
                history.push(accumulator.pop());
            }
            return previous;
        }

        if (previous instanceof UnaryMathematicalOperator) {
            return lastOperator.effect().apply(List.of((Operand) unpackUnaryOperation((UnaryMathematicalOperator) previous, history, accumulator)));
        }

        throw new IllegalStateException("fff");
    }

}
