package xyz.hardliner.calc.operators.special;

import org.apache.commons.lang3.tuple.Pair;
import xyz.hardliner.calc.Item;
import xyz.hardliner.calc.ItemResolvingRule;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.BinaryMathematicalOperator;
import xyz.hardliner.calc.operators.math.UnaryMathematicalOperator;

import java.util.List;
import java.util.Stack;

import static xyz.hardliner.calc.utils.StackUtils.cloneStack;

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
                    final var pair = unpackBinaryOperation(cloneStack(history));
                    stack.push(pair.getLeft());
                    stack.push(pair.getRight());
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
        } else if (previous instanceof UnaryMathematicalOperator) {
            return lastOperator.effect().apply(List.of((Operand) unpackUnaryOperation((UnaryMathematicalOperator) previous, history, accumulator)));
        } else throw new IllegalStateException("fff");
    }

    private static Pair<Item, Item> unpackBinaryOperation(Stack<Item> history) {

        final var second = history.pop();
        final Item secondToReturn;

        if (second instanceof Operand) {
            secondToReturn = second;
        } else if (second instanceof BinaryMathematicalOperator) {
            final var pair = unpackBinaryOperation(history);
            secondToReturn = ((BinaryMathematicalOperator) second).effect().apply(List.of((Operand) pair.getRight(), (Operand) pair.getLeft()));
        } else {
            throw new NonApplicableOperation("Undo operation hasn't been implemented for " + second.print());
        }

        final var first = history.pop();
        final Item firstToReturn;

        if (first instanceof Operand) {
            firstToReturn = first;
        } else if (second instanceof BinaryMathematicalOperator) {
            final var pair = unpackBinaryOperation(history);
            firstToReturn = ((BinaryMathematicalOperator) second).effect().apply(List.of((Operand) pair.getRight(), (Operand) pair.getLeft()));
        } else {
            throw new NonApplicableOperation("Undo operation hasn't been implemented for " + second.print());
        }

        return Pair.of(firstToReturn, secondToReturn);
    }
}
