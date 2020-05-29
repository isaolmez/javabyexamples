package com.javabyexamples.java.mapper.mapstruct.nested.deepcopy;

import static org.assertj.core.api.Assertions.assertThat;

import com.javabyexamples.java.mapper.mapstruct.nested.ClassA;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassB;
import com.javabyexamples.java.mapper.mapstruct.nested.ClassC;
import org.junit.jupiter.api.Test;

class DeepCopyNestedMapperTest {

    private final DeepCopyNestedMapper mapper = DeepCopyNestedMapper.INSTANCE;

    @Test
    public void testDeepCopy() {
        ClassC c = new ClassC("c");
        ClassB b = new ClassB("b", c);
        ClassA a = new ClassA("a", b);

        final ClassA deepCopy = mapper.deepCopy(a);

        assertThat(deepCopy.getPropertyA()).isEqualTo(a.getPropertyA());

        assertThat(deepCopy.getChild() != b).isTrue();
        assertThat(deepCopy.getChild().getPropertyB()).isEqualTo(b.getPropertyB());

        assertThat(deepCopy.getChild().getChild() != c).isTrue();
        assertThat(deepCopy.getChild().getChild().getPropertyC()).isEqualTo(c.getPropertyC());
    }
}
