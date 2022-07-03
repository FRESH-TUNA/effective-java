package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item4_Enforce_noninstantiability_with_a_private_constructor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * item3에서 설명한것처럼
 * 생성자를 따로 만들지 않으면 기본 생성자가만들어지므로 private 기본 생성자를 만들어 막는다.
 *
 * 하지만 reflection 공격에 취약하으로, 단순한 유틸클래스라면 exception을 추가해준다.
 *
 */
public class AddToException {
    private static class Util {
        private Util() throws IllegalAccessException {
            throw new IllegalAccessException("하용되지 않은 접근");
        }

        public static int getZero() {
            return 1;
        }
    }

    @Test
    @DisplayName("reflection 시도시 exception이 발생한다.")
    public void test() throws
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        // given
        Constructor<Util> constructor = Util.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // then
        assertThatThrownBy(() -> {
            constructor.newInstance();
        }).isInstanceOf(ReflectiveOperationException.class);
    }
}
