package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item2_Consider_a_builder_when_faced_with_many_constructor_parameters;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BuilderPatternHierachy {
    private static abstract class Hamburger {
        public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

        final Set<Topping> toppings;

        abstract static class Builder<T extends Builder<T>> {
            EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

            public T addTopping(Topping topping) {
                toppings.add(Objects.requireNonNull(topping));
                return self();
            }

            abstract Hamburger build();

            // Subclasses must override this method to return "this"
            protected abstract T self();
        }

        private Hamburger(Builder<?> builder) {
            toppings = builder.toppings.clone();
        }
    }

    private static class Woffer extends Hamburger {
        public enum TYPE {CHEEZE, SHRIMP}
        private final TYPE type;

        private Woffer(Builder builder) {
            super(builder);
            type = builder.type;
        }

        /**
         * private static class Builder extends Hamburger.Builder
         * 로하면 Woffer.Builder 주입이 안된다!!
         */
        private static class Builder extends Hamburger.Builder<Builder> {
            private TYPE type;

            @Override
            Woffer build() {
                return new Woffer(self());
            }

            public Builder type(TYPE type) {
                this.type = type;
                return this;
            }

            @Override
            protected Builder self() {
                return this;
            }
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    @Test
    public void test() {
        Woffer woffer = Woffer.builder()
                .addTopping(Hamburger.Topping.HAM)
                .addTopping(Hamburger.Topping.MUSHROOM)
                .type(Woffer.TYPE.CHEEZE)
                .build();

        assertThat(woffer.toppings.contains(Hamburger.Topping.HAM)).isEqualTo(true);
        assertThat(woffer.toppings.contains(Hamburger.Topping.MUSHROOM)).isEqualTo(true);
        assertThat(woffer.type).isEqualTo(Woffer.TYPE.CHEEZE);
    }
}
