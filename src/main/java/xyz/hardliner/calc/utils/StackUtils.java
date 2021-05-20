package xyz.hardliner.calc.utils;

import java.util.Stack;

public class StackUtils {

    public static <T> Stack<T> cloneStack(Stack<T> original) {
        final var cloned = new Stack<T>();
        cloned.addAll(original);
        return cloned;
    }

}
