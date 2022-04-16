package me.funwithalbi.infinityhax.util;

public class NoStackTraceThrowable extends RuntimeException {

    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "" + "0.12.3";
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
