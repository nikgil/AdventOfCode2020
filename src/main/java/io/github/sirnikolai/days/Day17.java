package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        String[] arr = input.split("\n");
        char[][] arr2 = new char[arr.length][arr.length];
        List<char[][]> cycle = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i].toCharArray();
        }

        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length / 2) {
                cycle.add(pad(arr2));
                continue;
            }

            cycle.add(createBase(arr2.length + 2));
        }

        for(int i = 0; i < 6; i++) {
            cycle = doCycle(cycle);

            for(int j = 0; j < cycle.size(); j++) {
                cycle.set(j, pad(cycle.get(j)));
            }

            cycle.add(0, createBase(cycle.get(0).length));
            cycle.add(createBase(cycle.get(0).length));
        }

        System.out.println(count(cycle));
    }

    private int count(List<char[][]> cube) {
        int tally = 0;

        for(char[][] map : cube) {
            for(char[] layer : map) {
                for(char c : layer) {
                    if(c == '#') {
                        tally++;
                    }
                }
            }
        }

        return tally;
    }

    private void prettyPrint(List<char[][]> cube) {
        for(char[][] map : cube) {
            for(char[] layer : map) {
                System.out.println(Arrays.toString(layer));
            }
            System.out.println();
        }
    }

    private List<char[][]> doCycle(List<char[][]> cube) {
        List<char[][]> cloned = clone(cube);

        for (int z = 0; z < cube.size(); z++) {
            char[][] layer = cube.get(z);

            for (int x = 0; x < layer.length; x++) {
                for (int y = 0; y < layer.length; y++) {
                    int active = countActiveNeighbours(cube, x, y, z);

                    if (layer[x][y] == '#') {
                        if (active != 2 && active != 3) {
                            cloned.get(z)[x][y] = '.';
                        }
                    } else {
                        if (active == 3) {
                            cloned.get(z)[x][y] = '#';
                        }
                    }
                }
            }
        }

        return cloned;
    }

    private int[][] paths = {{0,1}, {1,0}, {0,-1}, {-1,0}, {0,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};

    private int countActiveNeighbours(List<char[][]> cube, int x, int y, int z) {
        int total = 0;
        char[][] currentLayer = null;

        //do prev
        if(z > 0) {
            currentLayer = cube.get(z-1);

            for(int[] path : paths) {
                int x2 = path[0] + x;
                int y2 = path[1] + y;

                if(x2 < 0 || x2 >= currentLayer.length) {
                    continue;
                }

                if(y2 < 0 || y2 >= currentLayer.length) {
                    continue;
                }

                if(currentLayer[x2][y2] == '#') {
                    total++;
                }
            }
        }

        //do current
        currentLayer = cube.get(z);

        for(int[] path : paths) {
            int x2 = path[0] + x;
            int y2 = path[1] + y;

            if(x2 == x && y2 == y) {
                continue;
            }

            if(x2 < 0 || x2 >= currentLayer.length) {
                continue;
            }

            if(y2 < 0 || y2 >= currentLayer.length) {
                continue;
            }

            if(currentLayer[x2][y2] == '#') {
                total++;
            }
        }

        //do next
        if(z < cube.size() - 1) {
            currentLayer = cube.get(z+1);

            for(int[] path : paths) {
                int x2 = path[0] + x;
                int y2 = path[1] + y;

                if(x2 < 0 || x2 >= currentLayer.length) {
                    continue;
                }

                if(y2 < 0 || y2 >= currentLayer.length) {
                    continue;
                }

                if(currentLayer[x2][y2] == '#') {
                    total++;
                }
            }
        }

        return total;
    }

    private char[][] pad(char[][] layer) {
        char[][] padded = new char[layer.length + 2][layer.length + 2];

        for(char[] arr : padded) {
            Arrays.fill(arr, '.');
        }

        for(int i = 0; i < layer.length; i++) {
            for(int j = 0; j < layer.length; j++) {
                padded[i+1][j+1] = layer[i][j];
            }
        }

        return padded;
    }

    private List<char[][]> clone(List<char[][]> cube) {
        List<char[][]> lst = new ArrayList<>();

        for(char[][] map : cube) {
            lst.add(clone(map));
        }

        return lst;
    }

    private char[][] createBase(int n) {
        char[][] clone = new char[n][n];

        for(char[] arr : clone) {
            Arrays.fill(arr, '.');
        }

        return clone;
    }

    private char[][] clone(char[][] map) {
        char[][] clone = new char[map.length][map[0].length];

        for(int i = 0; i < map.length; i++) {
            clone[i] = Arrays.copyOf(map[i], map[i].length);
        }

        return clone;
    }

    @Override
    public void Part2(String input) {
        // This was helped on https://reddit.com/r/adventofcode
        String[] arr = input.split("\n");
        Map<Point, Boolean> map = new HashMap<>();

        for(int j = 0; j < arr.length; j++) {
            String s = arr[j];

            for(int i = 0; i < s.length(); i++) {
                map.put(new Point(j, i, 0, 0, s.charAt(i) == '#'), s.charAt(i) == '#');
            }
        }

        for(int i = 0; i < 6; i++) {
            map = runCycle(map);
        }

        System.out.println(countPoints(map));
    }

    private Map<Point, Boolean> runCycle(Map<Point, Boolean> map) {
        int[] mins = getMins(map.keySet());
        int[] maxes = getMaxes(map.keySet());
        Map<Point, Boolean> clone = new HashMap<>();

        for(int x = mins[0] - 1; x < maxes[0] + 2; x++) {
            for(int y = mins[1] - 1; y < maxes[1] + 2; y++) {
                for(int z = mins[2] - 1; z < maxes[2] + 2; z++) {
                    for(int w = mins[3] - 1; w < maxes[3] + 2; w++) {
                        Point next = new Point(x,y,z,w,false);

                        if(map.containsKey(next)) {
                            next.active = map.get(next);
                        }

                        int count = getCount(map, next);

                        if(next.active) {
                            if(count != 2 && count != 3) {
                                clone.put(next, false);
                            } else {
                                clone.put(next, true);
                            }
                        } else {
                            if(count == 3) {
                                clone.put(next, true);
                            } else {
                                clone.put(next, false);
                            }
                        }
                    }
                }
            }
        }

        return clone;
    }

    private int getCount(Map<Point, Boolean> map, Point p) {
        int total = 0;

        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -1; z <= 1; z++) {
                    for(int w = -1; w <= 1; w++) {
                        if(
                                x == 0 &&
                                y == 0 &&
                                z == 0 &&
                                w == 0) {
                            continue;
                        }

                        Point next = new Point(p.x + x, p.y + y, p.z + z, p.w + w,false);

                        if(map.containsKey(next)) {
                            next.active = map.get(next);
                        }

                        if(next.active) {
                            total++;
                        }
                    }
                }
            }
        }

        return total;
    }

    private int[] getMins(Set<Point> set) {
        int[] arr = new int[4];

        for(Point p : set) {
            arr[0] = Math.min(arr[0], p.x);
            arr[1] = Math.min(arr[1], p.y);
            arr[2] = Math.min(arr[2], p.z);
            arr[3] = Math.min(arr[3], p.w);
        }

        return arr;
    }

    private int[] getMaxes(Set<Point> set) {
        int[] arr = new int[4];

        for(Point p : set) {
            arr[0] = Math.max(arr[0], p.x);
            arr[1] = Math.max(arr[1], p.y);
            arr[2] = Math.max(arr[2], p.z);
            arr[3] = Math.max(arr[3], p.w);
        }

        return arr;
    }

    private long countPoints(Map<Point, Boolean> map) {
        return map.values().stream().filter(b -> b).count();
    }

    private static class Point {
        int x;
        int y;
        int z;
        int w;
        boolean active;

        public Point(int x, int y, int z, int w, boolean active) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
            this.active = active;
        }

        private Point add(int x, int y, int z, int w) {
            return new Point(this.x + x, this.y + y, this.z + z, this.w + w, false);
        }

        private double manhattanDistance(Point other) {
            return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2) + Math.pow(other.w - w, 2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y &&
                    z == point.z &&
                    w == point.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, w);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", w=" + w +
                    ", active=" + active +
                    '}';
        }
    }
}
