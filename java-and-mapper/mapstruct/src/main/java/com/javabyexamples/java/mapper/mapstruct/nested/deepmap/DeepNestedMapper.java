package com.javabyexamples.java.mapper.mapstruct.nested.deepmap;

import com.javabyexamples.java.mapper.mapstruct.nested.ClassA;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassADto;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassB;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassBDto;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassC;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassCDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeepNestedMapper {

    DeepNestedMapper INSTANCE = Mappers.getMapper(DeepNestedMapper.class);

    ClassADto deepMap(ClassA source);

    ClassBDto deepMap(ClassB source);

    ClassCDto deepMap(ClassC source);
}
