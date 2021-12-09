package com.company;

import java.util.Arrays;
import java.util.Random;

public class PR_1 {
    public static void main(String[] args) {
        task1();
        task2(args);
        task3();
        task4();
        task5();
    }

    public static void task5() {
        System.out.println("Task 5");
        Random random = new Random();
        int n = random.nextInt(31);
        System.out.println("Calculating  factorial for " + n);
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        System.out.println("Factorial = " + result);
        System.out.println();
    }

    public static void task4() {
        System.out.println("Task 4");
        int[] a = new int[10];
        Random random = new Random();
        for (int i = 0; i < a.length; ++i)
            a[i] = random.nextInt(10);
        System.out.println("Array using Random:");
        for (int j : a) System.out.print(j + " ");
        System.out.println();

        Arrays.sort(a);
        System.out.println("Sorted array using Random:");
        for (int j : a) System.out.print(j + " ");
        System.out.println();

        System.out.println();

        int max = 10;
        for (int i = 0; i < a.length; ++i)
            a[i] = (int) (Math.random() * max);
        System.out.println("Array using Math.random():");
        for (int j : a) System.out.print(j + " ");
        System.out.println();

        Arrays.sort(a);
        System.out.println("Sorted array using Math.random():");
        for (int j : a) System.out.print(j + " ");
        System.out.println();
    }

    public static void task3() {
        System.out.println("Task 3");
        System.out.println("Harmonic series");
        int p = 2;
        for (int i = 1; i < 11; i++) {
            System.out.printf("%.3f", 1. / (Math.pow(i, p)));
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    public static void task2(String[] args) {
        for (String s : args) System.out.println(s);
    }

    public static void task1() {
        System.out.println("Task 1");
        int[] a = new int[10];
        Random random = new Random();
        for (int i = 0; i < a.length; ++i)
            a[i] = random.nextInt(10);

        System.out.println("Random array:");
        for (int j : a) System.out.print(j + " ");
        System.out.println();

        int sum = 0;
        for (int j : a) sum += j;
        System.out.println("for sum = " + sum);

        int i = 0;
        sum = 0;
        while (i < a.length) {
            sum += a[i];
            ++i;
        }
        System.out.println("while sum = " + sum);

        sum = 0;
        i = 0;
        do {
            sum += a[i];
            ++i;
        } while (i < a.length);
        System.out.println("do-while sum = " + sum);
        System.out.println();
    }
}
