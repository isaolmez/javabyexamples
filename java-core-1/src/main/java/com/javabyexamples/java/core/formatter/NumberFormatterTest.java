package com.javabyexamples.java.core.formatter;

public class NumberFormatterTest {

    public static void main(String[] args) {
        NumberFormatterTest formatter = new NumberFormatterTest();
        formatter.integral();
        formatter.octal();
        formatter.hex();
        formatter.floatingPoint();
    }

    public void integral() {
        System.out.println("###### Integral");
        System.out.format("' %d '%n", 12);
        System.out.format("' %1$d '%n", 12);
        System.out.format("' %1$d %1$d '%n", 12);

        System.out.format("' %10d '%n", 12);
        System.out.format("' %-10d '%n", 12);
        System.out.format("' %010d '%n", 12);

        System.out.format("' %,d '%n", 12000);
        System.out.format("' %+,d '%n", 12000);
    }

    public void octal() {
        System.out.println("###### Octal");
        System.out.format("' %o '%n", 12);
        System.out.format("' %#o '%n", 12);
    }

    public void hex() {
        System.out.println("###### Hex");
        System.out.format("' %x '%n", 12);
        System.out.format("' %X '%n", 12);
        System.out.format("' %#X '%n", 12);
    }

    public void floatingPoint() {
        System.out.println("###### Floating Point");
        System.out.format("' %f '%n", 12.56789);
        System.out.format("' %2$f '%n", 9.99, 12.56789);
        System.out.format("' %1$f %1$f '%n", 12.56789);

        System.out.format("' %f '%n", 12.56789);
        System.out.format("' %.5f '%n", 12.56789);
        System.out.format("' %.3f '%n", 12.56789);

        System.out.format("' %5f '%n", 12.56789);
        System.out.format("' %20f '%n", 12.56789);
        System.out.format("' %-20f '%n", 12.56789);
        System.out.format("' %020f '%n", 12.56789);

        System.out.format("' %,f '%n", 12000.56789);
        System.out.format("' %+,f '%n", 12000.56789);
    }
}
