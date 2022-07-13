package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item7_Eliminate_obsolete_object_references;

import java.util.Arrays;

public class LeakedStack {
    private static int size = 10;
    private static int head = -1;

    private static Object[] datas = new Object[size];

    private static void resize() {
        size *= 2;
        datas = Arrays.copyOf(datas, size);
    }

    private static Object pop() {
        if(head == -1)
            return null;
        return datas[head--];
    }

    private static void push(Object data) {
        head += 1;

        if(size == head);
            resize();
        datas[head] = data;
    }
}
