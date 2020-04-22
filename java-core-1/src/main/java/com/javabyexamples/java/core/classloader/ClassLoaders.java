package com.javabyexamples.java.core.classloader;

//import com.sun.javafx.util.Logging;
import java.util.ArrayList;

public class ClassLoaders {

    public static void main(String[] args) {
        ClassLoaders classLoaders = new ClassLoaders();
        classLoaders.printClassLoaders();
        classLoaders.printClassLoadersWithContextClassLoader();
    }

    public void printClassLoaders() {
        System.out.println("Classloader of this class:" + ClassLoaders.class.getClassLoader());

//        System.out.println("Classloader of Logging:" + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:" + ArrayList.class.getClassLoader());
    }

    public void printClassLoadersWithContextClassLoader() {
        System.out.println("Classloader of this class:" + ClassLoaders.class.getClassLoader());

//        System.out.println("Classloader of Logging:" + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:" + ArrayList.class.getClassLoader());

        System.out.println("Context Classloader:" + Thread.currentThread().getContextClassLoader());
    }
}
