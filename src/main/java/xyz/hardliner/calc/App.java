package xyz.hardliner.calc;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.Addition;
import xyz.hardliner.calc.operators.Subtraction;

public class App {

    public static void main(String[] args) {
        var stack = new StackWithHistory();
        stack
            .push(new NumericOperand(5))
            .push(new NumericOperand(11))
            .push(new Addition())
            .push(new NumericOperand(100))
            .push(new Subtraction());
        System.out.println(stack.print());
        System.out.println(stack.printHistory());
    }

}
