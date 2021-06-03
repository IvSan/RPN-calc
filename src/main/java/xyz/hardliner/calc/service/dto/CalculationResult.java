package xyz.hardliner.calc.service.dto;

import xyz.hardliner.calc.operands.Operand;

import java.util.Optional;
import java.util.Stack;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static xyz.hardliner.calc.service.dto.CalculationStatus.SUCCESS;

public class CalculationResult {

    public final CalculationStatus status;
    public final Stack<Operand> result;
    public final Optional<String> errorMessage;

    public CalculationResult(Stack<Operand> result) {
        this.status = SUCCESS;
        this.result = result;
        this.errorMessage = empty();
    }

    public CalculationResult(CalculationStatus status, Stack<Operand> result, String errorMessage) {
        this.status = status;
        this.result = result;
        this.errorMessage = ofNullable(errorMessage);
    }
}
