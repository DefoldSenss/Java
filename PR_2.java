package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PR_2 {
    public static void main(String[] args) {
        Tester object = new Tester();
        object.testShape();
        object.testBallAndBook();
        object.DogFight();
    }

    public static class Shape {
        protected String name = "";

        public Shape(String name) {
            this.name = name;
        }

        public Shape() {
            this.name = "";
        }

        @Override
        public String toString() {
            return "Object name: " + name + ".";
        }
    }

    public static class Tester {
        public void testShape() {
            System.out.println("Testing Shape class");
            System.out.println("Creating shape with name=\"test1\"");
            Shape test = new Shape("test1");
            System.out.println("Object info output: \"" + test.toString() + "\"");
            System.out.println();
        }

        public void testBallAndBook() {
            System.out.println("Testing Ball and Book classes");
            System.out.println("Creating classes with names \"test1\" and \"test2\"");
            Ball test1 = new Ball("test1");
            Book test2 = new Book("test2");
            System.out.println("Object №1 info output: \"" + test1.toString() + "\"");
            System.out.println("Object №2 info output: \"" + test2.toString() + "\"");
            System.out.println();
        }

        public void DogFight() {
            System.out.println("Testing DogNursery class");
            DogNursery d = new DogNursery();
            System.out.println("Creating dog with name \"roboDog\" and age \"2\"");
            System.out.println("DogNursery output:");
            System.out.println("-------------------------------------------------");
            d.addDog("roboDog", 2);
            System.out.println("-------------------------------------------------");
            System.out.println("Creating dog with name \"roboDog2\" and age \"3\"");
            System.out.println("DogNursery output:");
            System.out.println("-------------------------------------------------");
            d.addDog("roboDog2", 3);
            System.out.println("-------------------------------------------------");
        }
    }

    public static class Ball extends Shape {
        public Ball(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            String base = super.toString();
            base += " This is a ball!";
            return base;
        }
    }

    public static class Book extends Shape {
        public Book(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            String base = super.toString();
            base += " This is a book!";
            return base;
        }
    }

    public static class Dog {
        private int age;
        private String name;

        public Dog(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHumanAge() {
            return age * 7;
        }

        public String toString() {
            return "This is a dog. Age = " + age + " name = " + name;
        }
    }

    public static class DogNursery {
        private ArrayList<Dog> dogsArray;

        public DogNursery() {
            dogsArray = new ArrayList<>();
        }

        public void addDog(String name, int age) {
            dogsArray.add(new Dog(age, name));
            printDogs();
        }

        private void printDogs() {
            System.out.println("Dogs:");
            for (int i = 0; i < dogsArray.size(); ++i)
                System.out.print("Dog №" + (i + 1) + ". Age = " + dogsArray.get(i).age + " name = " + dogsArray.get(i).name + "\n");
        }
    }
}
