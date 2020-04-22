package com.javabyexamples.java.tools.lombok.annotations.var;

import java.util.ArrayList;
import lombok.var;

public class VarUsage {

    public static void main(String[] args) {
        VarUsage varUsage = new VarUsage();
        varUsage.basicUsage();
        varUsage.canReassign();
        varUsage.usageWithCompoundType(true);
        varUsage.usageWithCompoundType(false);
    }

    public void basicUsage() {
        var lessons = new ArrayList<String>();
        lessons.add("Math");
        lessons.add("History");
        for (var item : lessons) {
            System.out.println(item);
        }
    }

    public void canReassign() {
        var name = "John";
        name = "Jack";
        System.out.println(name);
    }

    public void cannotChangeType() {
        var name = "John";
        // name = 12; // java: incompatible types: int cannot be converted to java.lang.String
    }

    public void usageWithCompoundType(boolean selectCat) {
        var cat = new Cat();
        System.out.println(cat.getClass());

        var catOrDog = selectCat ? new Cat() : new Dog();
        System.out.println(catOrDog.getClass());
    }

    public interface Animal {

    }

    public class Cat implements Animal {

    }

    public class Dog implements Animal {

    }
}
