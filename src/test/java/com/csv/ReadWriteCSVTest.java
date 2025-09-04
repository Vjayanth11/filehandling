package com.csv;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadWriteCSVTest {

    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary CSV file with sample content
        tempFile = Files.createTempFile("test-data", ".csv");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("id,name,age\n");
            writer.write("1,John,25\n");
            writer.write("2,Alice,30\n");
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testReadFromCsv() throws CsvValidationException {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the method
        ReadWriteCSV.readFromCsv(tempFile.toString());

        // Restore System.out
        System.setOut(oldOut);

        // Convert captured output to string
        String output = outputStream.toString();

        // Verify output contains expected lines
        assertTrue(output.contains("id, name, age"));
        assertTrue(output.contains("1, John, 25"));
        assertTrue(output.contains("2, Alice, 30"));
    }
}

