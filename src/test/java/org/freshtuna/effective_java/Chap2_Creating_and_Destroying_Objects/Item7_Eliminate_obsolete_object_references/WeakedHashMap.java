package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item7_Eliminate_obsolete_object_references;

import java.util.WeakHashMap;

/**
 * thanks to
 * 약한참조를 사용하므로 키값이 Null이 되면 다음 GC때 메모리가 해제된다.
 * https://blog.breakingthat.com/2018/08/26/java-collection-map-weakhashmap/
 */
public class WeakedHashMap {
    public static void main(String[] args) {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key1 = 1000;
        Integer key2 = 2000;
        map.put(key1, "1000");
        map.put(key2, "2000");

        key1 = null;

        /**
         * 강제 Garbage Collection
         */
        System.gc();
        map.entrySet().stream().forEach(el -> System.out.println(el));
    }
}
