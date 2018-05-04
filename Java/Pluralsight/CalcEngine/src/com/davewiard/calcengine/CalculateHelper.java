package com.davewiard.calcengine;

public class CalculateHelper {
    private static final char ADD_SYMBOL = '+';
    private static final char SUBTRACT_SYMBOL = '-';
    private static final char MULTIPLY_SYMBOL = '*';
    private static final char DIVIDE_SYMBOL = '/';

    MathCommand command;
    double leftVal;
    double rightVal;
    double result;

    @Override
    public String toString() {
        // example: 1.0 + 2.0 = 3.0
        char symbol = ' ';
        switch (this.command) {
            case Add:
                symbol = ADD_SYMBOL;
                break;
            case Divide:
                symbol = DIVIDE_SYMBOL;
                break;
            case Multiply:
                symbol = MULTIPLY_SYMBOL;
                break;
            case Subtract:
                symbol = SUBTRACT_SYMBOL;
                break;
        }

        StringBuilder sb = new StringBuilder(20);
        sb.append(this.leftVal);
        sb.append(' ');
        sb.append(symbol);
        sb.append(' ');
        sb.append(this.rightVal);
        sb.append(" = ");
        sb.append(result);

        return sb.toString();
    }

    public void process(String statement) throws InvalidStatementException {
        // example: add 1.0 2.0
        String parts[] = statement.split(" ");
        if (parts.length != 3) {
            throw new InvalidStatementException("Incorrect number of fields", statement);
        }

        String commandString = parts[0];
        try {
            this.leftVal = Double.parseDouble(parts[1]);    // 1.0
            this.rightVal = Double.parseDouble(parts[2]);   // 2.0
        } catch (NumberFormatException e) {
            throw new InvalidStatementException("Non-numeric data", statement, e);
        }

        setCommandFromString(commandString);
        if (command == null) {
            throw new InvalidStatementException("Invalid command", statement);
        }

        CalculateBase calculator = null;
        switch (this.command) {
            case Add:
                calculator = new Adder(this.leftVal, this.rightVal);
                break;
            case Subtract:
                calculator = new Subtracter(this.leftVal, this.rightVal);
                break;
            case Divide:
                calculator = new Divider(this.leftVal, this.rightVal);
                break;
            case Multiply:
                calculator = new Multiplier(this.leftVal, this.rightVal);
                break;
        }

        calculator.calculate();
        this.result = calculator.getResult();
    }

    private void setCommandFromString(String commandString) {
        // example: "add" -> MathCommand.Add
        if (commandString.equalsIgnoreCase(MathCommand.Add.toString())) {
            this.command = MathCommand.Add;
        } else if (commandString.equalsIgnoreCase(MathCommand.Subtract.toString())) {
            this.command = MathCommand.Subtract;
        } else if (commandString.equalsIgnoreCase(MathCommand.Multiply.toString())) {
            this.command = MathCommand.Multiply;
        } else if (commandString.equalsIgnoreCase(MathCommand.Divide.toString())) {
            this.command = MathCommand.Divide;
        }
    }
}
