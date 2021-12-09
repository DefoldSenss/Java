package com.company;

public class PR_5 {
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testMovable();
    }

    static class Tester {
        public void testMovable() {
            Point center = new Point(0, 0);
            Movable a = new MovableCircle(center, 0);
            System.out.println("Testing MovableCircle");
            System.out.println("Print info about object:");
            System.out.println(a.toString());
            System.out.println("Move object to (2,3)");
            a.Move(2, 3);
            System.out.println("Print info about object:");
            System.out.println(a.toString());
        }
    }

    public static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        protected void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        protected void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    public static class Circle {
        private Point center;
        private double radius;

        public Circle(Point center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public Point getCenter() {
            return center;
        }

        public void setCenter(Point center) {
            this.center = center;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "center=" + center +
                    ", radius=" + radius +
                    '}';
        }
    }


    public interface Movable {
        boolean canMove();

        void Move(int new_x, int new_y);
    }

    public static class MovableCircle extends Circle implements Movable {
        boolean canMove = true;

        public MovableCircle(Point center, double radius) {
            super(center, radius);
        }

        @Override
        public boolean canMove() {
            return canMove;
        }

        public void setCanMove(boolean canMove) {
            this.canMove = canMove;
        }

        @Override
        public void Move(int new_x, int new_y) {
            Point center = getCenter();
            MovablePoint newCenter = MovablePoint.pointToMovablePoint(center);
            newCenter.Move(new_x, new_y);
            center.setX(newCenter.getX());
            center.setY(newCenter.getY());
            setCenter(center);
        }

        @Override
        public String toString() {
            return "This is MovableCircle. Center = (" + getCenter().getX() + "," + getCenter().getY() + ")";
        }
    }

    public static class MovablePoint extends Point implements Movable {
        boolean canMove = true;

        public MovablePoint(int x, int y) {
            super(x, y);
        }

        public void setCanMove(boolean canMove) {
            this.canMove = canMove;
        }

        @Override
        public boolean canMove() {
            return canMove;
        }

        @Override
        public void Move(int x, int y) {
            this.setX(getX() + x);
            this.setY(getY() + y);
        }

        public static MovablePoint pointToMovablePoint(Point p) {
            return new MovablePoint(p.getX(), p.getY());
        }

        public String ToString() {
            return "This is MovablePoint";
        }
    }

}
