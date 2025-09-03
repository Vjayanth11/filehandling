package fileio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteUserInputToFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text to save (type 'exit' to finish):");

        try (FileWriter fw = new FileWriter("user_input.txt")) {
            String input;
            while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
                fw.write(input + System.lineSeparator());
            }
            System.out.println("User input saved to user_input.txt");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

