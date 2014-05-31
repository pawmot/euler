package com.pawmot.euler;

import java.util.HashMap;
import java.util.Map;

public class Problem19 implements ProblemSolution {
    @Override
    public void printSolution() {
        Map<Integer, Integer> monthsToDays = new HashMap<>();
        monthsToDays.put(0, 31);
        monthsToDays.put(1, 28);
        monthsToDays.put(2, 31);
        monthsToDays.put(3, 30);
        monthsToDays.put(4, 31);
        monthsToDays.put(5, 30);
        monthsToDays.put(6, 31);
        monthsToDays.put(7, 31);
        monthsToDays.put(8, 30);
        monthsToDays.put(9, 31);
        monthsToDays.put(10, 30);
        monthsToDays.put(11, 31);

        // Sunday is 0, Monday is 1, ..., Saturday is 6
        int day = 2; // 1 Jan 1901 was a Tuesday

        int sundays = 0;
        for(int y = 1901; y < 2001; y++) {
            for(int m = 0; m < 12; m++) {
                day += monthsToDays.get(m);

                if(m == 1 && y%4 == 0) day++;

                day %= 7;

                if(day == 0) sundays++;
            }
        }

        // If the first day of XXI is a Sunday, then we had counted it in the loop - let's subtract it now.
        if(day == 0) sundays--;

        System.out.println(String.format("There were %1$d sundays on the first day of a month in the XX century", sundays));
    }
}
