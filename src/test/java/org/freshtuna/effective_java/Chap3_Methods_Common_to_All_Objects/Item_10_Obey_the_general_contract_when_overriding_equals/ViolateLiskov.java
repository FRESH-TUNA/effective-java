package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.awt.*;

public class ViolateLiskov {
    private static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point))
                return false;

            Point p = (Point)o;
            return p.x == x && p.y == y;
        }
    }

    public class ColorPoint extends Point {
        private final Color color;
        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        /**
         * 리스코프 치환원칙이 깨진다.
         * 부모타입의 메소드가 자식타입의 메소드에서도 제대로 동작하지 않기 때문이다.
         */
        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != getClass())
                return false;

            Point p = (Point) o;
            return p.x == this.x && p.y == this.y;
        }
    }
}
