package hw4;

import java.io.File;
import java.util.Scanner;

public class Greed {
    public static void main(String[] args) {
        // I prefer re-using variables instead of creating new ones
        // Therfore I only created one number of lines variable and scn variable
        int n = 5, p = 5, c = 5, numOfLines = 0;
        int[] demand, salary;
        Scanner scn;

        // Reads player demand File
        try {
            scn = new Scanner(new File("yearly_player_demand.txt"));
            scn.nextLine();
            while (scn.hasNextLine()) {
                scn.nextLine();
                numOfLines++;
            }
            demand = new int[numOfLines];
            scn = new Scanner(new File("yearly_player_demand.txt"));
            scn.nextLine();
            for (int i = 0; i < numOfLines; i++) {
                scn.nextInt();
                demand[i] = scn.nextInt();
            }
            // resetting number of lines to re use it
            numOfLines = 0;

        } catch (Exception e) {
            System.out.println("Yearly Player Demand File Not Found!");
            return;
        }
        // Reads player salary File
        try {
            scn = new Scanner(new File("players_salary.txt"));
            scn.nextLine();
            while (scn.hasNextLine()) {
                scn.nextLine();
                numOfLines++;
            }
            salary = new int[numOfLines];
            scn = new Scanner(new File("players_salary.txt"));
            scn.nextLine();
            for (int i = 0; i < numOfLines; i++) {
                scn.nextInt();
                salary[i] = scn.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Players Salary File Not Found!");
            return;
        }
        scn.close();

        System.out.println("Greedy Result:" + Greedy(n, p, c, demand, salary));
    }

    public static int Greedy(int n, int p, int c, int[] demand, int[] salary) {
        int diff, nextYearDiff, cost = 0, atHand = 0;
        for (int i = 0; i < n; i++) {
            diff = demand[i] - atHand - p;
            atHand = 0;
            if (diff >= 0) {
                cost += diff * c;
            } else {
                nextYearDiff = demand[i + 1] - p;
                if (i + 1 != n && nextYearDiff > 0) {
                    atHand = -diff < nextYearDiff ? -diff : nextYearDiff;
                    cost += salary[atHand - 1];
                }
            }
        }
        return cost;

    }
}