package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.Arrays;

public class Day11 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] split = input.split("\n");
        char[][] subsplit = new char[split.length][split[0].length()];

        for(int i = 0; i < subsplit.length; i++) {
            subsplit[i] = split[i].toCharArray();
        }

        char[][] prev;
        char[][] next = subsplit;

        for(;;) {
            prev = next;
            next = clone(prev);
            boolean didAlter = alter(1, prev, next);

            if(!didAlter) {
                break;
            }
        }

        int count = 0;

        for(char[] c : next) {
            for(char ch : c) {
                if(ch == '#') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    public void Part2(String input) {
        String[] split = input.split("\n");
        char[][] subsplit = new char[split.length][split[0].length()];

        for(int i = 0; i < subsplit.length; i++) {
            subsplit[i] = split[i].toCharArray();
        }

        char[][] prev;
        char[][] next = subsplit;

        for(;;) {
            prev = next;
            next = clone(prev);
            boolean didAlter = alter(2, prev, next);

            if(!didAlter) {
                break;
            }
        }

        int count = 0;

        for(char[] c : next) {
            for(char ch : c) {
                if(ch == '#') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private int[] getFirstNonEmpty(char[][] grid, int i, int j, int[] dir) {
        i += dir[0];
        j += dir[1];

        while(i >= 0 && j >= 0 && i < grid.length && j < grid[i].length) {
            if(grid[i][j] != '.') {
                return new int[]{i,j};
            }

            i += dir[0];
            j += dir[1];
        }

        return new int[]{-1, -1};
    }

    int[][] movements = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {1,1}, {1,-1}, {-1,1}};

    private boolean canSwitchL(int part, char[][] board, int i, int j) {
        for(int[] movement : movements) {
            int nextX = i + movement[0];
            int nextY = j + movement[1];

            if(part == 2) {
                int[] loc = getFirstNonEmpty(board, i, j, movement);
                nextX = loc[0];
                nextY = loc[1];
            }

            if(nextX < 0 || nextY < 0)
                continue;

            if(nextX >= board.length || nextY >= board[nextX].length)
                continue;

            if(board[nextX][nextY] == '#') {
                return false;
            }
        }

        return true;
    }

    private boolean canSwitchHash(int part, char[][] board, int i, int j) {
        int count = 0;

        for(int[] movement : movements) {
            int nextX = i + movement[0];
            int nextY = j + movement[1];

            if(part == 2) {
                int[] loc = getFirstNonEmpty(board, i, j, movement);
                nextX = loc[0];
                nextY = loc[1];
            }

            if(nextX < 0 || nextY < 0)
                continue;

            if(nextX >= board.length || nextY >= board[nextX].length)
                continue;

            if(board[nextX][nextY] == '#') {
                count++;
            }

            if(count == 4 + (part - 1))
                return true;
        }

        return false;
    }

    private boolean alter(int part, char[][] prev, char[][] alter) {
        boolean didChange = false;

        for(int i = 0; i < prev.length; i++) {
            for(int j = 0; j < prev[i].length; j++) {
                if(prev[i][j] == 'L') {
                    if(canSwitchL(part, prev, i, j)) {
                        alter[i][j] = '#';
                        didChange = true;
                    }
                } else if(prev[i][j] == '#') {
                    if(canSwitchHash(part, prev, i, j)) {
                        alter[i][j] = 'L';
                        didChange = true;
                    }
                }
            }
        }

        return didChange;
    }

    private char[][] clone(char[][] arr) {
        char[][] clone = new char[arr.length][arr[0].length];

        for(int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, clone[i], 0, arr[i].length);
        }

        return clone;
    }
}
