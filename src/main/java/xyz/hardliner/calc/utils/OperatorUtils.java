package xyz.hardliner.calc.utils;

import xyz.hardliner.calc.exception.NonApplicableOperation;
import xyz.hardliner.calc.operators.Operator;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Division;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.operators.special.Clear;
import xyz.hardliner.calc.operators.special.Undo;

import java.util.function.Supplier;

import static java.util.Arrays.stream;

public enum OperatorUtils {
    ADDITION("+", Addition::new),
    SUBTRACTION("-", Subtraction::new),
    MULTIPLICATION("*", Multiplication::new),
    DIVISION("/", Division::new),
    SQUARE_ROOT("sqrt", SquareRoot::new),
    CLEAR("clear", Clear::new),
    UNDO("undo", Undo::new),
    ;

    public final String alias;
    private final Supplier<Operator> operatorGenerator;

    OperatorUtils(String alias, Supplier<Operator> operatorGenerator) {
        this.alias = alias;
        this.operatorGenerator = operatorGenerator;
    }

    public static Operator parse(String alias) {
        final var operator = stream(OperatorUtils.values()).filter(a -> a.alias.equals(alias)).findFirst()
            .orElseThrow(() -> new NonApplicableOperation("Unrecognized operator: " + alias));
        return operator.operatorGenerator.get();
    }
}
