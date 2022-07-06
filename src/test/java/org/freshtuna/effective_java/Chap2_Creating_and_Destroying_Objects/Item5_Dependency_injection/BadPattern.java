package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item5_Dependency_injection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BadPattern {
    private interface Discounter {
        Long discount(Long cost);
    }

    private static class PersentDiscounter implements Discounter {
        @Override
        public Long discount(Long cost) {
            return (long) (cost * 0.9);
        }
    }

    /**
     * Discounter 객체를 직접 생성해주는것은
     * 개방패쇄원칙 을 위배한다.
     */
    private static final Discounter discounter = new PersentDiscounter();

    @Test
    @DisplayName("정률할인")
    public void test() {
        Long cost = 10L;
        assertThat(BadPattern.discounter.discount(cost)).isEqualTo(9L);
    }
}
