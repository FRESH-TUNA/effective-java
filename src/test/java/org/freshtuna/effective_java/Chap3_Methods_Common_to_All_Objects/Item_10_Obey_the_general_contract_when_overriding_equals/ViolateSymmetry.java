package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.util.Objects;

/**
 * Symmetry
 * a.equals(b) -> b.equals(a)
 */
public class ViolateSymmetry {
    private static class CaseInsensitiveString {
        private final String string;

        public CaseInsensitiveString(String string) {
            this.string = Objects.requireNonNull(string);
        }

        // Broken - violates symmetry!

        /**
         * string의 경우 equals method 사용시
         * case를 검사하지 않으므로 symmetry가 깨진다.
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof CaseInsensitiveString)
                return string.equalsIgnoreCase(((CaseInsensitiveString) o).string);
            if (o instanceof String)
                return string.equalsIgnoreCase((String) o);
            return false;
        }
    }
}
