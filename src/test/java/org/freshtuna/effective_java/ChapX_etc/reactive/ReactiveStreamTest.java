package org.freshtuna.effective_java.ChapX_etc.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * thank to johnny
 * [@author](https://my.oschina.net/arthor) johnny
 **/
public class ReactiveStreamTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * publisher
         */
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        /**
         * subscriber
         */
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("Subscription succeeded.");
                subscription.request(1);
                System.out.println("Request a data in a subscription method");
            }

            /**
             * item 주입시 콜백
             */
            @Override
            public void onNext(Integer item) {
                System.out.printf("[onNext Receive data item : %d]\n ", item);
                /**
                 * 1개의 데이터를 가져올수 있으면 가져온다.
                 */
                subscription.request(1);
            }

            /**
             * item 주입시 에러 콜백
             */
            @Override
            public void onError(Throwable throwable) {
                System.out.println("[onError Exception occurred)");
                /**
                 * subscription이 그만 메시지를 가져오도록 한다.
                 */
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("[onComplete All data receipt completed)");
            }
        };

        /**
         * subscriber 등록
         */
        publisher.subscribe(subscriber);

        /**
         * publisher에서 subscriber들에 시그널 전달
         */
        for (int i = 1; i <= 5; i++) {
            System.out.printf("[production data %d ]\n", i );

            /**
             * Publishes the given item, if possible,
             * to each current subscriber by asynchronously invoking its onNext method.
             */
            publisher.submit(i);
        }


        /**
         * publisher가 닫히면 subscriber의 onComplete 메소드가 호출된다.
         */
        publisher.close();

        /**
         * main 메소드가 끝나면 프로그램이 죽어버리기 때문에 ㅠ
         */
        Thread.currentThread().join(100000);
    }
}
