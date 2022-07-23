package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.awt.*;

/**
 * effective java 3E
 * Transitive는 상속관계에서 문제를 발생시킬수 있다.
 */
public class ViolateSymmetry2 {
    private static class Point {
        private final int x;
        private final int y;

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
         * Point x = new Point(1,2);
         * ColorPoint y = new ColorPoint(1,2,RED);
         *
         * x.equals(y) 는 true 이자만 y.equals(x) 는 false 임으로
         * 대칭성을 위반하고 있다.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ColorPoint))
                return false;
            return super.equals(o) && ((ColorPoint) o).color == color;
        }
    }
}
