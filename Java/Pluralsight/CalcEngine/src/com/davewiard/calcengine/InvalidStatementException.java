package com.davewiard.calcengine;

public class InvalidStatementException extends Exception {
    InvalidStatementException(String reason, String statement) {
        super(reason + ": " + statement);
    }

    InvalidStatementException(String reason, String statement, Throwable cause) {
        super(reason + ": " + statement, cause);
    }
}
