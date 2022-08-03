package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.awt.*;

public class NullNONEEquality {
    private static class NullQuality {
        /**
         * null 과의 비교는 항상 false 여야만 한다.
         */
        @Override
        public boolean equals(Object o) {
            if(o == null)
                return false;
            return true;
        }
    }
}
