package PA3.horspool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Horspool {
    
    /**
     * Converts lowercase letter/space to index for shift table
     * @param c character in shift table
     * @return index of character
     */
    public static int charToIndex(char c) {
        if(c == ' ') {
            return 26;
        }
        // (int) 'a' = 97
        return (int) c - 97;
    }

    /**
     * Creates a shift table for Horspool's algorithm
     * @param pattern pattern to be searched
     * @return int array of shift values for each character
     */
    public static int[] createShiftTable(String pattern) {
        int[] table = new int[27];
        for(int i = 0; i < table.length; i++) {
            table[i] = pattern.length();
        }
        for(int i = 1; i < pattern.length(); i++) {
            if(table[charToIndex(pattern.charAt(pattern.length()-i-1))] == pattern.length()) {
                table[charToIndex(pattern.charAt(pattern.length()-i-1))] = i;
            }
        }
        return table;
    }

    /**
     * Implements Horspool's according to the book
     * @param pattern string to be searched for
     * @param text string being searched
     * @return index of start of pattern in text, -1 if pattern is absent from text
     */
    public static int checkMatch(String pattern, String text) {
        int[] shift = createShiftTable(pattern);
        int r = pattern.length()-1;
        while(r <= text.length() - 1) {
            int matchCount = 0;
            // loops until either pattern.length() matches are found or mismatch occurs
            while(matchCount <= pattern.length() - 1 && pattern.charAt(pattern.length() - 1 - matchCount) == text.charAt(r - matchCount)) {
                matchCount++;
            }
            // all of pattern was in text consecutively
            if(matchCount == pattern.length()) {
                return r - pattern.length() + 1;
            }
            // uses shift table to move right index further
            r = r + shift[charToIndex(text.charAt(r))];
        }
        return -1;
    }

    /**
     * Creates a int array based on the contents of input.txt
     * @return array with ints in input.txt
     */
    public static String[] readInput(String filepath) {
        File f = new File(filepath);
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        String[] arr = new String[2];
        arr[0] = scan.nextLine().toLowerCase();
        arr[1] = scan.nextLine().toLowerCase();
        scan.close();
        return arr;
    }

    public static void main(String[] args) {
        String[] inputs = readInput("./PA3/horspool/input.txt");
        String pattern = inputs[0];
        String text = inputs[1];
        long start = System.nanoTime();
        int index = checkMatch(pattern, text);
        System.out.println("Execution time: " + (System.nanoTime() - start));
        File f = new File("./PA3/horspool/output.txt");
        FileWriter outputWriter;
        try {
            outputWriter = new FileWriter(f);
            outputWriter.write(Integer.toString(index));
            outputWriter.close();
        }
        catch(IOException e) {
            System.out.println("min_output.txt does not exist");
            return;
        }
    }
}
