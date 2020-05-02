package com.javabyexamples.java.test.testng;

import com.google.common.base.Preconditions;
import java.util.TreeMap;

public class SimpleGrader implements Grader {

    private static final TreeMap<Integer, String> GRADES;

    static {
        GRADES = new TreeMap<>();
        GRADES.put(50, "F");
        GRADES.put(60, "D");
        GRADES.put(70, "C");
        GRADES.put(85, "B");
        GRADES.put(100, "A");
    }

    @Override
    public String grade(int grade) {
        Preconditions.checkArgument(grade >= 0 && grade <= 100);
        return GRADES.ceilingEntry(grade).getValue();
    }
}
