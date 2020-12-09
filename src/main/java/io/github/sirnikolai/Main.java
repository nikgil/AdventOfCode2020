package io.github.sirnikolai;

import io.github.sirnikolai.days.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<Integer, AbstractSolution> solutions;

    public static void main(String[] args) throws IOException {
        solutions = new HashMap<>();
        populateMap();

        int runTarget = solutions.size();

        if(args.length > 0) {
            try {
                runTarget = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println(args[0] + " is not a number... Using default max value instead");
            }
        }

        AbstractSolution toRun = solutions.get(runTarget);

        String input = parseDayInput(runTarget);

        toRun.Part1(input);

        toRun.Part2(input);
    }

    private static String parseDayInput(int day) throws IOException {
        String path = String.format("Day%d.txt", day);
        Path p = Paths.get("src", "main", "resources", path);
        List<String> lst = Files.readAllLines(p);
        
        return String.join("\n", lst);
    }
    private static void populateMap() {
        solutions.put(1, new Day1());
        solutions.put(2, new Day2());
        solutions.put(3, new Day3());
        solutions.put(4, new Day4());
        solutions.put(5, new Day5());
        solutions.put(6, new Day6());
        solutions.put(7, new Day7());
        solutions.put(8, new Day8());
        solutions.put(9, new Day9());
    }
}
