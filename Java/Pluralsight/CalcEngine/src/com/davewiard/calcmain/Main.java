package com.davewiard.calcmain;


import com.davewiard.calcengine.Adder;
import com.davewiard.calcengine.CalculateBase;
import com.davewiard.calcengine.CalculateHelper;
import com.davewiard.calcengine.Divider;
import com.davewiard.calcengine.DynamicHelper;
import com.davewiard.calcengine.InvalidStatementException;
//import com.davewiard.calcengine.MathEquation;
import com.davewiard.calcengine.MathProcessing;
import com.davewiard.calcengine.Multiplier;
import com.davewiard.calcengine.PowerOf;
import com.davewiard.calcengine.Subtracter;

public class Main {

    public static void main(String[] args) {
//        useMathEquation();
//        useCalculatorBase();
//        useCalculatorHelper();

        String[] statements = {
                "add 25.0 92.0",        // 25.0 + 92.0 = 117.0
                "power 5.0 2.0",        // 5.0 ^ 2.0 = 25.0
        };

        System.out.println();
        System.out.println("Using Interface :: DynamicHelper");
        System.out.println();
        DynamicHelper helper = new DynamicHelper(new MathProcessing[] {
                new Adder(),
                new PowerOf()
        });
        for (String statement:statements) {
            String output = helper.process(statement);
            System.out.println(output);
        }
    }

    static void useCalculatorHelper() {
        String[] statements = {
            "add 1.0",                  // Error incorrect number of values
            "add xx 25.0",              // Error: non-numeric data
            "addX 0.0 0.0",             // Error: invalid command
            "divide 100.0 50.0",        // 100.0 / 50.0 = 2.0
            "add 25.0 92.0",            // 25.0 + 92.0 = 117.0
            "subtract 225.0 17.0",      // 225.0 - 17.0 = 208.0
            "multiply 11.0 3.0"         // 11.0 * 3.0 = 33.0
        };

        System.out.println();
        System.out.println("Using Abstract Class :: CalculatorHelper");
        System.out.println();
        CalculateHelper helper = new CalculateHelper();
        for (String statement:statements) {
            try {
                helper.process(statement);
                System.out.println(helper);
            } catch (InvalidStatementException e) {
                System.out.println(e.getMessage());
                if (e.getCause() != null) {
                    System.out.println(e.getCause().getMessage());
                }
            }
        }
    }

    /*
    static void useMathEquation() {
        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation(100.d, 50.0d, 'd');
        equations[1] = new MathEquation(25.0d, 92.0d, 'a');
        equations[2] = new MathEquation(225.0d, 17.0d, 's');
        equations[3] = new MathEquation(11.0d, 3.0d, 'm');

        System.out.println();
        System.out.println("Using One Class, No Inheritance");
        System.out.println();

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.print("result = ");
            System.out.println(equation.getResult());
        }
    }
    */

    static void useCalculatorBase() {
        System.out.println();
        System.out.println("Using Inheritance");
        System.out.println();

        CalculateBase[] calculators = {
                new Divider(100.0d, 50.d),
                new Adder(25.0d, 92.0d),
                new Subtracter(225.0d, 17.0d),
                new Multiplier(11.0d, 3.0d)
        };

        for (CalculateBase calculator : calculators) {
            calculator.calculate();
            System.out.print("result = ");
            System.out.println(calculator.getResult());
        }
    }
}
