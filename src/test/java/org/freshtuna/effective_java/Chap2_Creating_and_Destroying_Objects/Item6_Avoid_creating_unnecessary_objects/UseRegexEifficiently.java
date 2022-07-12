package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item6_Avoid_creating_unnecessary_objects;

import java.util.regex.Pattern;

public class UseRegexEifficiently {
    /**
     * 매번 실행될때 마타 regex 객체가 생성되므로 비횻율적이다.
     */
    private static class UnEffificentRomanNumerals {
        static boolean isRomanNumeral(String s) {
            return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        }
    }

    /**
     * 매번 실행될때 마다 이미 컴파일된 객체를 사용되므로 효율적이다.
     */
    private static class EffificentRomanNumerals {
        private static final Pattern ROMAN = Pattern.compile(
                        "^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        static boolean isRomanNumeral(String s) {
            return ROMAN.matcher(s).matches();
        }
    }
}
