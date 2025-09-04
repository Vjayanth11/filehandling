package com.json;

import com.csv.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    private static final String TEST_FILE = "src/test/resources/test-employees.json";
    private static final String INVALID_FILE = "src/test/resources/invalid-employees.json";

    @Test
    public void testWriteAndReadJson() throws Exception {
        // Arrange - create employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Jayanth", 50000),
                new Employee(2, "karghe", 60000),
                new Employee(3, "ramesh", 55000)
        );

        ObjectMapper mapper = new ObjectMapper();

        // Act - write to JSON
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(TEST_FILE), employees);

        // Assert file exists
        File file = new File(TEST_FILE);
        assertTrue(file.exists(), "JSON file should be created");

        // Act - read back from JSON
        List<Employee> readEmployees = mapper.readValue(file, new TypeReference<List<Employee>>() {});

        // Assert - check size and content
        assertEquals(employees.size(), readEmployees.size(), "Employee list size should match");
        assertEquals(employees.get(0).getName(), readEmployees.get(0).getName(), "First employee name should match");
        assertEquals(employees.get(1).getSalary(), readEmployees.get(1).getSalary(), "Second employee salary should match");
    }

    @Test
    public void testReadInvalidJson() throws Exception {
        // Arrange - write invalid JSON into a file
        try (FileWriter writer = new FileWriter(INVALID_FILE)) {
            writer.write("{ invalid json: ,,, }"); // deliberately broken JSON
        }

        final ObjectMapper mapper = new ObjectMapper();
        final File file = new File(INVALID_FILE);

        // Act + Assert - expect a JsonProcessingException
        assertThrows(JsonProcessingException.class, () -> {
            mapper.readValue(file, new TypeReference<List<Employee>>() {});
        }, "Reading invalid JSON should throw JsonProcessingException");
    }
}
