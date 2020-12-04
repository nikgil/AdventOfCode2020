package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

public class Day3 extends AbstractSolution {
    @Override
    public void Part1(String input) {
        int count = getSteps(input, 3, 1);
        System.out.println(count);
    }

    @Override
    public void Part2(String input) {
        long count1 = getSteps(input, 1, 1);
        long count3 = getSteps(input, 3, 1);
        long count5 = getSteps(input, 5, 1);
        long count7 = getSteps(input, 7, 1);
        long count12 = getSteps(input, 1, 2);

        System.out.println(count1 * count3 * count5 * count7 * count12);
    }

    private int getSteps(String input, int xStep, int yStep) {
        char[][] map = getMap(input);
        int x = 0;
        int y = 0;
        int count = 0;

        while(y < map.length) {
            if(map[y][x] == '#') {
                count++;
            }

            y += yStep;
            x += xStep;
            x %= map[0].length;
        }

        return count;
    }

    private char[][] getMap(String input) {
        String[] split = input.split("\n");
        char[][] map = new char[split.length][split[0].length()];

        for(int i = 0; i < split.length; i++) {
            map[i] = split[i].toCharArray();
        }

        return map;
    }
}
