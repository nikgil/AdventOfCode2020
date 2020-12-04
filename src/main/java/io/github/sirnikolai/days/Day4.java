package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day4 extends AbstractSolution {

    Set<String> targetFields = new HashSet<>();

    {
        String split = "byr\n" +
                "iyr\n" +
                "eyr\n" +
                "hgt\n" +
                "hcl\n" +
                "ecl\n" +
                "pid\n" +
                "cid";

        targetFields.addAll(Arrays.asList(split.split("\n")));
    }

    @Override
    public void Part1(String input) {
        String[] split = input.split("\n\n");
        int valid = 0;

        for(String s : split) {
            s = s.replace("\n", " ");
           if(isValid(s)) {
               valid++;
           }
        }

        System.out.println(valid);
    }

    private boolean isValid(String cred) {
        String[] split = cred.split(" ");
        Set<String> set = new HashSet<>();

        for(String s : split) {
            if(targetFields.contains(s.split(":")[0])) {
                set.add(s.split(":")[0]);
            }
        }

        if(targetFields.size() - set.size() == 1) {
            if(!set.contains("cid"))
                return true;

            return false;
        }

        return targetFields.size() == set.size();
    }

    @Override
    public void Part2(String input) {
        String[] split = input.split("\n\n");
        int valid = 0;

        for(String s : split) {
            s = s.replace("\n", " ");
            if(isValid2(s)) {
                valid++;
            }
        }

        System.out.println(valid);
    }

    private boolean isValid2(String cred) {
        if(!isValid(cred)) {

            return false;
        }

        String[] split = cred.split(" ");

        for(String s : split) {
            String[] sub = s.split(":");

            switch (sub[0]) {
                case "byr": {
                    int i = Integer.parseInt(sub[1]);

                    if (i < 1920 || i > 2002)
                        return false;
                    break;
                }
                case "iyr": {
                    int i = Integer.parseInt(sub[1]);

                    if (i < 2010 || i > 2020)
                        return false;
                    break;
                }
                case "hgt":
                    if (sub[1].endsWith("cm")) {
                        int i = Integer.parseInt(sub[1].substring(0, sub[1].indexOf("cm")));

                        if (i < 150 || i > 193)
                            return false;
                    } else if (sub[1].endsWith("in")) {
                        int i = Integer.parseInt(sub[1].substring(0, sub[1].indexOf("in")));

                        if (i < 59 || i > 76)
                            return false;
                    } else {
                        return false;
                    }
                    break;
                case "hcl":
                    if (sub[1].length() != 7)
                        return false;

                    for (int i = 1; i < sub[1].length(); i++) {
                        if (!Character.isLetterOrDigit(sub[1].charAt(i)))
                            return false;
                    }
                    break;
                case "ecl":
                    if (!(new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth")).contains(sub[1])))
                        return false;
                    break;
                case "pid":
                    if (sub[1].length() != 9)
                        return false;

                    for (char c : sub[1].toCharArray()) {
                        if (!Character.isDigit(c))
                            return false;
                    }
                    break;
                case "eyr": {
                    int i = Integer.parseInt(sub[1]);

                    if (i < 2020 || i > 2030)
                        return false;
                    break;
                }
            }
        }

        return true;
    }
}
