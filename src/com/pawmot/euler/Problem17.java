package com.pawmot.euler;

import java.util.HashMap;
import java.util.Map;

public class Problem17 implements ProblemSolution {
    @Override
    public void printSolution() {
        int count = 0;

        // This could be actually done without ever generating the names - it would suffice to just count the length of specific words without the words themselves specified.
        // Nevertheless, I think that this solution is more fun, mainly because of the name generation.
        for(int i = 1; i <= 1000; i++) {
            String name = IntegerToStringConverter.convert(i, true);
            System.out.println(i + " - " + name);
            String onlyLetters = name.replaceAll("[\\s-]*", "");

            count += onlyLetters.length();
        }

        System.out.println(String.format("There is a total sum of %1$d letters in all the numbers from 1 to 1000", count));
    }

    // works only up to 999,999
    // returns British numerals (e.g. "four hundred and thirty-two")
    private static final class IntegerToStringConverter {
        private static final String THOUSAND = "thousand";
        private static final String HUNDRED = "hundred";
        private static final String AND = "and";
        private static final Map<Integer, String> secondDigitNames;
        private static final Map<Integer, String> firstDigitNames;
        private static final Map<Integer, String> twoDigitSpecialCases;

        static {
            secondDigitNames = new HashMap<>();
            secondDigitNames.put(2, "twenty");
            secondDigitNames.put(3, "thirty");
            secondDigitNames.put(4, "forty");
            secondDigitNames.put(5, "fifty");
            secondDigitNames.put(6, "sixty");
            secondDigitNames.put(7, "seventy");
            secondDigitNames.put(8, "eighty");
            secondDigitNames.put(9, "ninety");

            firstDigitNames = new HashMap<>();
            firstDigitNames.put(0, "zero");
            firstDigitNames.put(1, "one");
            firstDigitNames.put(2, "two");
            firstDigitNames.put(3, "three");
            firstDigitNames.put(4, "four");
            firstDigitNames.put(5, "five");
            firstDigitNames.put(6, "six");
            firstDigitNames.put(7, "seven");
            firstDigitNames.put(8, "eight");
            firstDigitNames.put(9, "nine");

            twoDigitSpecialCases = new HashMap<>();
            twoDigitSpecialCases.put(10, "ten");
            twoDigitSpecialCases.put(11, "eleven");
            twoDigitSpecialCases.put(12, "twelve");
            twoDigitSpecialCases.put(13, "thirteen");
            twoDigitSpecialCases.put(14, "fourteen");
            twoDigitSpecialCases.put(15, "fifteen");
            twoDigitSpecialCases.put(16, "sixteen");
            twoDigitSpecialCases.put(17, "seventeen");
            twoDigitSpecialCases.put(18, "eighteen");
            twoDigitSpecialCases.put(19, "nineteen");
        }

        public static String convert(int number, boolean useAnd) {
            if(number == 0) {
                return firstDigitNames.get(number);
            }

            StringBuilder sb = new StringBuilder();

            if(number > 999) {
                String thousands = convert(number / 1000, false);
                sb.append(thousands);
                sb.append(' ');
                sb.append(THOUSAND);
                sb.append(' ');
            }

            number = number % 1000;
            int hundreds = number / 100;
            int rest = number - hundreds * 100;

            if(hundreds > 0) {
                sb.append(firstDigitNames.get(hundreds));
                sb.append(' ');
                sb.append(HUNDRED);
                sb.append(' ');
                if(rest > 0 && useAnd) {
                    sb.append(AND);
                    sb.append(' ');
                }
            }

            if(rest > 0) {
                if(twoDigitSpecialCases.containsKey(rest)) {
                    sb.append(twoDigitSpecialCases.get(rest));
                } else {
                    int tens = rest / 10;
                    rest -= tens * 10;

                    if(tens > 0) {
                        sb.append(secondDigitNames.get(tens));

                        if(rest>0) {
                            sb.append('-');
                        }
                    }

                    if(rest>0) {
                        sb.append(firstDigitNames.get(rest));
                    }
                }
            }

            return sb.toString();
        }
    }
}
