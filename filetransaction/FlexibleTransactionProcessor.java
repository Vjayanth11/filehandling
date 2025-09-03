package filetransaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlexibleTransactionProcessor {
    
    public static void main(String[] args) {
        // Default threshold or from command line argument
        double threshold = 1000.0;
        if (args.length > 0) {
            try {
                threshold = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid threshold value. Using default: $1000.0");
            }
        }
        
        processTransactions("transactions.txt", "filtered_transactions.txt", threshold);
    }
    
    public static void processTransactions(String inputFile, String outputFile, double threshold) {
        int totalRecords = 0;
        int filteredRecords = 0;
        
        System.out.println("Starting transaction processing...");
        System.out.println("Threshold amount: $" + threshold);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                totalRecords++;
                
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] fields = line.split(",");
                
                if (fields.length != 4) {
                    System.out.println("Skipping invalid record at line " + totalRecords);
                    continue;
                }
                
                try {
                    double amount = Double.parseDouble(fields[2].trim());
                    
                    if (amount > threshold) {
                        writer.write(line);
                        writer.newLine();
                        filteredRecords++;
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Skipping record with invalid amount at line " + totalRecords);
                }
            }
            
            System.out.println("\n=== Processing Summary ===");
            System.out.println("Input file: " + inputFile);
            System.out.println("Output file: " + outputFile);
            System.out.println("Total records: " + totalRecords);
            System.out.println("Filtered records: " + filteredRecords);
            System.out.println("Filter rate: " + String.format("%.2f%%", (double)filteredRecords/totalRecords*100));
            
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }
    }
}