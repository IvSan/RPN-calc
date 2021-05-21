package xyz.hardliner.calc.operators.special;

import org.apache.commons.lang3.tuple.Pair;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.Operand;
import xyz.hardliner.calc.operators.math.BinaryMathematicalOperator;
import xyz.hardliner.calc.operators.math.UnaryMathematicalOperator;
import xyz.hardliner.calc.service.ApplicableCheck;
import xyz.hardliner.calc.service.Item;
import xyz.hardliner.calc.service.ItemResolvingRule;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

import static xyz.hardliner.calc.service.ApplicableCheck.successfulCheck;
import static xyz.hardliner.calc.utils.Operators.UNDO;
import static xyz.hardliner.calc.utils.StackUtils.cloneStack;

public class Undo implements SpecialOperator {

    @Override
    public String print() {
        return UNDO.alias;
    }

    @Override
    public Function<Stack<Item>, ApplicableCheck> applicableChecker() {
        return items -> successfulCheck();
    }

    @Override
    public ItemResolvingRule resolvingRule() {
        return new ItemResolvingRule(
            item -> item instanceof Undo,
            (item, state) -> {
                final var stack = state.getLeft();
                final var history = state.getRight();

                if (history.empty()) return; // nothing to undo

                final var itemToUndo = history.pop();
                stack.pop();

                if (itemToUndo instanceof Operand) return;
                else if (itemToUndo instanceof UnaryMathematicalOperator) {
                    stack.push(unpackItem(cloneStack(history)));
                } else if (itemToUndo instanceof BinaryMathematicalOperator) {
                    final var pair = unpackBinaryOperation(cloneStack(history));
                    stack.push(pair.getLeft());
                    stack.push(pair.getRight());
                } else {
                    throw new NonApplicableOperation("Undo operation hasn't been implemented for " + itemToUndo.print());
                }
            });
    }

    private Pair<Item, Item> unpackBinaryOperation(Stack<Item> history) {
        final var second = unpackItem(history);
        final var first = unpackItem(history);
        return Pair.of(first, second);
    }

    private Item unpackItem(Stack<Item> history) {
        final var item = history.pop();
        if (item instanceof Operand) {
            return item;
        } else if (item instanceof UnaryMathematicalOperator) {
            return ((UnaryMathematicalOperator) item).effect().apply(List.of((Operand) unpackItem(history)));
        } else if (item instanceof BinaryMathematicalOperator) {
            final var pair = unpackBinaryOperation(history);
            return ((BinaryMathematicalOperator) item).effect().apply(List.of((Operand) pair.getRight(), (Operand) pair.getLeft()));
        } else {
            throw new NonApplicableOperation("Undo operation hasn't been implemented for " + item.print());
        }
    }
}
