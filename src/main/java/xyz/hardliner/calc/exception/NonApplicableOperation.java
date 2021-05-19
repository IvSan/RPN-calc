package xyz.hardliner.calc.exception;

public class NonApplicableOperation extends RuntimeException implements CalculatorException {

    public NonApplicableOperation(String message) {
        super(message);
    }

    public NonApplicableOperation(String message, Throwable cause) {
        super(message, cause);
    }
}
