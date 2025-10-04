package AoC2024_4;

// Used ChatGPT to generate almost all code

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AoC2024_4_1 {
    private static final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static int total = 0;

    public static void main(String[] args) {
        String filePath = "AoC2024_4_input.txt"; // Path to the text file
        String word = "XMAS"; // Word to search

        try {
            char[][] grid = readGridFromFile(filePath);
            findWord(grid, word);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        System.out.println(total);
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
        int wordLength = word.length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == word.charAt(0)) { // Start matching from the first letter
                    for (int dir = 0; dir < 8; dir++) {
                        if (searchInDirection(grid, word, i, j, dir)) {
                            System.out.printf("Word found at (%d, %d) in direction %d%n", i, j, dir);
                            total++;
                        }
                    }
                }
            }
        }
    }

    private static boolean searchInDirection(char[][] grid, String word, int x, int y, int direction) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLength = word.length();

        for (int k = 0; k < wordLength; k++) {
            int newX = x + k * dx[direction];
            int newY = y + k * dy[direction];

            // Check boundaries
            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols) {
                return false;
            }

            // Check character match
            if (grid[newX][newY] != word.charAt(k)) {
                return false;
            }
        }

        return true; // Word matched in this direction
    }
}
