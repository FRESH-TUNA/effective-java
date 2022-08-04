package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals.Item11_Always_override_hashCode_when_you_override_equals;

public class Item11 {
    /**
     * 되도록 모든데이터에 대해 hash function을 작성한다.
     * equals를 재정의했다면 hash function도 재정의 해야 한다.
     * equals == true 이면 hashcode도 일치해야 한다.
     *
     * hashcode는 hashmap, hashset등에 사용된다.
     */
    private class HashCodeClass {
        int areaCode;
        String name;
        int[] hohoho;

        @Override public int hashCode() {
            int result = Integer.hashCode(areaCode);

            result = 5 * result + name.hashCode();

            /**
             * multiflier 에는 홀수를 사용하는것이 좋다. 그리고 shift 연산으로 jvm에 최적화의 여지를 주는것도 좋다.
             */
            for (int x : hohoho) {
                result = (result << 2) + Integer.hashCode(x) + result;
            }
            return result;
        }
    }


    /**
     * hash function이 오래걸리고
     * 여러번 사용될 가능성이 있다면 caching 하는것도 좋다.
     * lazy caching 의 경우 쓰레드 안전에 유의한다.
     */
    private class LazyCaching {
        int areaCode;
        String name;
        int[] hohoho;

        private int hashcode = 0;

        @Override public int hashCode() {
            if (hashcode != 0)
                return hashcode;

            int result = Integer.hashCode(areaCode);

            result = 5 * result + name.hashCode();

            /**
             * multiflier 에는 홀수를 사용하는것이 좋다. 그리고 shift 연산으로 jvm에 최적화의 여지를 주는것도 좋다.
             */
            for (int x : hohoho) {
                result = (result << 2) + Integer.hashCode(x) + result;
            }
            hashcode = result
            return result;
        }
    }
}
