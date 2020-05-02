package com.javabyexamples.java.test.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleGraderTest {

    private final Grader grader = new SimpleGrader();

    @Before
    public void setUp() {
    }

    @Test
    public void shouldGrade_F() {
        final int grade = 25;
        final String expected = "F";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_F_ForMinimum() {
        final int grade = 0;
        final String expected = "F";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_F_ForMaximum() {
        final int grade = 50;
        final String expected = "F";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_D() {
        final int grade = 55;
        final String expected = "D";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_D_ForMinimum() {
        final int grade = 51;
        final String expected = "D";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_D_ForMaximum() {
        final int grade = 60;
        final String expected = "D";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_C() {
        final int grade = 65;
        final String expected = "C";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_C_ForMinimum() {
        final int grade = 61;
        final String expected = "C";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGrade_C_ForMaximum() {
        final int grade = 69;
        final String expected = "C";

        String actual = grader.grade(grade);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_WhenGradeIsSmallerThan0() {
        final int grade = -1;

        grader.grade(grade);

        Assert.fail("Should have thrown exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException_WhenGradeIsGreaterThan100() {
        final int grade = 200;

        grader.grade(grade);

        Assert.fail("Should have thrown exception");
    }

    // Other test methods
}
