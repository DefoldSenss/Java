package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PR_17_18 {

    public static void main(String[] args) {
        new Task1().check("abcdefghijklmnopqrstuv18340");
        new Task2().check("aE:dC:cA:56:76:54");
        new Task3().check("23.78 USD 23.78 USD");
    }

    public static class Task1 {
        public void check(String s) {
            String right;
            right = "abcdefghijklmnopqrstuv18340";
            Pattern pattern = Pattern.compile(right);
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches())
                System.out.println("true");
            else
                System.out.println("false");
        }
    }

    public static class Task2 {
        public void check(String s) {
            String regex, in;
            regex = "([a-f]+[A-F]+:){3}+([0-9]{2}+:){2}+[0-9]{2}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches())
                System.out.println("true");
            else
                System.out.println("false");
        }
    }

    public static class Task3 {
        public void check(String s) {
            String regex = "([1-9]\\d+[.]?\\d{0,2})([ ])([U][S][D]|[R][U][R]|[E][U])";
            Pattern p1 = Pattern.compile(regex);
            Matcher m1 = p1.matcher(s);
            while (m1.find())
                System.out.println(m1.group());
        }
    }
}
