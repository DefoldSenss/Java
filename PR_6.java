package com.company;

public class PR_6 {
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testMovables();
    }

    public static class Tester {
        MovableRectangle mr = new MovableRectangle(0, 0, 1, 2);
        PR_5.MovablePoint rbPoint = new PR_5.MovablePoint(1, 2);
        MovablePoints mp = new MovablePoints(new PR_5.MovablePoint(0, 0), rbPoint);

        public void testMovables() {
            System.out.println("Testing movableRectangle and MovablePoints");

            printInfo();
            System.out.println();
            System.out.println("Moving object on (3,3)");
            mr.Move(3, 3);
            mp.Move(3, 3);

            printInfo();
        }

        private void printInfo() {
            System.out.println();
            System.out.println("Info about objects:");
            System.out.println(mr.toString());
            System.out.println(mp.toString());
        }
    }

    public static class Rectangle {
        private int x, y;
        private int a, b;

        Rectangle(int x, int y, int a, int b) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.b = b;
        }

        public int getSquare() {
            return a * b;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public void setA(int a) {
            this.a = a;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    public static class MovableRectangle extends Rectangle implements PR_5.Movable {
        boolean canMove = true;

        @Override
        public boolean canMove() {
            return canMove;
        }

        MovableRectangle(int x, int y, int a, int b) {
            super(x, y, a, b);
        }

        @Override
        public void Move(int new_x, int new_y) {
            setX(getX() + new_x);
            setY(getY() + new_y);
        }

        public String toString() {
            return "This is MovableRectangle. Coordinates = (" + getX() + "," + getY() + ")";
        }
    }

    public static class MovablePoints implements PR_5.Movable {
        boolean canMove = true;
        private PR_5.MovablePoint a, b;

        MovablePoints(PR_5.MovablePoint a, PR_5.MovablePoint b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean canMove() {
            return canMove;
        }

        @Override
        public void Move(int new_x, int new_y) {
            a.Move(new_x, new_y);
            b.Move(new_x, new_y);
        }

        public PR_5.MovablePoint getA() {
            return a;
        }

        public PR_5.MovablePoint getB() {
            return b;
        }

        public String toString() {
            return "This is MovablePoints. Coordinates = (" + getA().getX() + "," + getA().getY() + ")";
        }
    }
}
