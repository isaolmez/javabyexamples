package com.javabyexamples.java.tools.lombok.annotations.val;

import java.util.ArrayList;
import lombok.val;

public class ValUsage {

    public static void main(String[] args) {
        ValUsage valUsage = new ValUsage();
        valUsage.basicUsage();
        valUsage.usageWithCompoundType(true);
        valUsage.usageWithCompoundType(false);
    }

    public void basicUsage() {
        val lessons = new ArrayList<String>();
        lessons.add("Math");
        lessons.add("History");
        for (val item : lessons) {
            System.out.println(item);
        }
    }

    public void cannotReassign() {
        val name = "John";
        // name = "Jack"; // java: cannot assign a value to final variable name
    }

    public void usageWithCompoundType(boolean selectCat) {
        val cat = new Cat();
        System.out.println(cat.getClass());

        val catOrDog = selectCat ? new Cat() : new Dog();
        System.out.println(catOrDog.getClass());
    }

    public interface Animal {

    }

    public class Cat implements Animal {

    }

    public class Dog implements Animal {

    }
}
