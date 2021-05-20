package xyz.hardliner.calc.exception;

public class InsufficientParametersException extends CalculatorException {

    public InsufficientParametersException(String message) {
        super(message);
    }

    public InsufficientParametersException(String message, Throwable cause) {
        super(message, cause);
    }

}
