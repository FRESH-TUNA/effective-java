package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item2_Consider_a_builder_when_faced_with_many_constructor_parameters;

/**
 * 파라미터가 많은 생성자를 위해
 * TelescopedConstructorPattern 을 사용했다.
 * 필수적인 인자에 생성자를 만들고, 다른 선택 인자를 받아 객체를 생성해주는 생성자를 만들어준다.
 * 필수 인자를 받는 생성자는 필수인자를 받은후, 선택인자를 받는 생성자로 delegate한다.
 * 하지만 확장에 어렵고
 * 생성자 사용시 들어가는 인자가 어떤 역활을 하는지(이게 칼로리인지 나트륨인지) 불명확하다.
 */
public class TelescopedConstructorPattern {
    private static class NutritionFacts {
        private final int calories;
        private final int fat;
        private final int natrium;
        private final int carbohydrate;

        public NutritionFacts(int calories) {
            this(calories, 0);
        }

        public NutritionFacts(int calories, int fat) {
            this(calories, fat, 0);
        }

        public NutritionFacts(int calories, int fat, int sodium) {
            this(calories, fat, sodium, 0);
        }

        public NutritionFacts(int calories, int fat, int sodium, int carbohydrate) {
            this.calories = calories;
            this.fat = fat;
            this.natrium = sodium;
            this.carbohydrate = carbohydrate;
        }
    }

    public static void main(String[] args) {
        /**
         * 인자들이 어떤 역활을 하는지 불명확하다.
         */
        new NutritionFacts(10000);
        new NutritionFacts(10000, 400, 500, 300);
    }
}
