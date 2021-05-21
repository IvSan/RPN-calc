package xyz.hardliner.calc.utils;

import org.apache.commons.lang3.math.NumberUtils;
import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operands.Operand;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public enum Operands {
    NUMBER(NumberUtils::isParsable, line -> {
        return new NumericOperand(new BigDecimal(line));
    });

    private final Predicate<String> checker;
    private final Function<String, Operand> operandGenerator;

    Operands(Predicate<String> checker, Function<String, Operand> operandGenerator) {
        this.checker = checker;
        this.operandGenerator = operandGenerator;
    }

    public static boolean isOperand(String line) {
        return stream(Operands.values()).anyMatch(operand -> operand.checker.test(line));
    }

    public static Operands parseOperand(String line) {
        return stream(Operands.values()).filter(operand -> operand.checker.test(line)).findFirst().orElseThrow(
            () -> new NonApplicableOperation("Cannot parse any operand in string line: " + line)
        );
    }

    public Operand instantiate(String line) {
        return operandGenerator.apply(line);
    }

}
