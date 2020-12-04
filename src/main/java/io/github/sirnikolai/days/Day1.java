package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;

public class Day1 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        Set<Integer> sum = new HashSet<>();
        int target = 2020;

        for(int i : parse(input)) {
            if(sum.contains(target - i)) {
                System.out.println(i * (target - i));
                return;
            } else {
                sum.add(i);
            }
        }
    }

    @Override
    public void Part2(String input) {
        Map<Long, List<Long>> map = new HashMap<>();
        int[] arr = parse(input);
        long target = 2020;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                map.put(target - (arr[i] + arr[j]), Arrays.asList((long)arr[i], (long)arr[j]));
            }
        }

        for(int i = 0; i < arr.length; i++) {
            if(map.containsKey((long)arr[i])) {
                System.out.println(arr[i] * map.get((long)arr[i]).get(0) * map.get((long)arr[i]).get(1));
                return;
            }
        }
    }

    private int[] parse(String s) {
        String[] str = s.split("\n");
        int[] arr = new int[str.length];

        for(int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        return arr;
    }
}
