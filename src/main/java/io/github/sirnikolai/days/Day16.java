package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends AbstractSolution {

    @Override
    public void Part1(String input) {
        Set<Integer> set = new HashSet<>();
        String[] in = input.split("\n\n");

        parseHeader(in[0], set);

        String[] near = in[2].split("\n");
        long tally = 0;

        for(int i = 1; i < near.length; i++) {
            String[] split = near[i].split(",");

            for(String s : split) {
                int next = Integer.parseInt(s);
                if(!set.contains(next)) {
                    tally += next;
                }
            }
        }

        System.out.println(tally);
    }

    private void parseHeader(String header, Set<Integer> set) {
        String[] split = header.split("\n");

        for(String s : split) {
            String nums = s.substring(s.indexOf(": ") + 2);
            String[] ranges = nums.split(" or ");

            for(String s2 : ranges) {
                String[] split2 = s2.split("-");

                for(int i = Integer.parseInt(split2[0]); i <= Integer.parseInt(split2[1]); i++) {
                    set.add(i);
                }
            }
        }
    }

    private void parseHeader2(String header, Map<String, Set<Integer>> map) {
        String[] split = header.split("\n");

        for(String s : split) {
            String title = s.substring(0, s.indexOf(":"));

            String nums = s.substring(s.indexOf(": ") + 2);
            String[] ranges = nums.split(" or ");

            Set<Integer> set = new HashSet<>();
            map.putIfAbsent(title, set);

            for(String s2 : ranges) {
                String[] split2 = s2.split("-");

                for(int i = Integer.parseInt(split2[0]); i <= Integer.parseInt(split2[1]); i++) {
                    set.add(i);
                }
            }
        }
    }

    @Override
    public void Part2(String input) {
        Map<String, Set<Integer>> map = new HashMap<>();
        Set<Integer> predicate = new HashSet<>();
        String[] in = input.split("\n\n");

        parseHeader2(in[0], map);
        parseHeader(in[0], predicate);

        String[] near = in[2].split("\n");
        String[] myNums = in[1].split("\n");
        List<Integer> myList = Arrays.stream(myNums[1].split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        Map<Integer, Set<String>> mapped = new HashMap<>();
        List<List<Integer>> lst = new ArrayList<>();

        for(int i = 0; i < myList.size(); i++) {
            mapped.putIfAbsent(myList.get(i), new HashSet<>());
            lst.add(new ArrayList<>());
            lst.get(i).add(myList.get(i));
        }

        for(int i = 1; i < near.length; i++) {
            String[] split = near[i].split(",");
            boolean isValid = true;

            for(String s : split) {
                int next = Integer.parseInt(s);
                if(!predicate.contains(next)) {
                    isValid = false;
                    break;
                }
            }

            if(isValid) {
                for(int j = 0; j < split.length; j++) {
                    int next = Integer.parseInt(split[j]);
                    lst.get(j).add(next);
                }
            }
        }

        for(Map.Entry<String, Set<Integer>> e : map.entrySet()) {
            for(List<Integer> lst2 : lst) {
                if(e.getValue().containsAll(lst2)) {
                    mapped.get(lst2.get(0)).add(e.getKey());
                }
            }
        }

        long total = 1;

        while(true) {
            boolean changed = false;

            for(Map.Entry<Integer, Set<String>> e : mapped.entrySet()) {
                if(e.getValue().size() == 1) {
                    changed = true;
                    String val = (String) e.getValue().toArray()[0];

                    if(val.startsWith("departure")) {
                        total *= e.getKey();
                    }

                    e.getValue().clear();

                    for(Set<String> set : mapped.values()) {
                        set.remove(val);
                    }
                }
            }

            if(!changed) {
                break;
            }
        }

        System.out.println(total);
    }
}
