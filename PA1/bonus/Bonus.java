package PA1.bonus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import PA1.quicksort.*;

public class Bonus {

    public static void writeRandomFile(int size, int n) {
        File f = new File("./PA1/bonus/" + size + "/" + n + ".txt");
        double[] arr = new double[size];
        for(int i = 0; i < size; i++) {
            arr[i] = Math.random() * 10;
        }
        String str = QS.writeArray(arr);
        try {
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAllFiles() {
        for(int i = 10; i <= 100000; i *= 10) {
            for(int j = 0; j < 100; j++) {
                writeRandomFile(i, j);
            }
        }
    }
    

    public static void main(String[] args) {
        
    }
}
