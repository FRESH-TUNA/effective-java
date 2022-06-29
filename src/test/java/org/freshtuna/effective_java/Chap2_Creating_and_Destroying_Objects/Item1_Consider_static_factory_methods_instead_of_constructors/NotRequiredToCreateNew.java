package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item1_Consider_static_factory_methods_instead_of_constructors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotRequiredToCreateNew {
    @DisplayName("정적 팩토리메소드는 반드시 객체를 생성할 필요가 없다.")
    @Test
    public void test() {
        Boolean a = Boolean.valueOf(false);
        Boolean b = Boolean.valueOf(false);

        assertThat(a).isSameAs(b);
    }
}
