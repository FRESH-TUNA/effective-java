package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item6_Avoid_creating_unnecessary_objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 되도록 객체 생성보다는 리터럴을 쓰자
 */
public class UseLiteral {

    private static String data1 = new String("ORANGE");

    private static String data2 = "ORANGE";

    private static String data3 = "ORANGE";

    @Test
    @DisplayName("리터럴은 constant 풀을 통해 공유된다.")
    public void test() {
        assertThat(data1).isNotSameAs(data2);
        assertThat(data2).isSameAs(data3);
    }
}
