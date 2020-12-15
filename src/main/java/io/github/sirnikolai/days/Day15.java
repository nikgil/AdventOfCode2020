package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;

public class Day15 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] in = input.split(",");
        Map<Integer, List<Integer>> map = new HashMap<>();
        int prev = -1;

        for(int i = 0; i < in.length; i++) {
            List<Integer> lst = new ArrayList<>();
            lst.add(i);

            map.put(Integer.parseInt(in[i]), lst);
            prev = Integer.parseInt(in[i]);
        }

        for(int i = in.length; i < 2020; i++) {
            if(map.get(prev).size() == 1) {
                map.putIfAbsent(0, new ArrayList<>());
                map.get(0).add(i);
                prev = 0;
                continue;
            }

            List<Integer> appearances = map.get(prev);

            int next = appearances.get(appearances.size() - 1) - appearances.get(appearances.size() - 2);

            map.putIfAbsent(next, new ArrayList<>());
            map.get(next).add(i);

            prev = next;
        }

        System.out.println(prev);
    }

    @Override
    public void Part2(String input) {
        String[] in = input.split(",");
        Map<Long, List<Long>> map = new HashMap<>();
        long prev = -1;

        for(int i = 0; i < in.length; i++) {
            List<Long> lst = new ArrayList<>();
            lst.add((long) i);

            map.put(Long.parseLong(in[i]), lst);
            prev = Integer.parseInt(in[i]);
        }

        for(int i = in.length; i < 30000000; i++) {
            if(map.get(prev).size() == 1) {
                map.putIfAbsent(0l, new ArrayList<>());
                map.get(0l).add((long) i);
                prev = 0;
                continue;
            }

            List<Long> appearances = map.get(prev);

            long next = appearances.get(appearances.size() - 1) - appearances.get(appearances.size() - 2);

            map.putIfAbsent(next, new ArrayList<>());
            map.get(next).add((long) i);

            prev = next;
        }

        System.out.println(prev);
    }
}
