package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.math.BigInteger;
import java.util.*;

public class Day13 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] arr = input.split("\n");
        String[] times = arr[1].split(",");
        int minTime = Integer.parseInt(arr[0]);
        int[] next = new int[times.length];
        int min = Integer.MAX_VALUE;
        Arrays.fill(next, -1);

        for(int i = 0; i < next.length; i++) {
            if(times[i].equals("x")) {
                continue;
            }

            int nextTime = Integer.parseInt(times[i]);
            int time = 0;

            while(time < minTime) {
//                if(i == 0) {
//                    System.out.println(time);
//                }
                time += nextTime;

                //time = toTime(time);
            }

            next[i] = time;
            min = min == Integer.MAX_VALUE
                    ? i
                    : next[min] > next[i]
                        ? i
                        : min;
        }

        System.out.println(Integer.parseInt(times[min]) * (next[min] - minTime));
    }

    // Well this was a lost cause
    private int toTime(int i) {
        String s = i + "";

        if(s.length() == 1) {
            return i;
        }

        int mins = Integer.parseInt(s.substring(s.length() - 2));
        int addHrs = 0;

        if(mins >= 60) {
            addHrs += mins / 60;
            mins %= 60;
        }

        String minString = mins < 10
            ? "0" + mins
            : mins + "";

        if(s.length() > 2) {
            int hrs = Integer.parseInt(s.substring(0, s.length() - 2));
            addHrs += hrs;
        }

        return Integer.parseInt(addHrs + minString);
    }

    @Override
    public void Part2(String input) {
        String[] arr = input.split("\n");
        String[] times = arr[1].split(",");
        List<BigInteger[]> lst = new ArrayList<>();

        for(int i = 0; i < times.length; i++) {
            if(times[i].equals("x"))
                continue;

            int next = Integer.parseInt(times[i]);

            lst.add(new BigInteger[]{new BigInteger((next - i) + ""), new BigInteger(next + "")});
        }

        System.out.println(chineseRemainderTheorem(lst));
    }

    // Had to google this
    public BigInteger chineseRemainderTheorem(List<BigInteger[]> pairs) {
        BigInteger m = BigInteger.ONE;
        BigInteger total = BigInteger.ZERO;

        for(BigInteger[] arr : pairs) {
            m = m.multiply(arr[1]);
        }

        for(BigInteger[] arr : pairs) {
            BigInteger b = m.divide(arr[1]);
            BigInteger temp = arr[0].multiply(b);
            BigInteger modPow = b.modInverse(arr[1]);

            temp = temp.multiply(modPow).add(total);
            total = temp.mod(m);
        }

        return total;
    }
}
