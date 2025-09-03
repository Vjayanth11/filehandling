package filemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileMerger {
    public static void main(String[] args) {
        String[] files = {"file.txt", "file1.txt", "file2.txt"};
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("merged_feedback.txt"))) {
            
            for (String file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                    
                } catch (IOException e) {
                    System.out.println("Could not read " + file);
                }
            }
            
            System.out.println("Files merged into merged_feedback.txt");
            
        } catch (IOException e) {
            System.out.println("Error creating output file");
        }
    }
}
