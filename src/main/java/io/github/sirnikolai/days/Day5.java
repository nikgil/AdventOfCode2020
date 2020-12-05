package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] lst = input.split("\n");
        int max = 0;

        for(String s : lst) {
            String first = s.substring(0,7);
            String sec = s.substring(7);

            int col = binarySearchFB(first, 0, 0, 127);
            int row = binarySearchLR(sec, 0, 0, 7);

            max = Math.max(max, col * 8 + row);
        }

        System.out.println(max);
    }

    @Override
    public void Part2(String input) {
        String[] lst = input.split("\n");
        List<Integer> list = new ArrayList<>();

        for(String s : lst) {
            String first = s.substring(0,7);
            String sec = s.substring(7);

            int col = binarySearchFB(first, 0, 0, 127);
            int row = binarySearchLR(sec, 0, 0, 7);
            int val = col * 8 + row;

            list.add(col * 8 + row);
        }

        Collections.sort(list);

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) - list.get(i-1) != 1) {
                System.out.println(list.get(i) + " " + list.get(i-1));
            }
        }
    }

    private int binarySearchFB(String s, int idx, int l, int r) {
        if(idx == s.length()) {
            return r;
        }

        int mid = l + (r - l) / 2;

        if(s.charAt(idx) == 'F') {
            return binarySearchFB(s, idx+1, l, mid);
        } else {
            return binarySearchFB(s, idx+1, mid, r);
        }
    }

    private int binarySearchLR(String s, int idx, int l, int r) {
        if(idx == s.length()) {
            return r;
        }

        int mid = l + (r - l) / 2;

        if(s.charAt(idx) == 'L') {
            return binarySearchLR(s, idx+1, l, mid);
        } else {
            return binarySearchLR(s, idx+1, mid, r);
        }
    }
}
