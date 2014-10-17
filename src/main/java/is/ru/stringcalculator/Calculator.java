package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Calculator {
	private static final String DEFAULT_DELIMITER = ",|\n";
	
	public static int add(String text){
		String delimiter = getDelimiter(text);
		int[] numbers = extractNumbers(text, delimiter);
		return sum(numbers);
	}
	
	private static int[] extractNumbers(String text, String delimiter) {
		if(text.startsWith("//")){
			text = text.substring(4);
		}
		String [] seperated = text.split(delimiter);
		return toIntArray(seperated);
	}

	private static int[] toIntArray(String[] seperated) {
		int[] numbers = new int[seperated.length];
		for(int i = 0; i < seperated.length; i++){
			numbers[i] = toInt(seperated[i]);
		}
		return numbers;
	}

	private static String getDelimiter(String text) {
		if(text.startsWith("//")){
			return Pattern.quote(text.substring(2, 3));
		}
		else{
			return DEFAULT_DELIMITER;
		}
	}

	private static int toInt(String text){
		if(text.isEmpty())
			return 0;
		return Integer.parseInt(text);
	}
	
	private static int sum(int[] numbers){
		ArrayList<Integer> negatives = null;
		int sum = 0;
		for(int i : numbers){
			if(i < 0){
				if(negatives == null){
					negatives = new ArrayList<Integer>();
				}
				negatives.add(i);
			}
			sum += i;
		}
		if(negatives != null){
			String prefix = "";
			StringBuilder listOfNegatives = new StringBuilder();
			for(int i : negatives){
				listOfNegatives.append(prefix);
				prefix = ", ";
				listOfNegatives.append(i);
			}
			throw new IllegalArgumentException("Negative numbers not allowed: " + listOfNegatives.toString());
		}
		return sum;
	}
	
}
