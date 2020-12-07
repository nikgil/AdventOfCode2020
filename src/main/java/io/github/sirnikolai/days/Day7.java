package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day7 extends AbstractSolution {
    @Override
    public void Part1(String input) {
        String[] arr = input.split("\n");
        Map<String, Bag> bags = new HashMap<>();

        for(String s : arr) {
            String[] sub = s.split(" contain ");
            String name = sub[0].substring(0, sub[0].indexOf(" bags"));

            bags.put(name, new Bag(name, sub[1].split(", ")));
        }

        Set<String> canContain = new HashSet<>();

        for(Bag b : bags.values()) {
            if(b.map.containsKey("shiny gold")) {
                canContain.add(b.name);
            }
        }

        int prevSize = canContain.size();

        do {
            prevSize = canContain.size();
            Set<String> temp = new HashSet<>();

            for(Bag b : bags.values()) {
                for(String s : canContain) {
                    if(b.map.containsKey(s)) {
                        temp.add(b.name);
                    }
                }
            }

            canContain.addAll(temp);
        } while(canContain.size() != prevSize);

        System.out.println(canContain.size());
    }

    @Override
    public void Part2(String input) {
        String[] arr = input.split("\n");
        Map<String, Bag> bags = new HashMap<>();

        for(String s : arr) {
            String[] sub = s.split(" contain ");
            String name = sub[0].substring(0, sub[0].indexOf(" bags"));

            bags.put(name, new Bag(name, sub[1].split(", ")));
        }

        System.out.println(getCount(bags, "shiny gold") - 1);
    }

    private int getCount(Map<String, Bag> bags, String target) {
        int total = 1;
        Bag targetBag = bags.get(target);

        for(Map.Entry<String, Integer> e : targetBag.map.entrySet()) {
            total += e.getValue() * getCount(bags, e.getKey());
        }

        return total;
    }

    private static class Bag {
        String name;
        Map<String, Integer> map;

        public Bag(String name, String[] contents) {
            this.name = name;
            this.map = new HashMap<>();

            if(!contents[0].equals("no other bags.")) {
                split(contents, map);
            }
        }

        private void split(String[] contents, Map<String, Integer> map) {
            for(String s : contents) {
                String[] sub = s.split(" ");

                StringBuilder sb = new StringBuilder();

                for(int i = 1; i < sub.length - 1; i++) {
                    sb.append(sub[i]).append(" ");
                }

                map.put(sb.toString().trim(), Integer.parseInt(sub[0]));
            }
        }

        @Override
        public String toString() {
            return "Bag{" +
                    "name='" + name + '\'' +
                    ", map=" + map +
                    '}';
        }
    }
}
