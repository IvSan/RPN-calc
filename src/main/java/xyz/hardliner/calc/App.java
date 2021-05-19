package xyz.hardliner.calc;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Division;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;

public class App {

    public static void main(String[] args) {
        var stack = new StackWithHistory();
        stack
            .push(new NumericOperand(5))
            .push(new NumericOperand(11))
            .push(new Addition())
            .push(new NumericOperand(100))
            .push(new Subtraction())
            .push(new NumericOperand(20))
            .push(new Division())
            .push(new NumericOperand(9))
            .push(new SquareRoot())
            .push(new Multiplication());
        System.out.println(stack.print());
        System.out.println(stack.printHistory());
    }

}
