package com.agh.technology.xp.lab2.common.library.exception;

public class DebugException extends Exception{
    public DebugException(String message, Throwable cause) {
        super(message, cause);
    }

    public DebugException(String message) {
        super(message);
    }
}
