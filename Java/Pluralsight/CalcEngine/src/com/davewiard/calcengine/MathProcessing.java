package com.davewiard.calcengine;

public interface MathProcessing {
    String SEPARATOR = " ";
    String getKeyword();    // add
    char getSymbol();       // '+'
    double doCalculation(double leftVal, double rightVal);
}
