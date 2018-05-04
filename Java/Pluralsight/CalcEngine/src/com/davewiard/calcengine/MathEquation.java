package com.davewiard.calcengine;

public class MathEquation {
    private double leftVal;
    private double rightVal;
    private char opCode;
    private double result;

    MathEquation() {}

    MathEquation(char opCode) {
        this.opCode = opCode;
    }

    public MathEquation(double leftVal, double rightVal, char opCode) {
        this(opCode);
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }

    public double getResult() {
        return this.result;
    }

    public void execute(double leftVal, double rightVal) {
        this.leftVal = leftVal;
        this.rightVal = rightVal;
        execute();
    }

    public void execute() {
        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;

            case 'd':
                result = (rightVal != 0) ? leftVal / rightVal : 0.0d;
                break;

            case 'm':
                result = leftVal * rightVal;
                break;

            case 's':
                result = leftVal - rightVal;
                break;

            default:
                System.out.println("Error - invalid opcode");
                result = 0.0d;
                break;
        }
    }
}
