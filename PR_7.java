package com.company;

public class PR_7 {

    public static void main(String[] args){
        dishesTest();
        dogTest();
    }

    public static void dishesTest(){
        System.out.println("[+] Dishes test");
        Dish plate = new Plate(1,10);
        Dish pan = new Pan(20,100);
        Dish pot = new Pot(13, 500, 3);

        plate.calcVolume();
        ((Pan)pan).makeBorsh();
        System.out.println("The pot color is: "+((Pot)pot).getColor());
        System.out.println();
    }

    public static void dogTest(){
        System.out.println("[+] Dog test");
        Dog chin = new Chin(3, "Lol");
        Dog shibainu = new ShibaInu(3, "Kek");
        Dog pug = new Pug(3, "No_one_cares_about_this_text");

        chin.feed();
        shibainu.feed();
        ((Pug)pug).play();
    }

    public static abstract class Dish {
        public abstract void calcVolume();
        public abstract double calcCost();
    }

    public static class Pan  extends  Dish{
        int radius;
        int deep;
        double volume;

        public Pan(int radius, int deep) {
            this.radius = radius;
            this.deep = deep;
        }

        @Override
        public void calcVolume() {
            volume =  radius* deep* 10;
        }

        @Override
        public double calcCost() {
            return radius*deep*228;
        }

        public void makeBorsh(){
            System.out.println("Borsh svaren");
        }
    }
    public static class Plate extends Dish{

        int radius;
        int deep;
        double volume;

        public Plate(int radius, int deep) {
            this.radius = radius;
            this.deep = deep;
            calcVolume();
        }

        @Override
        public void calcVolume() {
            volume = radius * deep;
        }

        @Override
        public double calcCost() {
            return radius*deep*1.337;
        }
    }
    public static class Pot extends Dish{
        int color;
        int radius;
        int deep;
        double volume;

        Pot(int radius, int deep, int color){
            this.radius = radius;
            this.deep = deep;
            this.color = color;
            calcVolume();
        }

        public int getColor() {
            return color;
        }

        @Override
        public void calcVolume() {
            volume = radius*deep*1.488;
        }

        @Override
        public double calcCost() {
            return color*deep*69;
        }
    }

    public static abstract class Dog{
        int age; String name;
        Dog(int age, String name){
            this.age = age;
            this.name = name;
        }
        abstract void feed();
    }

    public static class Pug extends Dog {

        public Pug(int age, String name) {
            super(age, name);
        }

        @Override
        void feed() {
            System.out.println("Pug was fed.");
        }

        void play(){
            System.out.println("You are playing with pug...");
        }
    }

    public static class ShibaInu extends Dog{

        ShibaInu(int age, String name) {
            super(age, name);
        }

        @Override
        void feed() {
            System.out.println("ShibaInu was fed.");
        }

        void stare(){
            System.out.println("You are staring at ShibaInu...");
        }
    }

    public  static class Chin extends Dog{

        Chin(int age, String name) {
            super(age, name);
        }

        @Override
        void feed() {
            System.out.println("Chin was fed");
        }

        void pet(){
            System.out.println("You are petting the Chin");
        }
    }

}
