package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item7_Eliminate_obsolete_object_references;

import java.util.HashMap;
import java.util.Map;


/**
 * thanks to
 * https://blog.breakingthat.com/2018/08/26/java-collection-map-weakhashmap/
 */
public class LeakedHashMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Integer key1 = 1000;
        Integer key2 = 2000;
        map.put(key1, "test a");
        map.put(key2, "test b");

        key1 = null;

        /**
         * 강제 Garbage Collection
         */
        System.gc();  //강제 Garbage Collection
        map.entrySet().stream().forEach(el -> System.out.println(el));
    }
}
