package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day6 extends AbstractSolution {
    @Override
    public void Part1(String input) {
        String[] split = input.split("\n\n");
        int count = 0;

        for(String s : split) {
            count += postProcess(s).size();
        }

        System.out.println(count);
    }

    @Override
    public void Part2(String input) {
        String[] split = input.split("\n\n");
        int count = 0;

        for(String s : split) {
            count += postProcess2(s);
        }

        System.out.println(count);
    }

    private Set<Character> postProcess(String s) {
        String[] arr = s.split("\n");
        Set<Character> set = new HashSet<>();

        for(String s2 : arr) {
            for(char c : s2.toCharArray()) {
                set.add(c);
            }
        }

        return set;
    }

    private int postProcess2(String s) {
        String[] arr = s.split("\n");
        Map<Character, Integer> set = new HashMap<>();

        for(String s2 : arr) {
            for(char c : s2.toCharArray()) {
                set.put(c, set.getOrDefault(c, 0) + 1);
            }
        }

        int count = 0;

        for(Map.Entry<Character, Integer> e : set.entrySet()) {
            if(e.getValue() == arr.length) {
                count++;
            }
        }

        return count;
    }
}
