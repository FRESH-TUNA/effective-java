package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item1_Consider_static_factory_methods_instead_of_constructors;

public class DocsOrCommentRequired {
    /**
     * 개발자는 생성자에 대해선 잘알지만
     * 정적팩토리메소드에서는 잘 모를수 있으므로
     * 코드에 주석을 남겨두더나
     * 기술문서에 정적팩토리메소드에 대한 설명을 추가해야 한다.
     */
    public static class test {
        private String name;

        private test() {}

        private test(String name) {
            this.name = name;
        }

        /**
         * Static Factory Methoc
         * 이름 받아 test 객체를 생성한다.
         * @param name
         * @return test
         */
        public static test of(String name) {
            return new test(name);
        }
    }
}
