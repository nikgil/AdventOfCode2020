package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day8 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        int acc = 0;
        String[] arr = input.split("\n");
        Node[] nodes = new Node[arr.length];

        for(int i = 0; i < arr.length; i++) {
            String[] sub = arr[i].split(" ");
            nodes[i] = new Node(sub[0], Integer.parseInt(sub[1]));
        }

        for(int i = 0; i < nodes.length; ) {
            Node next = nodes[i];

            if(next.visited) {
                System.out.println(acc);
                return;
            }

            next.visited = true;

            if(next.action.equals("nop")) {
                i++;
            } else if(next.action.equals("acc")) {
                acc += next.val;
                i++;
            } else {
                i += next.val;
            }
        }
    }

    @Override
    public void Part2(String input) {
        String[] arr = input.split("\n");
        Node[] nodes = new Node[arr.length];

        for(int i = 0; i < arr.length; i++) {
            String[] sub = arr[i].split(" ");
            nodes[i] = new Node(sub[0], Integer.parseInt(sub[1]));
        }

        for(Node n : nodes) {
            if(n.action.equals("nop")) {
                n.action = "jmp";
                int[] ar = recurse(nodes);

                if(ar[0] == 1) {
                    System.out.println(ar[1]);
                    return;
                }

                n.action = "nop";

                for(Node n2 : nodes) {
                    n2.visited = false;
                }
            } else if(n.action.equals("jmp")) {
                n.action = "nop";
                int[] ar = recurse(nodes);

                if(ar[0] == 1) {
                    System.out.println(ar[1]);
                    return;
                }

                n.action = "jmp";
                for(Node n2 : nodes) {
                    n2.visited = false;
                }
            }
        }
    }

    private int[] recurse(Node[] nodes) {
        int acc = 0;
        for(int i = 0; i < nodes.length; ) {
            Node next = nodes[i];

            if(next.visited) {
                return new int[]{-1, -1};
            }

            next.visited = true;

            if(next.action.equals("nop")) {
                i++;
            } else if(next.action.equals("acc")) {
                acc += next.val;
                i++;
            } else {
                i += next.val;
            }
        }

        return new int[]{1, acc};
    }

    private static class Node {
        boolean visited;
        String action;
        int val;

        Node(String action, int val) {
            this.action = action;
            this.val = val;
            this.visited = false;
        }
    }
}
