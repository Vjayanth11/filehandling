package fileio;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
    public static void main(String[] args) {
        int wordCount = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("text_file.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                wordCount += words.length;
            }
            System.out.println("Word count: " + wordCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
