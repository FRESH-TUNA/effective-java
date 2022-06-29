package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item2_Consider_a_builder_when_faced_with_many_constructor_parameters;

/**
 * setter를 사용하는 JavaBeanPattern
 * 클래스내의 값이 바뀌므로 consistency(일관성)가 없다.
 * 따라서 멀티쓰레드 상황에 주의 해야한다.
 * 이를 해결하기 위해 객체 생성이 완벽히 끝나면, freeze를 걸어 수정을 하려할때 예외를 발생시키는 기법도 있지만 선호되지 않는다.
 */
public class JavaBeanPattern {
    private static class NutritionFacts {
        private int calories;
        private int fat;
        private int natrium;
        private int carbohydrate;

        public NutritionFacts() {
            this.calories = 0;
            this.fat = 0;
            this.natrium = 0;
            this.carbohydrate = 0;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public void setNatrium(int natrium) {
            this.natrium = natrium;
        }

        public void setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
        }
    }

    /**
     * 이를 해결하기 위해 객체 생성이 완벽히 끝나면, freeze를 걸어 수정을 하려할때 예외를 발생시키는 기법도 있지만 선호되지 않는다.
     * 이코드도 멀티쓰레드관점에서 위험하다.
     */
    private static class NutritionFactsMyFreeze {
        private int calories;
        private int carbohydrate;
        private boolean freeze;

        public NutritionFactsMyFreeze() {
            this.calories = 0;
            this.carbohydrate = 0;
            this.freeze = false;
        }

        public void setCalories(int calories) {
            if (freeze)
                throw new RuntimeException();
            this.calories = calories;
        }

        public void setCarbohydrate(int carbohydrate) {
            if (freeze)
                throw new RuntimeException();
            this.carbohydrate = carbohydrate;
        }

        public void freeze() {
            this.freeze = true;
        }
    }

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts();
        nutritionFacts.setCalories(10000);
        nutritionFacts.setCarbohydrate(200);
        nutritionFacts.setNatrium(200);
        nutritionFacts.setCarbohydrate(400);
    }
}
