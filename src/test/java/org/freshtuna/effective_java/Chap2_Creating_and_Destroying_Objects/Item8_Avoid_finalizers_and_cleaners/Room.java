package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item8_Avoid_finalizers_and_cleaners;

import java.lang.ref.Cleaner;


/**
 * thanks to
 * effective java 3E
 * https://madplay.github.io/post/avoid-finalizers-and-cleaners
 *
 * 실행 시점을 보장할 수 없다.
 * finalize 메서드와 마찬가지로 Cleaner 의 경우도 사용했을 때 언제 실행될 지 시점을 보장할 수 없다.
 *
 * 실행 조차 안될 수 있다.
 * 즉시 실행이 안되는 것을 감안한다고 하더라도 프로그램이 비정상 종료된다는 등의 이유로 실행조차 안될 수 있디.
 * 그렇기 때문에 특정 시점 또는 반드시 실행되어야 한다는 것을 기대하고 사용해서는 안된다고 한다.
 *
 * 역효과를 불러올 수 있다.
 * Unreachable 상태의 객체를 가비지 컬렉션할 때 기본적으로 finalizer가 호출되지만 그렇다고 가비지 컬렉션이 즉시 수행되는 것은 아니다.
 * finalizer queue에 삽입되어 순차적으로 수행된다.
 * 따라서 finalize 가 느리게 실행되는경우, 인스턴스의 소멸이 느려져서 OutOfMemory와 같은 오류를 발생시킬 수 있다.
 *
 * finalizer 는 실행이 느리다.
 *
 * 보안에 취약하다.
 * finalize 메서드를 고의적으로 실행 시간이 오래 걸리도록 만들면 전반적인 시스템 장애를 불러올 수 있다.
 * 또 메서드를 override 하여 악의적으로 정상 실행을 방해할 수 있다고 한다.
 * final 키워드를 붙여서 상속하지 못하도록 막는다.
 */
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    /**
     * TrashBin
     */
    private static class TrashBin implements Runnable {
        int numJunkPiles; // Number of junk piles in this room

        TrashBin(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // Invoked by close method or cleaner
        @Override
        public void run() {
            System.out.println("쓰레기통 비우기!");
            numJunkPiles = 0;
        }
    }

    // 쓰레기통
    private final TrashBin trashBin;

    // clean 객체
    private final Cleaner.Cleanable cleanable;

    public Room(int junks) {
        trashBin = new TrashBin(junks);
        cleanable = cleaner.register(this, trashBin);
    }


    /**
     * AutoCloseable methods
     */
    @Override
    public void close() {
        cleanable.clean();
    }

    /**
     * output
     * System.out.println("Goodbye");
     * 쓰레기통 비우기!
     *
     * try with resources를 사용하면
     * 명시적으로 close()를 호출해준다.
     * 하지만 실행시점은 알수 없다.
     */
//    public static void main(String[] args) {
//        try(Room room = new Room(99);){
//            System.out.println("Goodbye");
//        }
//    }

    /**
     * output
     * System.out.println("Goodbye");
     *
     * try with resources를 사용하지 않으면
     * close()를 호출할지 안할지는 undefined behavior이다.
     * 실행시점 또한 알수 없다.
     */
    public static void main(String[] args) {
        Room room = new Room(99);
        System.out.println("Goodbye");
    }
}
