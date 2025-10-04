package AoC2024_3;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AoC2024_3_2 {
    public static String[] extractMulXYAndDoDont(String input) {
        List<String> resultList = new ArrayList<>();
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            resultList.add(matcher.group());
        }
        
        return resultList.toArray(new String[0]);
    }    

    public static void main(String[] args) throws IOException {
        String fn = "AoC2024_3_input.txt";
        Scanner input = new Scanner(new FileReader(fn));

        Stack<String> doDontStack = new Stack<>();

        boolean shouldDo = true;
        int total = 0;
        String line;
    
        while (input.hasNextLine()) {
            line = input.nextLine();
            
            // Parse line into string array with mul(x,y), do(), and don't()
            String[] results = extractMulXYAndDoDont(line);

            for (String part : results) {
                if (part.startsWith("mul(") && shouldDo) {
                    String[] nums = part.substring(4, part.length() - 1).split(",");
                    int x = Integer.parseInt(nums[0]);
                    int y = Integer.parseInt(nums[1]);
                    total += x * y;
                }
                else if (part.equals("don't()")) {
                    shouldDo = false;
                }
                else if (part.equals("do()")) {
                    shouldDo = true;
                }
            }
        }
        
        System.out.println("Total uncorrupted, enabled, and multiplied and added is: " + total);
    
        input.close();
    }
    
}