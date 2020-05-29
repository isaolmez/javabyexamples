package com.javabyexamples.java.mapper.mapstruct.nested.deepmap;

import com.javabyexamples.java.mapper.mapstruct.nested.ClassA;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassADto;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassB;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassC;

public class MapperClient {

    public static void main(String[] args) {
        ClassC c = new ClassC("c");
        ClassB b = new ClassB("b", c);
        ClassA a = new ClassA("a", b);

        ClassADto result = DeepNestedMapper.INSTANCE.deepMap(a);
        System.out.println("Mapping finished: " + result);
    }
}
