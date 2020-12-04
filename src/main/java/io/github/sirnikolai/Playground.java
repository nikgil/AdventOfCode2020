package io.github.sirnikolai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Playground {
    public static void main(String[] args) {
        String s = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#";

        String[] input = s.split("\n");

        ArrayList<List<Character>> inputList = new ArrayList<>();
        for(String s2 : input) {
            System.out.println(Arrays.asList(s2.toCharArray()).getClass());
            System.out.println((new ArrayList<Integer>()).getClass());

            List<Character> lst = s2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            inputList.add(lst);
        }
    }
}
