package com.tavisca.gce.assignment;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String FILE_FLAG = "-f";

    public static void main(String[] args) {
        if (args.length < 2) {
            showUsage();
            return;
        }

        List<String> words = null;
        if (!args[0].equals(FILE_FLAG))
            words = new ArrayList<>(Arrays.asList(args));
        else if (args.length == 2) {
            try {
                words = readWords(args[1]);
            } catch (FileNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
                return;
            }
        } else {
            showUsage();
            return;
        }

        Map<String, Long> wordToFrequencyMap = groupWords(words);
        System.out.println("========= [ Words and their frequency ] =========");
        System.out.println(String.format("%-20s%s", "Word", "Frequency"));
        wordToFrequencyMap.forEach(
                (k, v) -> System.out.println(String.format("%-20s%s", k, v)));
    }

    private static Map<String, Long> groupWords(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    }

    private static List<String> readWords(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        List<String> words = new ArrayList<>();

        if (file.isFile()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
                words.add(scanner.next());
        }

        return words;
    }

    private static void showUsage() {

    }
}
