package com.javabyexamples.java.tools.lombok.annotations.builder.methodlevel;

public class StudentDelomboked {

    private final String name;
    private final int age;
    private String section;
    private String school;

    public StudentDelomboked(String name, int age, String section, String school) {
        this.name = name;
        this.age = age;
        this.section = section;
        this.school = school;
    }

    public static StudentDelomboked createStudent(String name, int age, String school) {
        return new StudentDelomboked(name, age, school, "");
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private String name;
        private int age;
        private String school;

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

        public StudentBuilder school(String school) {
            this.school = school;
            return this;
        }

        public StudentDelomboked build() {
            return StudentDelomboked.createStudent(name, age, school);
        }

        public String toString() {
            return "Student.StudentBuilder(name=" + this.name + ", age=" + this.age + ", school=" + this.school + ")";
        }
    }
}
