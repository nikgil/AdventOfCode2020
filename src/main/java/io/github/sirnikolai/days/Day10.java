package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.Arrays;

public class Day10 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        int[] in = toInteger(input.split("\n"));
        Arrays.sort(in);

        int oneJumps = 1;
        int threeJumps = 1;

        for(int i = 1; i < in.length; i++) {
            int diff = in[i] - in[i-1];

            if(diff == 1) {
                oneJumps++;
            } else if(diff == 3) {
                threeJumps++;
            }
        }

        System.out.println(oneJumps * threeJumps);
    }

    @Override
    public void Part2(String input) {
        int[] in = toInteger(input.split("\n"));
        Arrays.sort(in);

        System.out.println(backtrack(in, -1, 0, in[in.length - 1]));
    }

    private long backtrack(int[] arr, int cur, int tally, int target) {
        long[] arr2 = new long[arr.length+1];
        arr2[0] = 1;

        for(int i = 1; i < arr2.length; i++) {
            if(arr[i-1] <= 3) {
                arr2[i] = 1;
            }

            for(int j = Math.max(i-3, 1); j < i; j++) {
                if(arr[i-1] - arr[j-1] <= 3) {
                    arr2[i] += arr2[j];
                }
            }
        }

        return arr2[arr2.length-1];
//        if(target == tally) {
//            return 1l;
//        } else if(target < tally) {
//            return 0l;
//        }
//
//        long sum = 0l;
//
//        for(int i = cur + 1; i < arr.length; i++) {
//            if(arr[i] - tally > 3) {
//                break;
//            }
//
//            sum += backtrack(arr, i, arr[i], target);
//        }
//
//        return sum;
    }

    private int[] toInteger(String[] in) {
        int[] out = new int[in.length];

        for(int i = 0; i < in.length; i++) {
            out[i] = Integer.parseInt(in[i]);
        }

        return out;
    }
}
