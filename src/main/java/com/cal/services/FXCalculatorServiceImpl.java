package com.cal.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.cal.response.CalculatorResponse;
import com.cal.stack.CalculatorMemory;
import com.cal.util.CalculatorUtil;

public class FXCalculatorServiceImpl implements FXCalculatorService{

	@Override
	public CalculatorResponse calculateFX(String inputList){
		
		CalculatorResponse response = new CalculatorResponse();
		List<String> myList = new ArrayList<String>(Arrays.asList(inputList.split(" ")));
		
		Stack<String> fxMainStack = CalculatorMemory.getFxMainStackInstance();
		
		BigDecimal bigValue1 = new BigDecimal(0.00);
		BigDecimal bigValue2 = new BigDecimal(0.00);
		
		String firstPop = null;
		String secondPop = null;
		
		BigDecimal finalresult = new BigDecimal(0.00);
		
		for (String inputlist : myList){
			fxMainStack.push(finalresult.toString());
			
			System.out.println(inputlist.toString().toUpperCase());

			if (inputlist.equalsIgnoreCase(CalculatorUtil.SQRT)){
				firstPop = mainStack.pop().toString();
				bigValue1 = new BigDecimal((String) firstPop);
				undoStack.push(firstPop);
				finalresult = CalculatorUtil.initiateComputation(bigValue2, bigValue1, inputlist);
				mainStack.push(finalresult.toString());
			
			}else if (CalculatorUtil.CALCULATOR_OPERATORS.indexOf(inputlist) >= 0){
				if (mainStack.size() < 2 && !inputlist.equalsIgnoreCase(CalculatorUtil.SQRT)){
					
					response.setStatus(CalculatorUtil.FAILURE_STATUS);
					response.setMessage("operator " + inputlist + " : Insufficient Parameters");
					response.setResult(mainStack);
					return response;
					
				}else{
					
					firstPop = mainStack.pop().toString();
					secondPop = mainStack.pop().toString();
					
					bigValue1 = new BigDecimal((String) firstPop);
					bigValue2 = new BigDecimal((String) secondPop);
					
					undoStack.push(firstPop);
					undoStack.push(secondPop);
					
					finalresult = CalculatorUtil.initiateComputation(bigValue2, bigValue1, inputlist);
					mainStack.push(finalresult.toString());
				}
			
			}else if (CalculatorUtil.checkString(inputlist)){
				if (CalculatorUtil.CLEAR.equals(inputlist.toString().toUpperCase())){
					mainStack = CalculatorMemory.resetMainStackInstance();
				}
			
				if (CalculatorUtil.UNDO.equals(inputlist.toString().toUpperCase())){
					
					if (undoStack.size() == 0){
						mainStack.pop();
					}else{
						mainStack.push(undoStack.pop());
					}
				}
			
			}else{
				mainStack.push(inputlist);
			}
		}
		response.setStatus(CalculatorUtil.SUCCESSFUL_STATUS);
		response.setMessage("Inserted into Stack");
		response.setResult(mainStack);
		return response;
	}

}
