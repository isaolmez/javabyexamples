package com.javabyexamples.java.mapper.mapstruct.collection.primitive;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListMapper {

    ListMapper INSTANCE = Mappers.getMapper(ListMapper.class);

    List<String> map(List<Integer> grades);

}
