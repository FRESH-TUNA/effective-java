package org.freshtuna.effective_java.ChapX_etc.literal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class test {
    @Test
    @DisplayName("같은 리터럴의 경우 같은 참조를 가진다.")
    public void test() {
        // 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.Integer1).isSameAs(ClassA.Integer2);

        // 다른 객체라도 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.Integer1).isSameAs(ClassB.Integer2);

        // 다른 객체라도 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.Integer1).isSameAs(ClassB.ClassC.Integer2);

        // 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.String1).isSameAs(ClassA.String2);

        // 다른 객체라도 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.String1).isSameAs(ClassB.String2);

        // 다른 객체라도 같은 리터럴의 경우 같은 pool에 있는 객체를 참조한다.
        assertThat(ClassA.String1).isSameAs(ClassB.ClassC.String2);

        // 평가식이 다르더라도 그렇다.
        assertThat(ClassD.Integer1).isSameAs(ClassD.Integer2);
        assertThat(ClassD.String1).isSameAs(ClassD.String2);
    }

    @Test
    @DisplayName("리터럴간의 연산을 통해 얻은 결과도 풀에 캐싱된다.")
    public void test2() {
        Integer d = ClassD.Integer1 + ClassD.Integer2;
        String s = ClassD.String1 + ClassD.String2;

        assertThat(d).isSameAs(ClassD.Integer3);
        assertThat(s).isSameAs(ClassD.String3);
    }
}
