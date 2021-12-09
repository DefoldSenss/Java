package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class PR_29_30 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.run();
    }

    static class Counter {
        public void run() {
            Comparator<Map.Entry<String, Integer>> valueComparator = Map.Entry.comparingByValue(Comparator.reverseOrder());
            Comparator<Map.Entry<String, Integer>> keyComparator = Map.Entry.comparingByKey();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            Stream<String> stream = in.lines();

            stream.flatMap(x -> stream(x.split("\\W+")))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(x -> x, Collectors.summingInt(p -> 1)))
                    .entrySet()
                    .stream()
                    .sorted(valueComparator.thenComparing(keyComparator))
                    .map(Map.Entry::getKey)
                    .limit(10)
                    .forEach(System.out::println);
        }
    }
}
