package is.ru.stringcalculator;

public class Calculator {
	public static int add(String text){
		String[] numbers;
		if(text.contains("\n"))
		{
			numbers = text.split("\n");
		}
		else
		{
			numbers = text.split(",");
		}
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
