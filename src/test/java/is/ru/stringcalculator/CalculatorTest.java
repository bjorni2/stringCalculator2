package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	@Test
	public void EmptyStringShouldReturnZero() {
		assertEquals(0, Calculator.add(""));;
	}
	
	@Test
	public void OneNumberShouldReturnTheSameNumber(){
		assertEquals(5, Calculator.add("5"));
	}
	
	@Test
	public void TwoNumbersShouldReturnTheirSum(){
		assertEquals(7, Calculator.add("5,2"));
	}
	
	@Test
	public void MoreThanTwoNumbersShouldReturnTheirSum(){
		assertEquals(11, Calculator.add("3,4,2,2"));
	}
}
