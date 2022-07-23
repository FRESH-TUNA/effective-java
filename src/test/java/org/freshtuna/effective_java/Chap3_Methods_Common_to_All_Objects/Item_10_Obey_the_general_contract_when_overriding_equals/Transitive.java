package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.awt.*;
import java.util.Objects;

/**
 * effective java 3E
 * 컴포지션 패턴을 활용하여 transitive, symmetric 특성을 지킨다.
 */
public class Transitive {
    private static class ColorPoint {
        private final Point point;
        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            point = new Point(x, y);
            this.color = Objects.requireNonNull(color);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ColorPoint))
                return false;

            ColorPoint cp = (ColorPoint) o;
            return cp.point.equals(point) && cp.color.equals(color);
        }
    }
}
