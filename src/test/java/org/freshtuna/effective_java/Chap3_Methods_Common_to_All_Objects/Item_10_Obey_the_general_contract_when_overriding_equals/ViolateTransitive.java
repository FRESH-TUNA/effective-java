package org.freshtuna.effective_java.Chap3_Methods_Common_to_All_Objects.Item_10_Obey_the_general_contract_when_overriding_equals;

import java.awt.*;

public class ViolateTransitive {
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
         * ColorPoint x = new ColorPoint(1,2,RED);
         * Point y = new Point(1,2);
         * ColorPoint z = new ColorPoint(1,2,BLUE);
         *
         * x.equals(y) // true
         * y.equals(z) // true
         * x.equals(z) // false
         * 추이성이 만족되지 않는다.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point))
                return false;

            if (!(o instanceof ColorPoint))
                return o.equals(this);

            return super.equals(o) && ((ColorPoint) o).color == color;
        }
    }
}
