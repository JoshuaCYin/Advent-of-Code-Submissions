package AoC2024_2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AoC2024_2_2 {
    public static void main(String[] args) throws IOException {
        String fn = "AoC2024_2_input.txt";
        Scanner input = new Scanner(new FileReader(fn));

        int safe = 0;
        String line;

        while (input.hasNextLine()) {
            line = input.nextLine();
            String[] rowStr = line.split("\\s+");
            int[] row = new int[rowStr.length];
            for (int k = 0; k < rowStr.length; k++) {
                row[k] = Integer.parseInt(rowStr[k]); // assuming valid input
            }

            boolean isSafe = checkSafety(row);
            if (!isSafe) {
                isSafe = checkIfCanBeMadeSafe(row);
            } 
            
            if (isSafe) {
                safe++;
                for (int s = 0; s < row.length; s++) {
                    System.out.print(row[s] + " ");
                }
                System.out.println();
            }
        }   

        System.out.println("Total safe is: " + safe);
        input.close();
    }

    private static boolean checkSafety(int[] row) {
        if (row[0] > row[1]) { // starting numbers are decreasing, so rest should be too if safe
            for (int i = 0; i < row.length - 1; i++) {
                if ((row[i] <= row[i+1]) || (row[i] - row[i+1] < 1) || (row[i] - row[i+1] > 3)) {
                    return false;
                }
            }
        }
        else if (row[0] < row[1]) { // starting numbers are increasing, so rest should be too if safe
            for (int i = 0; i < row.length - 1; i++) {
                if ((row[i] >= row[i+1]) || (row[i+1] - row[i] < 1) || (row[i+1] - row[i] > 3)) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    private static boolean checkIfCanBeMadeSafe(int[] row) {
        for (int i = 0; i < row.length; i++) {
            int[] tempRow = new int[row.length - 1];
            int index = 0;
            for (int j = 0; j < row.length; j++) {
                if (j != i) {
                    tempRow[index++] = row[j];
                }
            }
            if (checkSafety(tempRow)) {
                return true;
            }
        }
        return false;
    }
}

