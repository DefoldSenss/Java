package com.company;

public class PR_3 {
    public static void main(String[] args) {
        Tester object = new Tester();
        object.testCircle();
        object.testHuman();
    }

    static class Tester {
        public void testCircle() {
            System.out.println("[+]Testing Circle class");
            Circle circle = new Circle(3.14);
            System.out.println("[+]Created circle with radius = 3.14. Info about object:");
            System.out.println(circle.toString());
            System.out.println("[+]Change radius to 3");
            circle.setRadius(3);
            System.out.println("[+]Info about object:");
            System.out.println(circle.toString());
            System.out.println();
        }

        public void testHuman() {
            System.out.println("[+]Testing Human class");
            Human human = new Human();
            System.out.println("[+]Info about human:");
            System.out.println(human.toString());
            System.out.println("[+]Changing info about human.");
            human.head.setIQ(128);
            human.legRight.setSize(40);
            human.legLeft.setSize(40);
            System.out.println("[+]New info about human:");
            System.out.println(human.toString());
        }
    }

    public static class Human {
        private final Hand handLeft;
        private final Hand handRight;
        private final Leg legRight;
        private final Leg legLeft;
        private final Head head;

        int age;

        Human() {
            handLeft = new Hand(true);
            handRight = new Hand(false);
            legLeft = new Leg(true);
            legRight = new Leg(false);
            head = new Head();
        }

        public Hand getHandLeft() {
            return handLeft;
        }

        public Hand getHandRight() {
            return handRight;
        }

        public Leg getLegLeft() {
            return legLeft;
        }

        public Leg getLegRight() {
            return legRight;
        }

        public Head getHead() {
            return head;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Human{ " +
                    "handLeft=" + handLeft.toString() +
                    ", handRight=" + handRight.toString() +
                    ", legRight=" + legRight.toString() +
                    ", legLeft=" + legLeft.toString() +
                    ", head=" + head.toString() +
                    ", age=" + age +
                    " }";
        }
    }

    public static class Hand {
        private final boolean left;
        private int fingersCount;

        Hand(boolean l) {
            left = l;
            fingersCount = 5;
        }

        public int getFingersCount() {
            return fingersCount;
        }

        public void setFingersCount(int fingersCount) {
            this.fingersCount = fingersCount;
        }

        public boolean isLeft() {
            return left;
        }

        @Override
        public String toString() {
            String info1;
            if (left)
                info1 = "hand is left";
            else info1 = "hand is right";
            return "Hand{" +
                    info1 +
                    ", fingersCount=" + fingersCount +
                    '}';
        }
    }

    public static class Leg {
        private int size;
        private final boolean left;

        Leg(boolean l) {
            left = l;
            size = 30;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isLeft() {
            return left;
        }

        @Override
        public String toString() {
            String info1;
            if (left)
                info1 = "leg is left";
            else info1 = "leg is right";
            return "Leg{" +
                    "size=" + size +
                    ", " + info1 +
                    '}';
        }
    }

    public static class Head {
        private int IQ;

        Head() {
            IQ = 70;
        }

        public int getIQ() {
            return IQ;
        }

        public void setIQ(int IQ) {
            this.IQ = IQ;
        }

        @Override
        public String toString() {
            return "Head{" +
                    "IQ=" + IQ +
                    '}';
        }
    }

    public static class Circle extends PR_2.Shape {
        protected double radius;

        public Circle(double radius) {
            super();
            this.radius = radius;
        }

        public Circle() {
            super();
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double getArea() {
            return Double.parseDouble(String.format("%.4g%n", 3.14 * radius * radius).replace(',', '.'));
        }

        @Override
        public String toString() {
            return "This is Circle. Radius = " + radius + " area = " + getArea();
        }
    }
}
