package AoC2024_4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AoC2024_4_2 {
    private static int total = 0;

    public static void main(String[] args) {
        String filePath = "AoC2024_4_input.txt"; // Path to the text file
        String word = "MAS"; // Word to search

        try {
            char[][] grid = readGridFromFile(filePath);
            findWord(grid, word);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        System.out.printf("Total occurrences of '%s' in X-shape: %d%n", word, total);
    }

    private static char[][] readGridFromFile(String filePath) throws IOException {
        List<char[]> gridList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Convert the line to a character array and add to the list
                gridList.add(line.toCharArray());
            }
        }

        // Convert the list to a 2D array
        return gridList.toArray(new char[0][]);
    }

    public static void findWord(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 1; i < rows - 1; i++) { // Avoid edges
            for (int j = 1; j < cols - 1; j++) { // Avoid edges
                if (searchXShape(grid, word, i, j)) {
                    System.out.printf("Word '%s' found in X-shape at center (%d, %d)%n", word, i, j);
                    total++;
                }
            }
        }
    }
    
    private static boolean searchXShape(char[][] grid, String word, int x, int y) {
        // Boundary check
        if (x - 1 < 0 || x + 1 >= grid.length || y - 1 < 0 || y + 1 >= grid[0].length) {
            return false;
        }
    
        // Center must match word[1]
        if (grid[x][y] != word.charAt(1)) {
            return false;
        }
    
        // Diagonal positions
        char topLeft = grid[x - 1][y - 1];
        char topRight = grid[x - 1][y + 1];
        char bottomLeft = grid[x + 1][y - 1];
        char bottomRight = grid[x + 1][y + 1];
    
        // Check for valid X-shape configurations:
        // 1. Top-left & bottom-right form one pair (word[0] and word[2])
        // 2. Top-right & bottom-left form the other pair (word[0] and word[2])
        boolean validPair1 = (topLeft == word.charAt(0) && bottomRight == word.charAt(2)) ||
                             (topLeft == word.charAt(2) && bottomRight == word.charAt(0));
    
        boolean validPair2 = (topRight == word.charAt(0) && bottomLeft == word.charAt(2)) ||
                             (topRight == word.charAt(2) && bottomLeft == word.charAt(0));
    
        // Both pairs must be valid
        return validPair1 && validPair2;
    }    
}