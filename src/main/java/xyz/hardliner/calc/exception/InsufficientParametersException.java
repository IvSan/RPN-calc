package xyz.hardliner.calc.exception;

public class InsufficientParametersException extends RuntimeException implements CalculatorException {

    public InsufficientParametersException(String message) {
        super(message);
    }

    public InsufficientParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
