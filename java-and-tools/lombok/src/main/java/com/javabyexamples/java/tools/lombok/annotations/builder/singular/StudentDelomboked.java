package com.javabyexamples.java.tools.lombok.annotations.builder.singular;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class StudentDelomboked {

    private String name;
    private int age;
    private Set<String> lessons;

    StudentDelomboked(String name, int age, Set<String> lessons) {
        this.name = name;
        this.age = age;
        this.lessons = lessons;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {

        private String name;
        private int age;
        private ArrayList<String> lessons;

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

        public StudentBuilder lesson(String lesson) {
            if (this.lessons == null) {
                this.lessons = new ArrayList<String>();
            }
            this.lessons.add(lesson);
            return this;
        }

        public StudentBuilder lessons(Collection<? extends String> lessons) {
            if (this.lessons == null) {
                this.lessons = new ArrayList<String>();
            }
            this.lessons.addAll(lessons);
            return this;
        }

        public StudentBuilder clearLessons() {
            if (this.lessons != null) {
                this.lessons.clear();
            }

            return this;
        }

        public StudentDelomboked build() {
            Set<String> lessons;
            switch (this.lessons == null ? 0 : this.lessons.size()) {
                case 0:
                    lessons = java.util.Collections.emptySet();
                    break;
                case 1:
                    lessons = java.util.Collections.singleton(this.lessons.get(0));
                    break;
                default:
                    lessons = new java.util.LinkedHashSet<String>(
                      this.lessons.size() < 1073741824 ? 1 + this.lessons.size() + (this.lessons.size() - 3) / 3 : Integer.MAX_VALUE);
                    lessons.addAll(this.lessons);
                    lessons = java.util.Collections.unmodifiableSet(lessons);
            }

            return new StudentDelomboked(name, age, lessons);
        }

        public String toString() {
            return "Student.StudentBuilder(name=" + this.name + ", age=" + this.age + ", lessons=" + this.lessons + ")";
        }
    }
}
