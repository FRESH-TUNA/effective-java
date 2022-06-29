package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item2_Consider_a_builder_when_faced_with_many_constructor_parameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 빌더 패턴
 * 파라미터의 이름을 가진 함수를 사용한다.
 * 유연하지만, 객체 생성을 위해서 빌더를 무조건 생성해야 하기때문에 조금 비효율적이다.
 * 그럼에도 불구하고 빌더패턴은 가장 좋은 선택을 받고 있다.
 */
public class BuilderPattern {
    public static class Platform {
        private final String name;
        private final Long id;
        private final String link;

        public static class Builder {
            private final String name;
            private Long id;
            private String link;

            /**
             * 'return this' 를 통해 객체를 계속 전파하면서 쓸수 있다.
             * ex) Builder.calories(100).sodium(300)
             * @return this
             */
            public Builder id(Long id) {
                this.id = id;
                return this;
            }
            public Builder link(String link) {
                this.link = link;
                return this;
            }

            /**
             * 필수 인자를 통해 빌더를 생성한다.
             * @param name
             */
            private Builder(String name) {
                this.name = name;
                this.id = null;
                this.link = null;
            }

            public Platform build() {
                return new Platform(this);
            }
        }

        public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public static Builder builder(String name) {
            return new Builder(name);
        }

        private Platform(Builder builder) {
            this.name = builder.name;
            this.id = builder.id;
            this.link = builder.link;
        }
    }

    @Test
    public void test() {
        Platform platform = Platform.builder("백준").link("link").build();
        assertThat(platform.getName()).isEqualTo("백준");
        assertThat(platform.getLink()).isEqualTo("link");
    }

    @Test
    @DisplayName("하나의 빌더로 여러개의 인스턴스를 만들수 있다.")
    public void createMulti() {
        Platform.Builder platform = Platform.builder("백준").link("link");

        Platform platform1 = platform.build();
        Platform platform2 = platform.build();

        assertThat(platform1).isNotSameAs(platform2);
    }
}
