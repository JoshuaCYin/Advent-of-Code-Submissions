package AoC2024_1;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AoC2024_1_1 {
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

        Integer smallest1, smallest2;
        int distance;
        int total = 0;
        while (!(list1.isEmpty() && list2.isEmpty())) {
            smallest1 = Collections.min(list1); // find smallest in left list, list1
            smallest2 = Collections.min(list2); // find smallest in right list, list2
            list1.remove(smallest1);
            list2.remove(smallest2);
            distance = Math.abs(smallest1 - smallest2); // find distance between each pair of smallest numbers
            total = total + distance;
        }

        System.out.println("Total is: " + total);
        input.close();
    }
}
