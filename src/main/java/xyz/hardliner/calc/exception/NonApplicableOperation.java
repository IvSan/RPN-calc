package xyz.hardliner.calc.exception;

public class NonApplicableOperation extends CalculatorException {

    public NonApplicableOperation(String message) {
        super(message);
    }

    public NonApplicableOperation(String message, Throwable cause) {
        super(message, cause);
    }

}
