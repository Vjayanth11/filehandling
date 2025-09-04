package com.json;

import com.csv.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadFromJson {

    public static void main(String[] args) {
        String file = "C:\\Users\\Jayanth\\mr360\\filehandling_csv\\src\\test\\resources\\employees.json";
        readFromJson(file);
    }

    public static void readFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Employee> employees = mapper.readValue(new File(filePath), new TypeReference<List<Employee>>() {});
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
