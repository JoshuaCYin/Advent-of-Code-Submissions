package AoC2024_3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoC2024_3_1 {
    public static List<String[]> extractMulXY(String input) {
        String regex = "mul\\(\\s*(\\w+)\\s*,\\s*(\\w+)\\s*\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        List<String[]> results = new ArrayList<>();
        while (matcher.find()) {
            String x = matcher.group(1);
            String y = matcher.group(2);
            results.add(new String[]{x, y});
        }
        return results;
    }

    public static void main(String[] args) throws IOException {
        String fn = "AoC2024_3_input.txt";
        Scanner input = new Scanner(new FileReader(fn));
    
        int total = 0;
        String line;
    
        while (input.hasNextLine()) {
            line = input.nextLine();
            List<String[]> results = extractMulXY(line);
            for (String[] result : results) {
                int x = Integer.parseInt(result[0]);
                int y = Integer.parseInt(result[1]);
                total = total + (x * y);
            }
        }
        System.out.println("Total uncorrupted multiplied and added is: " + total);
    
        input.close();
    }
    
}