package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Day14 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] split = input.split("\n");
        Map<Integer, String> map = new HashMap<>();

        int prev = 0;

        for(int i = 1; i <= split.length; i++) {
            if(i == split.length || split[i].startsWith("mask")) {
                doRound(map, split, prev, i);
                prev = i;
            }
        }

        long total = 0;

        for(String s : map.values()) {
            total += Long.parseLong(trimZeros(new StringBuilder(s).reverse().toString()), 2);
        }

        System.out.println(total);
    }

    private void doRound(Map<Integer, String> map, String[] split, int start, int end) {
        List<int[]> locs = getInts(split, start + 1, end);
        String mask = new StringBuilder(split[start].substring(split[start].indexOf("= ") + 2)).reverse().toString();


        for(int[] arr : locs) {
            map.put(arr[0], replace(mask, new StringBuilder(Integer.toString(arr[1], 2)).reverse().toString()));
        }
    }

    private String trimZeros(String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '0') {
                return s.substring(i);
            }
        }

        return "0";
    }

    private String replace(String mask, String num) {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < mask.length(); i++) {
            if(mask.charAt(i) != 'X') {
                s.append(mask.charAt(i));
            } else {
                if(i >= num.length()) {
                    s.append('0');
                } else {
                    s.append(num.charAt(i));
                }
            }
        }

        return s.toString();
    }

    private List<int[]> getInts(String[] arr, int start, int limit) {
        List<int[]> lst = new LinkedList<>();

        for(int i = start; i < limit; i++) {
            String sub = arr[i];

            int loc = Integer.parseInt(sub.substring(sub.indexOf("mem[")+4, sub.indexOf("]")));
            int val = Integer.parseInt(sub.substring(sub.indexOf("= ")+2));

            lst.add(new int[]{loc, val});
        }

        return lst;
    }

    private List<long[]> getLongs(String[] arr, int start, int limit) {
        List<long[]> lst = new LinkedList<>();

        for(int i = start; i < limit; i++) {
            String sub = arr[i];

            long loc = Long.parseLong(sub.substring(sub.indexOf("mem[")+4, sub.indexOf("]")));
            long val = Long.parseLong(sub.substring(sub.indexOf("= ")+2));

            lst.add(new long[]{loc, val});
        }

        return lst;
    }

    //TODO: this is pretty broken. Had to do in python because there is overflow here somewhere I can't explain
    @Override
    public void Part2(String input) {
        String[] split = input.split("\n");
        Map<Long, Long> map = new HashMap<>();

        int prev = 0;

        for(int i = 1; i <= split.length; i++) {
            if(i == split.length || split[i].startsWith("mask")) {
                doRound2(map, split, prev, i);
                prev = i;
            }
        }

        long total = 0;

        for(long i : map.values()) {
            total += i;
        }

        System.out.println(total);
    }

    private void doRound2(Map<Long, Long> map, String[] split, int start, int end) {
        List<long[]> locs = getLongs(split, start + 1, end);
        String mask = new StringBuilder(split[start].substring(split[start].indexOf("= ") + 2))
                .reverse()
                .toString();


        for(long[] arr : locs) {
            List<String> variants = replace2(mask, new StringBuilder(Long.toString(arr[0], 2)).reverse().toString());

            for(String s : variants) {
                map.put(Long.parseLong((s), 2), arr[1]);
            }
        }
    }

    private List<String> replace2(String mask, String num) {
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < mask.length(); i++) {
            if(mask.charAt(i) == 'X') {
                s.append('X');
            } else {
                if(i >= num.length()) {
                    s.append('0');
                } else {
                    if(mask.charAt(i) == '0') {
                        s.append(num.charAt(i));
                    } else {
                        s.append('1');
                    }
                }
            }
        }

        List<String> lst = new ArrayList<>();

        getPermutations(lst, s.reverse().toString().toCharArray());
        return lst;
    }

    private void getPermutations(List<String> lst, char[] c) {
        List<char[]> arr = new ArrayList<>();
        arr.add(c);

        for(int i = 0; i < c.length; i++) {
            List<char[]> temp = new ArrayList<>();

            for(char[] cArr : arr) {
                if(cArr[i] == 'X') {
                    char[] copy = Arrays.copyOf(cArr, cArr.length);

                    cArr[i] = '0';
                    copy[i] = '1';

                    temp.add(copy);
                }
            }

            arr.addAll(temp);
        }

        for(char[] cArr : arr) {
            lst.add(new String(cArr));
        }
    }
}
