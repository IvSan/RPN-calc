package xyz.hardliner.calc;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.special.Undo;

public class App {

    public static void main(String[] args) {
        var stack = new StackWithHistory();
//        stack
//            .process(new NumericOperand(5))
//            .process(new NumericOperand(11))
//            .process(new Addition())
//            .process(new NumericOperand(100))
//            .process(new Subtraction())
//            .process(new NumericOperand(20))
//            .process(new Division())
//            .process(new NumericOperand(9))
//            .process(new SquareRoot())
//            .process(new Multiplication())
//            .process(new Undo());
//        stack
//            .process(new NumericOperand(20))
//            .process(new NumericOperand(9))
//            .process(new SquareRoot())
//            .process(new Addition())
//            .process(new Undo())
//        ;
        stack
            .process(new NumericOperand(81))
            .process(new SquareRoot())
            .process(new Undo())
//            .process(new Undo())
        ;
        System.out.println(stack.print());
        System.out.println(stack.printHistory());
    }

}
