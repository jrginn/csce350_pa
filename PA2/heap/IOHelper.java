package PA2.heap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOHelper {
    /**
     * Creates a int array based on the contents of input.txt
     * @return array with ints in input.txt
     */
    public static int[] readArray(String filepath) {
        File f = new File(filepath);
        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            return null;
        }
        int n = Integer.parseInt(scan.nextLine());
        String[] nums = scan.nextLine().split("\\s");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }
        scan.close();
        return arr;
    }

    /**
     * Adds each element of arr to a line excluding first element
     * @param arr array of ints
     * @return space-separated string containing arr elements
     */
    public static String writeHeapArray(int[] arr) {
        String str = "";
        for(int i = 1; i < arr.length; i++) {
            str += arr[i] + " ";
        }
        return str;
    }
}
