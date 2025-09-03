package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReverseFileContent {
    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();
        
        // Read from input.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
        } catch (IOException e) {
            System.out.println("Error reading input.txt: " + e.getMessage());
            return;
        }
        
        // Reverse the content
        content.reverse();
        
        // Write reversed content to reversed.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reversed.txt"))) {
            writer.write(content.toString());
            System.out.println("File content reversed and saved to reversed.txt");
            
        } catch (IOException e) {
            System.out.println("Error writing to reversed.txt: " + e.getMessage());
        }
    }
}