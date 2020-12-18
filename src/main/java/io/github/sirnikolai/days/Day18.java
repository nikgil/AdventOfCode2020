package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;

public class Day18 extends AbstractSolution {
    private int x = 0;

    @Override
    public void Part1(String input) {
        String[] arr = input.split("\n");
        long tally = 0;

        for(String s : arr) {
            x = 0;
            tally += getResult(s);
        }

        System.out.println(tally);
    }

    private long getResult(String q) {
        q = q.replaceAll(" ", "");
        long tally = 0;
        char sign = '+';

        for( ;x < q.length(); x++) {
            if(q.charAt(x) == ')') {
                x++;
                break;
            }

            if(q.charAt(x) == '(') {
                x++;
                tally = getResult(tally, getResult(q), sign);
                x--;
                continue;
            }

            if(Character.isDigit(q.charAt(x))) {
                tally = getResult(tally, q.charAt(x) - '0', sign);
            } else {
                sign = q.charAt(x);
            }
        }
//        System.out.println(tally);
        return tally;
    }

    private long getResult(long tally, long val, char sign) {
        switch (sign) {
            case '+':
                tally += val;
                break;
            case '-':
                tally -= val;
                break;
            case '*':
                tally *= val;
                break;
        }

        return tally;
    }

    private long getResult2(String q) {
        q = q.replaceAll(" ", "");
        Stack<Long> tally = new Stack<>();
        char sign = '+';

        for( ;x < q.length(); x++) {
            if(q.charAt(x) == ')') {
                x++;
                break;
            }

            if(q.charAt(x) == '(') {
                x++;
                long result = getResult2(q);

                if(tally.isEmpty()) {
                    tally.push(result);
                } else {
                    if(sign == '+') {
                        tally.push(tally.pop() + result);
                    } else {
                        tally.push(result);
                    }
                }
                x--;
                continue;
            }

            if(Character.isDigit(q.charAt(x))) {
                if(tally.isEmpty()) {
                    tally.push((long) (q.charAt(x) - '0'));
                } else {
                    if(sign == '+') {
                        tally.push(tally.pop() + (q.charAt(x) - '0'));
                    } else {
                        tally.push((long) (q.charAt(x) - '0'));
                    }
                }
            } else {
                sign = q.charAt(x);
            }
        }

        long total = 1;

        while(!tally.isEmpty()) {
            total *= tally.pop();
        }

        return total;
    }

    @Override
    public void Part2(String input) {
        String[] arr = input.split("\n");
        long tally = 0;

        for(String s : arr) {
            x = 0;
            tally += getResult2(s);
//            System.out.println(getResult2(s));
        }

        System.out.println(tally);
    }
}
