package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item1_Consider_static_factory_methods_instead_of_constructors;


public class CannotBeSubclassed {
    /**
     * public, protected 생성자가 없이
     * 정적 팩토리 메소드만 있으면
     * 이 객체의 서브클래스를 만드는것은 불가능하다.
     * 하지만 상속이 아니라 컴포지션으로 개발을 유도하는 장점도 있다.
     */
    public static class test {
        private String name;

        private test() {}

        private test(String name) {
            this.name = name;
        }

        public static test of(String name) {
            return new test(name);
        }
    }
}
