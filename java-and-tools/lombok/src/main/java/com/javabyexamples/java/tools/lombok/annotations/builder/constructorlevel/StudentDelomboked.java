package com.javabyexamples.java.tools.lombok.annotations.builder.constructorlevel;

public class StudentDelomboked {

    private String name;
    private int age;
    private String section;
    private String school;

    public StudentDelomboked(String section, String school) {
        this.section = section;
        this.school = school;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private String section;
        private String school;

        StudentBuilder() {
        }

        public StudentBuilder section(String section) {
            this.section = section;
            return this;
        }

        public StudentBuilder school(String school) {
            this.school = school;
            return this;
        }

        public StudentDelomboked build() {
            return new StudentDelomboked(section, school);
        }

        public String toString() {
            return "Student.StudentBuilder(section=" + this.section + ", school=" + this.school + ")";
        }
    }
}
