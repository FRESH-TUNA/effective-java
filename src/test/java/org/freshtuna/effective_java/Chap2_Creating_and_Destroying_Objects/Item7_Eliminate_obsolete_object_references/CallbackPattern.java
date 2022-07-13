package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item7_Eliminate_obsolete_object_references;

/**
 * 메모리 누수의 원인중이 하나가 콜백패턴이다.
 */
public class CallbackPattern {
    private interface Callback { // 인터페이스는 외부에 구현해도 상관 없습니다.
        void callbackMethod();
    }

    private static class Callee {
        private boolean m_condition;
        private Callback m_callback;

        public Callee(Callback callback) {
            m_condition = false;
            m_callback = callback;
        }

        // 콜백 메서드를 호출할 수 있는지 확인
        private void checkCondition() {
            if(m_condition && (m_callback != null))
                m_callback.callbackMethod(); // 가능하면 콜백 메서드 호출
        }

        public void call() {
            System.out.println("callee called");
            m_callback.callbackMethod();
        }
    }

    private static class Caller {
        private Callee callee;
        private int value;

        /**
         * if caller constructor is called
         * and caller is not used
         * callback integer variable leaked
         */
        public Caller() {
            Callback callback = new Callback() {
                private int callback = 20;
                @Override
                public void callbackMethod() {
                    System.out.println("callbacked!!" + callback);
                }
            };
            callee = new Callee(callback);
        }

        public void call() {
            callee.call();
        }
    }

    public static void main(String[] args) {
        new Caller().call();
    }
}
