package xyz.hardliner.calc.service;

public class ApplicableCheck {

    private final boolean ok;
    private final String failMessage;

    public ApplicableCheck(boolean ok, String failMessage) {
        this.ok = ok;
        this.failMessage = failMessage;
    }

    public static ApplicableCheck successfulCheck() {
        return new ApplicableCheck(true, "");
    }

    public static ApplicableCheck failedCheck(String message) {
        return new ApplicableCheck(false, message);
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isFail() {
        return !ok;
    }

    public String getFailMessage() {
        if (ok) {
            throw new IllegalStateException("Applicable check is ok, no fail message");
        }
        return failMessage;
    }
}
