package com.json;

import com.csv.Employee;   // reuse your Employee class
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WriteToJson {

    public static void main(String[] args) {
        String file = "C:\\Users\\Jayanth\\mr360\\filehandling_csv\\src\\test\\resources\\employees.json";
        writeToJson(file);
    }

    public static void writeToJson(String file) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Jayanth", 50000),
                new Employee(2, "Gopi", 60000),
                new Employee(3, "Chari", 55000)
        );

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(file), employees);
            System.out.println("JSON file written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

