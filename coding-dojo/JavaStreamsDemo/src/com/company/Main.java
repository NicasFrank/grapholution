package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String[] INPUTS = new String[]{"-1", "14534", "100", "1", "42", "55", "-48", "576", "23", "90"};

    public static void main(String[] args) {
        intro();
        introWithStreams();
        intermediateOperations();
        terminalOperations();
        reduce();
        collectors();
    }

    private static void intro() {
        var validNumbers = new ArrayList<Integer>();

        for (var input: INPUTS) {
            var number = Integer.parseInt(input);
            if (number > 60) {
                validNumbers.add(number);
            }
        }

        validNumbers.sort(Integer::compareTo);

        for (var number: validNumbers) {
            System.out.println(number);
        }
    }

    private static void introWithStreams() {
        Arrays.stream(INPUTS)
                .map(Integer::parseInt)
                .filter(i -> i > 60)
                .sorted()
                .forEach(System.out::println);
    }

    private static void streamSources() {
        var stream = Stream.of("1", "2");
        stream = Stream.of(INPUTS);
        stream = Stream.iterate("l", s -> s + "l");
        stream = Stream.generate(() -> "l");

        var list = new ArrayList<String>();

        stream = list.stream();
    }

    private static void intermediateOperations() {
        Stream.of(INPUTS)
                .sorted(String::compareTo)
                .dropWhile(s -> s.length() != 2)
                .takeWhile(s -> s.length() != 4)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .filter(s -> {
                    System.out.println(s);
                    return !s.equals("-");
                })
                .mapToInt(Integer::parseInt)
                .boxed()
                .distinct()
                .limit(10)
                .skip(5);
    }

    private static void terminalOperations() {
        System.out.println(Stream.of(INPUTS).count());
        System.out.println(Stream.of(INPUTS).findFirst());
        System.out.println(Stream.of(INPUTS).findAny());
        System.out.println(Stream.of(INPUTS)
                .mapToInt(Integer::parseInt).parallel().sum());
        System.out.println(Stream.of(INPUTS)
                .mapToInt(Integer::parseInt).average());
        System.out.println(Stream.of(INPUTS)
                .mapToInt(Integer::parseInt).max());
        System.out.println(Stream.of(INPUTS)
                .mapToInt(Integer::parseInt).min());
        System.out.println(Stream.of(INPUTS).allMatch(i -> i.equals("-1")));
        System.out.println(Stream.of(INPUTS).anyMatch(i -> i.equals("-1")));
        Stream.of(INPUTS)
                .forEach(System.out::print);
    }

    private static void reduce() {
        System.out.println(Stream.of(INPUTS)
                .mapToInt(Integer::parseInt).reduce(1, (sum, i) -> sum * i));
        System.out.println(Stream.of(INPUTS).reduce("String: ", (sum, s) -> sum + s));
    }

    private static void collectors() {
        Stream.of(INPUTS).collect(Collectors.toSet());
        Stream.of(INPUTS).collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()));
        Stream.of(INPUTS).collect(Collectors.toMap(s -> s, s -> s));
        System.out.println(Stream.of(INPUTS)
                .collect(Collectors.summarizingInt(String::length)).getAverage());

        var groupings = Stream.of(INPUTS)
                .collect(Collectors.groupingBy(String::length, Collectors.summingInt(Integer::parseInt)));

    }
}
