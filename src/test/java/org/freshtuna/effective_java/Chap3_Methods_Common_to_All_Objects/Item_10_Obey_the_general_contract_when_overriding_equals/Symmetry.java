package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.util.Objects;

public class Symmetry {
    private static class CaseInsensitiveString {
        private final String string;

        public CaseInsensitiveString(String string) {
            this.string = Objects.requireNonNull(string);
        }

        // Broken - violates symmetry!

        /**
         * 같은 객체일때만 equals를 판단하도록 수정해본다
         */
        @Override
        public boolean equals(Object o) {
            return o instanceof CaseInsensitiveString && string.equalsIgnoreCase(((CaseInsensitiveString) o).string);
        }
    }
}
