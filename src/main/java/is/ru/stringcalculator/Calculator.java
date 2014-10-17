package is.ru.stringcalculator;

public class Calculator {
	public static int add(String text){
		String delimiter;
		if(text.startsWith("//")){
			delimiter = text.substring(2, 3);
			text = text.substring(4);
		}
		else{
			delimiter = ",|\n";
		}
		String[] numbers = text.split(delimiter);
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
