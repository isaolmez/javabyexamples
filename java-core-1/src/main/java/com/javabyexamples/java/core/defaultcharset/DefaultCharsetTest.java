package com.javabyexamples.java.core.defaultcharset;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

public class DefaultCharsetTest {

    public static void main(final String[] arguments) {
        System.out.println("Default Locale:   " + Locale.getDefault());
        System.out.println("Default Charset:  " + Charset.defaultCharset());
        System.out.println("file.encoding;    " + System.getProperty("file.encoding"));
        System.out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
        System.out.println("Default Encoding: " + getEncoding());
    }

    public static String getEncoding() {
        final byte[] bytes = {'D'};
        final InputStream inputStream = new ByteArrayInputStream(bytes);
        final InputStreamReader reader = new InputStreamReader(inputStream);
        final String encoding = reader.getEncoding();
        return encoding;
    }
}
