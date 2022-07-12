package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item6_Avoid_creating_unnecessary_objects;

/**
 * 오브젝트 생성 비용은 크다.
 * 되도록이면 primitive 타입을 써주는것이 좋다.
 */
public class BadAutoBoxing {
    private long sum(int... data) {
        Long sum = 0L;

        for (int x : data) {
            sum += x;
        }
        return sum;
    }
}
