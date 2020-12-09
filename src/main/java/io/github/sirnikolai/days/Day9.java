package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;

public class Day9 extends AbstractSolution {

    public static int cap = 25;

    @Override
    public void Part1(String input) {
        String[] set = input.split("\n");

        for(int i = cap; i < set.length; i++) {
            long next = Long.parseLong(set[i]);
            boolean invalid = true;

            for(int j = i - cap; j < i; j++) {
                long val1 = Long.parseLong(set[j]);

                for(int k = j + 1; k < i; k++) {
                    long val2 = Long.parseLong(set[k]);
                    if(val1 + val2 == next) {
                        invalid = false;
                        break;
                    }
                }
            }

            if(invalid) {
                System.out.println(next);
                return;
            }
        }
    }

    @Override
    public void Part2(String input) {
        String[] set = input.split("\n");
        long target = -1;

        for(int i = cap; i < set.length; i++) {
            long next = Long.parseLong(set[i]);
            boolean invalid = true;

            for(int j = i - cap; j < i; j++) {
                long val1 = Long.parseLong(set[j]);

                for(int k = j + 1; k < i; k++) {
                    long val2 = Long.parseLong(set[k]);
                    if(val1 + val2 == next) {
                        invalid = false;
                        break;
                    }
                }
            }

            if(invalid) {
                target = next;
                break;
            }
        }

        for(int i = 0; i < set.length; i++) {
            long sum = 0;

            for(int j = i; j < set.length; j++) {
                sum += Long.parseLong(set[j]);

                if(sum == target) {
                    long[] minmax = getMinMax(set, i, j);
                    System.out.println(minmax[0] + minmax[1]);
                    return;
                } else if(sum > target) {
                    break;
                }
            }
        }
    }

    private long[] getMinMax(String[] strs, int start, int end) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i = start; i <= end; i++) {
            min = Math.min(min, Long.parseLong(strs[i]));
            max = Math.max(max, Long.parseLong(strs[i]));
        }

        return new long[]{min, max};
    }
}
