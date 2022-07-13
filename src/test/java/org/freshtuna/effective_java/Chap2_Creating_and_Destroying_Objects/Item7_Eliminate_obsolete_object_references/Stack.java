package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item7_Eliminate_obsolete_object_references;

import java.util.Arrays;

public class Stack {
    private static int size = 10;
    private static int head = -1;

    private static Object[] datas = new Object[size];

    private static void resize() {
        size *= 2;
        datas = Arrays.copyOf(datas, size);
    }

    /**
     * pop시에 레퍼런스를 해체해줘야 한다.
     * pop이 되었지만 레퍼런스를 가지고 있어 Object에 대한 메모리 해제가 안된다.
     * 다 쓴 객체에 대한 참조를 해제하지 않으면 가비지 컬렉션의 대상이 되지 않아 계속 메모리가 할당 되는 메모리 누수현상이 발생 된다.
     * @return
     */
    /**
     * 하지만 직접 null로 설정하는것은 예외적인 상황이다.
     * 가장 좋은 방법은 scope를 벗어날때 변수가 떨어져나가면서 참조가 없어지는것이다.
     * 새로운 객체를 생성(heap 메모리 관리)하게 되면 memory leak에 항상 유의하도록 하자
     * @return
     */
    private static Object pop() {
        if(head == -1)
            return null;

        Object data = datas[head];
        datas[head] = null;
        head -= 1;
        return data;
    }

    private static void push(Object data) {
        head += 1;

        if(size == head);
        resize();
        datas[head] = data;
    }
}
