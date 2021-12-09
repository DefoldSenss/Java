package com.company;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Scanner;

public class PR_8 {
    public static void main(String[] args) {
        testFurnitureShop();
    }

    public static void testFurnitureShop() {
        System.out.println("Test shop");
        FurnitureShop shop = new FurnitureShop();
        shop.addToCatalog(new Chair(100.0, 2.0, "Wooden chair"));
        shop.addToCatalog(new Chair(100.0, 1.3, "Plastic chair"));
        shop.addToCatalog(new Table(200.0, 2.0, "Wooden table"));
        shop.addToCatalog(new Table(200.0, 1.3, "Plastic table"));
        shop.addToCatalog(new Table(300.0, 2.0, "Nice wooden chair"));

        shop.printCatalog();

        int index = -2;
        Scanner scanner = new Scanner(System.in);
        while(index != -1) {
            System.out.println("Enter the ID to buy. Enter -1 to exit:");
            index = scanner.nextInt();
            shop.buyItem(index);
            shop.printCatalog();
        }

    }

    public static abstract class Furniture {
        private final double cost;
        private final double materialType;

        Furniture(double cost, double materialType) {
            this.cost = cost;
            this.materialType = materialType;
        }

        public double getCost() {
            return cost;
        }

        public double getMaterialType() {
            return materialType;
        }

        public abstract double getFullCost();

        public abstract String toString();
    }

    public static class Chair extends Furniture {
        private final String name;

        Chair(double cost, double materialType, String name) {
            super(cost, materialType);
            this.name = name;
        }

        @Override
        public double getFullCost() {
            return getCost() * getMaterialType();
        }

        @Override
        public String toString() {
            return "A chair. Price = " + getFullCost();
        }
    }

    public static class Table extends Furniture {
        private final String name;

        Table(double cost, double materialType, String name) {
            super(cost, materialType);
            this.name = name;
        }

        @Override
        public double getFullCost() {
            return getCost() * getMaterialType();
        }

        @Override
        public String toString() {
            return "A table." + name + ". Price = " + getFullCost();
        }
    }

    public static class FurnitureShop {
        private final ArrayList<Furniture> furniture = new ArrayList<>();

        public void addToCatalog(Furniture furniture) {
            this.furniture.add(furniture);
        }

        public void buyItem(int index) {
            System.out.println("[+] You have bought \""+ furniture.get(index).toString()+"\"");
            furniture.remove(furniture.get(index));
        }

        public void printCatalog() {
            System.out.println("Furniture shop catalog:");
            int i = 0;
            for (Furniture f : furniture) {
                System.out.println(" ID = " + i++ + " | " + f);
            }
        }
    }


    public static abstract class Shape {
        private String color;
        private boolean filled;

        Shape() {
        }

        Shape(String color, boolean filled) {
            this.color = color;
            this.filled = filled;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isFilled() {
            return filled;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
        }

        public abstract double getArea();

        public abstract double getPerimeter();

        @Override
        public String toString() {
            return "Shape{" +
                    "color='" + color + '\'' +
                    ", filled=" + filled +
                    '}';
        }
    }

    public static class Circle extends Shape {
        private double radius;

        Circle() {
            super();
        }

        Circle(double radius) {
            super();
            this.radius = radius;
        }

        Circle(double radius, String color, boolean filled) {
            super(color, filled);
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        @Override
        public double getArea() {
            return 3.14 * radius * radius;
        }

        @Override
        public double getPerimeter() {
            return 2 * 3.14 * radius;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "radius=" + radius +
                    '}';
        }
    }

    public static class Rectangle extends Shape {
        private double width;
        private double length;

        Rectangle() {
            super();
        }

        Rectangle(double width, double length) {
            this.length = length;
            this.width = width;
        }

        Rectangle(double width, double length, String color, boolean filled) {
            super(color, filled);
            this.length = length;
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        @Override
        public double getArea() {
            return width * length;
        }

        @Override
        public double getPerimeter() {
            return width * 2 + length * 2;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "width=" + width +
                    ", length=" + length +
                    '}';
        }
    }

    public static class Square extends Rectangle {
        Square() {
        }

        Square(double side) {
            super(side, side);
        }

        Square(double side, String color, boolean filled) {
            super(side, side);
            this.setColor(color);
            this.setFilled(filled);
        }

        public double getSide() {
            return getWidth();
        }

        public void setSide(double side) {
            setLength(side);
            setWidth(side);
        }

        @Override
        public String toString() {
            return "Square{" +
                    "side=" + getSide() +
                    '}';
        }
    }
}
