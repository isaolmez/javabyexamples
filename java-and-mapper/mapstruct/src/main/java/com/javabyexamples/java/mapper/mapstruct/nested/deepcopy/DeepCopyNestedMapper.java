package com.javabyexamples.java.mapper.mapstruct.nested.deepcopy;

import com.javabyexamples.java.mapper.mapstruct.nested.ClassA;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassB;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassC;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeepCopyNestedMapper {

    DeepCopyNestedMapper INSTANCE = Mappers.getMapper(DeepCopyNestedMapper.class);

    ClassA deepCopy(ClassA source);

    ClassB deepCopy(ClassB source);

    ClassC deepCopy(ClassC source);
}
