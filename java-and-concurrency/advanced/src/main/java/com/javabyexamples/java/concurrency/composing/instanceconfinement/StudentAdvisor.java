package com.javabyexamples.java.concurrency.composing.instanceconfinement;

import java.util.ArrayList;
import java.util.List;

public class StudentAdvisor {

    private final Student student;

    public StudentAdvisor(Student student) {
        this.student = student;
    }

    public synchronized void addLesson(String lesson) {
        student.lessons.add(lesson);
    }

    public synchronized boolean containsLesson(String lesson) {
        return student.lessons.contains(lesson);
    }

    public static class Student {

        public final List<String> lessons = new ArrayList<>();
    }
}

