package com.javabyexamples.java.mapper.mapstruct.nested.deepmap;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.nested.ClassA;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassADto;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassB;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassC;
import org.junit.jupiter.api.Test;

class DeepNestedMapperTest {

    private final DeepNestedMapper mapper = DeepNestedMapper.INSTANCE;

    @Test
    public void testDeepMap() {
        ClassC c = new ClassC("c");
        ClassB b = new ClassB("b", c);
        ClassA a = new ClassA("a", b);

        final ClassADto deepMapped = mapper.deepMap(a);

        assertThat(deepMapped.getPropertyA()).isEqualTo(a.getPropertyA());
        assertThat(deepMapped.getChild().getPropertyB()).isEqualTo(b.getPropertyB());
        assertThat(deepMapped.getChild().getChild().getPropertyC()).isEqualTo(c.getPropertyC());
    }
}
