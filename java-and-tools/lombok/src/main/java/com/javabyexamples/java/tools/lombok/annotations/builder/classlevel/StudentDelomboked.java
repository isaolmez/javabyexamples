package com.javabyexamples.java.tools.lombok.annotations.builder.classlevel;

public class StudentDelomboked {

    private String name;
    private int age;

    StudentDelomboked(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private String name;
        private int age;

        StudentBuilder() {
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        public StudentDelomboked build() {
            return new StudentDelomboked(name, age);
        }

        public String toString() {
            return "Student.StudentBuilder(name=" + this.name + ", age=" + this.age + ")";
        }
    }
}
