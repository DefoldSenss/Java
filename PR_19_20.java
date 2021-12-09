package com.company;

import java.util.*;

public class PR_19_20 {
    public static void main(String[] args) {
        Generate generate = new Generate();
        generate.plates();
        generate.find("К777УН77");
    }

    public static class Generate {
        private final ArrayList<String> plates = new ArrayList<>();
        private final HashSet<String> plates_hash = new HashSet<>();
        private final Set<String> plates_tree = new TreeSet<>();

        public void plates() {
            String[] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
            for (int i = 1; i <= 199; i++) {
                for (int j = 1; j <= 9; j++)
                    for (String s : letters) {
                        String e = s + (j) + (j) + (j) + s + s + (i);
                        plates.add(e);
                        plates_hash.add(e);
                        plates_tree.add(e);
                    }
            }
            for (int i = 1; i <= 199; i++) {
                for (int l = 1; l <= 9; l++)
                    for (String item : letters) {
                        for (String value : letters) {
                            for (String s : letters) {
                                String e = item + (l) + (l) + (l) + value + s + (i);
                                plates.add(e);
                                plates_hash.add(e);
                                plates_hash.add(e);
                                plates_tree.add(e);
                            }
                        }
                    }
            }
        }

        public void find(String plate) {
            boolean found;

            long startTime = System.nanoTime();
            found = plates.contains(plate);
            long estimatedTime = System.nanoTime() - startTime;
            System.out.print("Поиск перебором: номер ");
            if (found)
                System.out.println("найден  " + estimatedTime + "нс");
            else
                System.out.println("не найден  " + estimatedTime + "нс");

            Collections.sort(plates);
            int x;
            startTime = System.nanoTime();
            x = Collections.binarySearch(plates, plate);
            estimatedTime = System.nanoTime() - startTime;
            System.out.print("Бинарный поиск: номер ");
            if (x > 0 && x <= plates.size())
                System.out.println("найден  " + estimatedTime + "нс");
            else
                System.out.println("не найден  " + estimatedTime + "нс");

            startTime = System.nanoTime();
            found = plates_hash.contains(plate);
            estimatedTime = System.nanoTime() - startTime;
            System.out.print("Поиск в HashSet: номер ");
            if (found)
                System.out.println("найден  " + estimatedTime + "нс");
            else
                System.out.println("не найден  " + estimatedTime + "нс");

            startTime = System.nanoTime();
            found = (plates_tree.contains(plate));
            estimatedTime = System.nanoTime() - startTime;
            System.out.print("Поиск в HashSet: номер ");
            if (found)
                System.out.println("найден  " + estimatedTime + "нс");
            else
                System.out.println("не найден  " + estimatedTime + "нс");
        }
    }
}
