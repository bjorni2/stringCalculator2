package is.ru.stringcalculator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calculator {
	private static final String USER_SPECIFIED_DELIMITER_PREFIX = "//";
	private static final int MAX_VALUE = 1000;
	private static final String DEFAULT_DELIMITER = ",|\n";
	private static final Pattern DELIMITER_PATTERN = Pattern.compile("(\\[([^\\]]*)\\])");
	
	public static int add(String text){
		String delimiter = getDelimiter(text);
		int[] numbers = extractNumbers(text, delimiter);
		return sum(numbers);
	}
	
	private static int[] extractNumbers(String text, String delimiter) {
		if(text.startsWith(USER_SPECIFIED_DELIMITER_PREFIX)){
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
		if(text.startsWith(USER_SPECIFIED_DELIMITER_PREFIX)){
			String delimiterPart  = text.substring(2, text.indexOf("\n"));
			if(delimiterPart.length() == 1){
				return Pattern.quote(delimiterPart);
			}
			Matcher delimiterMatcher = DELIMITER_PATTERN.matcher(delimiterPart);
			StringBuilder delimiter = new StringBuilder();
			while(delimiterMatcher.find()){
				appendStringList(Pattern.quote(delimiterMatcher.group(2)), delimiter, "|");
			}
			return delimiter.toString();
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
				appendStringList(i, negatives, ", ");
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

	private static void appendStringList(Object item, StringBuilder sb, String delimiter) {
		if(sb.length() != 0){
			sb.append(delimiter);
		}
		sb.append(item);
	}
	
	private static void illegalArgument(String message){
		throw new IllegalArgumentException(message);
	}
	
}
