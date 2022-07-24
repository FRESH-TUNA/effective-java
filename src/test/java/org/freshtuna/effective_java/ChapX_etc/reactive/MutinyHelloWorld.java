package org.freshtuna.effective_java.ChapX_etc.reactive;

import io.smallrye.mutiny.Uni;

public class MutinyHelloWorld {
    /**
     * A Uni represents a lazy asynchronous action.
     * It follows the subscription pattern,
     *  meaning that the action is only triggered once a UniSubscriber subscribes to the Uni.
     */
    public static void main(String[] args) {
        Uni.createFrom().item("hello")
                .onItem().transform(item -> item + " mutiny")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(item -> System.out.println(">> " + item));
    }
}
