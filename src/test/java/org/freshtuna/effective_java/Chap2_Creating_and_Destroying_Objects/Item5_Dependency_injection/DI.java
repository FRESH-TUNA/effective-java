package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item5_Dependency_injection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DI {

    private interface Discounter {
        Long discount(Long cost);
    }

    private static class PersentDiscounter implements Discounter {
        @Override
        public Long discount(Long cost) {
            return (long) (cost * 0.9);
        }
    }

    private static class Service {
        private final Discounter discounter;

        /**
         * Discounter 객체를 직접 생성하지않고
         * 다른 시스템에 구현체의 선택을 위임한다.
         */
        public Service(Discounter discounter) {
            this.discounter = discounter;
        }

        public Long cost(Long cost) {
            return discounter.discount(cost);
        }
    }

    @Test
    @DisplayName("정률할인")
    public void test() {
        Long cost = 10L;

        /**
         * client에서 구현체를 주입한다.
         */
        Service service = new Service(new PersentDiscounter());
        assertThat(service.cost(cost)).isEqualTo(9L);
    }
}
