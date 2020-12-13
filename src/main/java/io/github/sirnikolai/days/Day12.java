package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

public class Day12 extends AbstractSolution {

    enum dir {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }


    @Override
    public void Part1(String input) {
        int right = 0;
        int up = 0;
        int dir = 0;
        String[] arr = input.split("\n");

        for(String s : arr) {
            char action = s.charAt(0);
            int amount = Integer.parseInt(s.substring(1));

            if(action == 'F') {
                switch(getDir(dir)) {
                    case EAST:
                        action = 'E';
                        break;
                    case WEST:
                        action = 'W';
                        break;
                    case NORTH:
                        action = 'N';
                        break;
                    case SOUTH:
                        action = 'S';
                        break;
                }
            }

            switch (action) {
                case 'E':
                    right += amount;
                    break;
                case 'W':
                    right -= amount;
                    break;
                case 'N':
                    up += amount;
                    break;
                case 'S':
                    up -= amount;
                    break;
                case 'R':
                    dir -= amount;
                    break;
                case 'L':
                    dir += amount;
                    break;
            }
        }

        System.out.println(Math.abs(right) + Math.abs(up));
    }

    private dir getDir(int i) {
        while(i < 0)
            i += 360;

        if(i % 360 == 0)
            return dir.EAST;
        if(i % 360 == 180)
            return dir.WEST;
        if(i % 360 == 90)
            return dir.NORTH;

        return dir.SOUTH;
    }

    @Override
    public void Part2(String input) {
        int shipRight = 0;
        int shipUp = 0;

        int right = 10;
        int up = 1;

        String[] arr = input.split("\n");

        for(String s : arr) {
            char action = s.charAt(0);
            int amount = Integer.parseInt(s.substring(1));

            if(action == 'F') {
                shipRight += right * amount;
                shipUp += up * amount;
            }

            switch (action) {
                case 'E':
                    right += amount;
                    break;
                case 'W':
                    right -= amount;
                    break;
                case 'N':
                    up += amount;
                    break;
                case 'S':
                    up -= amount;
                    break;
                case 'R':
                    while(amount > 0) {
                        int rTemp = right;

                        right = up;
                        up = -rTemp;

                        amount -= 90;
                    }
                    break;
                case 'L':
                    while(amount > 0) {
                        int uTemp = up;

                        up = right;
                        right = -uTemp;

                        amount -= 90;
                    }
                    break;
            }
        }

        System.out.println(Math.abs(shipRight) + Math.abs(shipUp));
    }
}
