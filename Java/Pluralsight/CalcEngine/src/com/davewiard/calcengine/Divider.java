package com.davewiard.calcengine;

public class Divider extends CalculateBase {
    public Divider() {}

    public Divider(double leftVal, double rightVal) {
        super(leftVal, rightVal);
    }

    @Override
    public void calculate() {
        if (getRightVal() == 0.0d) {
            System.out.println("Cannot divide by zero!");
            return;
        }

        double value = getLeftVal() / getRightVal();
        setResult(value);
    }
}
