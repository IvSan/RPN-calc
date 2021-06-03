package xyz.hardliner.calc.exception;

import xyz.hardliner.calc.operands.Operand;

import java.util.Stack;

public class CalculatorExceptionWithLastSuccessfulState extends CalculatorException {

    public final Stack<Operand> lastSuccessfulState;

    public CalculatorExceptionWithLastSuccessfulState(String message, Stack<Operand> lastSuccessfulState) {
        super(message);
        this.lastSuccessfulState = lastSuccessfulState;
    }

    public CalculatorExceptionWithLastSuccessfulState(String message, Throwable cause, Stack<Operand> lastSuccessfulState) {
        super(message, cause);
        this.lastSuccessfulState = lastSuccessfulState;
    }

}
