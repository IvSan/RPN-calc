package xyz.hardliner.calc.exception;

public class NonApplicableOperator extends RuntimeException implements CalculatorException {

    public NonApplicableOperator(String message) {
        super(message);
    }

    public NonApplicableOperator(String message, Throwable cause) {
        super(message, cause);
    }
}
