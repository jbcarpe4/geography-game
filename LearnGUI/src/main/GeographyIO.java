package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeographyIO {
	
	private static final int FIRST_CITY = 1;
	
	private static final int LAST_CITY = 5;
	
	public static void readFile(String fileName, ArrayList<Question> list) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] array = line.split(", ");
				String[] shuffled = randomize(Arrays.copyOfRange(array, FIRST_CITY, LAST_CITY));
				Question q = new Question(array[0], shuffled[0], shuffled[1], shuffled[2],
										  shuffled[3], array[LAST_CITY]);
				list.add(q);
			}

		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error reading file");
		}
	}
	
	private static String[] randomize(String[] array) {
		Random rand = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			String temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
		return array;
	}

}
