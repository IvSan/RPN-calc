package xyz.hardliner.calc;

import xyz.hardliner.calc.operands.NumericOperand;
import xyz.hardliner.calc.operators.math.Addition;
import xyz.hardliner.calc.operators.math.Division;
import xyz.hardliner.calc.operators.math.Multiplication;
import xyz.hardliner.calc.operators.math.SquareRoot;
import xyz.hardliner.calc.operators.math.Subtraction;
import xyz.hardliner.calc.operators.special.Undo;

public class App {

    public static void main(String[] args) {
        var calc = new Calculator();
        calc
            .process(new NumericOperand(5))
            .process(new NumericOperand(11))
            .process(new Addition())
            .process(new NumericOperand(100))
            .process(new Subtraction())
            .process(new NumericOperand(20))
            .process(new Division())
            .process(new NumericOperand(9))
            .process(new SquareRoot())
            .process(new Multiplication())
            .process(new Undo())
        ;

        System.out.println(calc.print());
        System.out.println(calc.printHistory());
    }

}
