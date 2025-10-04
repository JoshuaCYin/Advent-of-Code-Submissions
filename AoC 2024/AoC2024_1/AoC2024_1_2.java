package AoC2024_1;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AoC2024_1_2 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        String fn = "AoC2024_1_input.txt";
        Scanner input = new Scanner(new FileReader(fn));

        String line;
        while (input.hasNextLine()) {
            line = input.nextLine();
            String[] columns = line.split("\\s+");
            if (columns.length == 2) {  // ensure there are only two numbers in this row
                list1.add(Integer.parseInt(columns[0])); // append numbers in first column to list1
                list2.add(Integer.parseInt(columns[1])); // append numbers in second column to list2
            }
        }

        int totalSimilarity = 0;
        Integer list1int, list2int;
        int list2freq, tempSimilarity;
        for (int i = 0; i < list1.size(); i++) {    // iterate through list1
            list1int = list1.get(i);
            list2freq = Collections.frequency(list2, list1int); // find number of occurrences in list2
            totalSimilarity = totalSimilarity + (list1int * list2freq); // calculate similarity score
        }

        System.out.println("Total is: " + totalSimilarity);
        input.close();
    }
}
