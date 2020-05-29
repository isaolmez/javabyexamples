package com.javabyexamples.java.mapper.mapstruct.collection.primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ListMapperTest {

    private final ListMapper mapper = ListMapper.INSTANCE;

    @Test
    public void testMap() {
        final List<String> mappedList = mapper.map(Arrays.asList(1, 2, 3, 4, 5));

        assertThat(mappedList).containsExactly("1", "2", "3", "4", "5");
    }
}
