package com.javabyexamples.java.tools.lombok.annotations.sneakythrows;

import java.io.IOException;
import java.text.ParseException;
import lombok.Lombok;
import lombok.SneakyThrows;

public class SampleService {

    @SneakyThrows({IOException.class, ParseException.class})
    public void filtersExceptions(String fileName) {
        if (fileName.startsWith("0")) {
            throw new ParseException("test", 1);
        } else {
            throw new IOException();
        }
    }

    @SneakyThrows
    public void sneakyThrowsCheckedAndSkips() {
        throw new IOException("Checked exception");
    }

    public void sneakyThrowsCheckedAndSkipsWithLombok() {
        try {
            throw new IOException("Checked exception");
        } catch (IOException e) {
            Lombok.sneakyThrow(e);
        }
    }

    public void throwsCheckedAndDeclares() throws IOException {
        throw new IOException("Checked exception");
    }

    public void throwsCheckedAndHandles() {
        try {
            throw new IOException("Checked exception");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void throwsUncheckedAndSkips() {
        throw new RuntimeException("Unchecked exception");
    }
}
