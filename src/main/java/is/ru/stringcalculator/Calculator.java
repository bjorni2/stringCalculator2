package is.ru.stringcalculator;

public class Calculator {
	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",")){
			String[] numbers = text.split(",");
			int sum = 0;
			for(String s : numbers){
				sum += toInt(s);
			}
			return sum;
		}
		return toInt(text);
	}
	
	private static int toInt(String text){
		return Integer.parseInt(text);
	}
	
}
