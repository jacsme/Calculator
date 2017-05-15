package com.cal.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorUtil {
	
	public static final String CALCULATOR_OPERATORS = "+*-/sqrt";
	public static final String CLEAR = "CLEAR";
	public static final String UNDO = "UNDO";
	public static final String SQRT = "SQRT";
	
	public static final String SUCCESSFUL_STATUS = "Successful";
	public static final String FAILURE_STATUS = "Failure";
	
	
	public static boolean checkString(String strValue){
		boolean stringFound = false;
		if (!Character.isDigit(strValue.charAt(0))){
			stringFound = true;
		}
		return stringFound;		
	}
	
	public static BigDecimal initiateComputation(BigDecimal value1, BigDecimal value2, String stroperand){
		BigDecimal result = new BigDecimal(0.00);
		BigDecimal remainder = new BigDecimal(0.00);
		switch(stroperand){
			case "+":
				result = value1.add(value2);
				break;
			case "-":
				result = value1.subtract(value2);
				break;
			case "*":
				result = value1.multiply(value2);
				break;
			case "/":
				//Compare equal value == 0, Compare Left > right == 1, Left < right == -1
				
				remainder = value1.remainder(value2);
				if (remainder.compareTo(new BigDecimal(0)) == 0){
					result = value1.divide(value2, 0, RoundingMode.HALF_UP);
				}else{
					result = value1.divide(value2, 15, RoundingMode.HALF_UP);
				}
				System.out.println("Remainder");
				break;
			case "sqrt":
				result = new BigDecimal(Math.sqrt(value1.doubleValue()));
				break;
		}
		return result;
	}
}
