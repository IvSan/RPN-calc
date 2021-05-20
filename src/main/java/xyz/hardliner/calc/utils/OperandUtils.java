package xyz.hardliner.calc.utils;

import org.apache.commons.lang3.math.NumberUtils;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public enum OperandUtils {
    NUMBER(NumberUtils::isParsable, line -> {
        return new NumericOperand(Double.parseDouble(line));
    });

    private final Predicate<String> checker;
    private final Function<String, Operand> operandGenerator;

    OperandUtils(Predicate<String> checker, Function<String, Operand> operandGenerator) {
        this.checker = checker;
        this.operandGenerator = operandGenerator;
    }

    public static boolean isOperand(String line) {
        return stream(OperandUtils.values()).anyMatch(operand -> operand.checker.test(line));
    }

    public static OperandUtils parseOperand(String line) {
        return stream(OperandUtils.values()).filter(operand -> operand.checker.test(line)).findFirst().orElseThrow(
            () -> new NonApplicableOperation("Cannot parse any operand in string line: " + line)
        );
    }

    public Operand instantiate(String line) {
        return operandGenerator.apply(line);
    }

}
