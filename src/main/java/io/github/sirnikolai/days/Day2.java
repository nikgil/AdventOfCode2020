package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;

public class Day2 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] split = input.split("\n");
        int total = 0;
        for(String s : split) {
            String[] subsplit = s.split(" ");
            int[] vals = getMinMax(subsplit[0]);
            char letter = subsplit[1].charAt(0);

            int count = charCount(subsplit[2], letter);

            if(count >= vals[0] && count <= vals[1]) {
                total++;
            }
        }

        System.out.println(total);
    }

    private int charCount(String s, char c) {
        int count = 0;

        for(char c2 : s.toCharArray()) {
            if(c2 == c) {
                count++;
            }
        }

        return count;
    }

    private int[] getMinMax(String s) {
        String[] split = s.split("-");

        return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    }

    @Override
    public void Part2(String input) {
        String[] split = input.split("\n");
        int total = 0;
        for(String s : split) {
            String[] subsplit = s.split(" ");
            int[] vals = getMinMax(subsplit[0]);
            char letter = subsplit[1].charAt(0);

            if(subsplit[2].charAt(vals[0]-1) == letter ^ subsplit[2].charAt(vals[1]-1) == letter) {
                total++;
            }
        }

        System.out.println(total);
    }
}
