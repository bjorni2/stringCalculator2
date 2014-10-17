package is.ru.stringcalculator;

public class Calculator {
	public static int add(String text){
		if(text.startsWith("//")){
			String delimiter = text.substring(2, 3);
			text = text.substring(4);
			String[] numbers = text.split(delimiter);
			return sum(numbers);
		}
		String[] numbers = text.split(",|\n");
		return sum(numbers);
	}
	
	private static int toInt(String text){
		if(text.isEmpty())
			return 0;
		return Integer.parseInt(text);
	}
	
	private static int sum(String[] numbers){
		int sum = 0;
		for(String s : numbers){
			sum += toInt(s);
		}
		return sum;
	}
	
}
