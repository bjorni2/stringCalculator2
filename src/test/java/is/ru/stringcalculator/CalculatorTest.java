package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
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
	
	@Test
	public void NewLineAsDelimiter(){
		assertEquals(3, Calculator.add("2\n1"));
	}
	
	@Test
	public void NewLineAndCommaAsDelimiter(){
		assertEquals(6, Calculator.add("1,2\n3"));
	}
	
	@Test
	public void UserSpecifiedDelimiter(){
		assertEquals(5, Calculator.add("//%\n3%2"));
	}
	
	@Test
	public void RegexReservedCharacterAsUserSpecifiedDelimiter(){
		assertEquals(6, Calculator.add("//+\n1+2+3"));
	}
	
	@Test
	public void NegativeNumbersShouldThrowErrorAndBeListedInErrorMessage(){
		thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Negative numbers not allowed: -1, -3");
        Calculator.add("-1,2,-3");
	}
	
	@Test
	public void NumbersBiggerThan1000ShouldBeIgnored(){
		assertEquals(3, Calculator.add("//?\n1337?3"));
	}
	
	@Test
	public void DelimiterCanBeOfAnyLength(){
		assertEquals(6, Calculator.add("//[***]\n1***2***3"));
	}
}
