package com.davewiard.calcengine;

import com.sun.org.apache.xpath.internal.operations.Mult;

public class Multiplier extends CalculateBase {
    public Multiplier() {}

    public Multiplier(double leftVal, double rightVal) {
        super(leftVal, rightVal);
    }

    @Override
    public void calculate() {
        double value = getLeftVal() * getRightVal();
        setResult(value);
    }
}
