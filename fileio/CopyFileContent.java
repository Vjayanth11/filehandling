package fileio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFileContent {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("sample.txt");
             FileWriter fw = new FileWriter("destination.txt")) {

            int data;
            while ((data = fr.read()) != -1) {
                fw.write(data);
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

