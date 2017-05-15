package com.cal.enumerator;

public enum CalculatorEnum {

	CLEAR("CLEAR"), UNDO("UNDO");
	
	private String enumType;
	
	private CalculatorEnum(String eType){
		enumType = eType;
	}

	public String getEnumType() {
		return enumType;
	}
}
