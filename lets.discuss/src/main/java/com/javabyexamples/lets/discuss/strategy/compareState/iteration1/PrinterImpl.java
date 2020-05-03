package com.javabyexamples.lets.discuss.strategy.compareState.iteration1;

public class PrinterImpl implements Printer {

    private final String value;

    public PrinterImpl(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println(value);
    }
}
