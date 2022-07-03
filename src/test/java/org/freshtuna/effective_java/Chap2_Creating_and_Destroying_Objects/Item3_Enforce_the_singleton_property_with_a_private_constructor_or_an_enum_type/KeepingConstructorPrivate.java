package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item3_Enforce_the_singleton_property_with_a_private_constructor_or_an_enum_type;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public class KeepingConstructorPrivate {
    /**
     * 생성자를 감췃지만 reflextion 라이브러리를 이용해서
     * 숨겨진 생성자, 필드, 메소드를 호출할수 있다.
     */
    @Test
    public void test() throws NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        Constructor<KeepingConstructorPrivateTestObject> constructor =
                KeepingConstructorPrivateTestObject.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        assertThat(KeepingConstructorPrivateTestObject.data).isNotSameAs(constructor.newInstance());
    }
}

