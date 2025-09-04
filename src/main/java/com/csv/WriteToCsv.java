package com.csv;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class WriteToCsv {

	public static void main(String[] args) {
		String file = "C:\\Users\\Jayanth\\mr360\\filehandling_csv\\src\\test\\resources\\input.txt";
		writeToCSV(file);
	}
	public static void writeToCSV(String file)
	{
		List<Employee> employees = Arrays.asList(new Employee(1, "jayanth", 50000), new Employee(2, "Gopi", 60000),
				new Employee(3, "Chari", 55000));

		try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
			writer.writeNext(new String[] { "ID", "Name", "Salary" });
			for (Employee emp : employees) {
				String[] row = { String.valueOf(emp.getId()), emp.getName(), String.valueOf(emp.getSalary()) };
				writer.writeNext(row);
			}
			System.out.println("CSV file written successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
