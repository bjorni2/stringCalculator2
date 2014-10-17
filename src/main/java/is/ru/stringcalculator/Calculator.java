package is.ru.stringcalculator;

import java.util.regex.Pattern;

public class Calculator {
	private static final int MAX_VALUE = 1000;
	private static final String DEFAULT_DELIMITER = ",|\n";
	
	public static int add(String text){
		String delimiter = getDelimiter(text);
		int[] numbers = extractNumbers(text, delimiter);
		return sum(numbers);
	}
	
	private static int[] extractNumbers(String text, String delimiter) {
		if(text.startsWith("//")){
			text = text.substring(text.indexOf("\n") + 1);
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
			if(text.startsWith("//[") && text.contains("]"))
			{
				String delimiter = text.substring(3, text.indexOf("]"));
				return Pattern.quote(delimiter);
			}
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
		StringBuilder negatives = new StringBuilder();
		int sum = 0;
		for(int i : numbers){
			if(i < 0){
				addToNegativeList(i, negatives);
			}
			else if(i > MAX_VALUE){
				continue;
			}
			sum += i;
		}
		if(negatives.length() != 0){
			illegalArgument("Negative numbers not allowed: " + negatives.toString());
		}
		return sum;
	}

	private static void addToNegativeList(int i, StringBuilder negatives) {
		if(negatives.length() != 0){
			negatives.append(", ");
		}
		negatives.append(i);
	}
	
	private static void illegalArgument(String message){
		throw new IllegalArgumentException(message);
	}
	
}
