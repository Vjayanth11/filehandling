package com.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class ReadWriteCSV {

	public static void main(String[] args) throws CsvValidationException {

		String file = "C:\\Users\\Jayanth\\mr360\\filehandling_csv\\src\\test\\resources\\input.txt";
		readFromCsv(file);
	}

	public static void readFromCsv(String filePath) throws CsvValidationException {
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			String[] row;
			while ((row = reader.readNext()) != null) {
				System.out.println(String.join(", ", row));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
