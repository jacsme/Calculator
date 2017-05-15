package com.cal.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.cal.response.CalculatorResponse;
import com.cal.stack.CalculatorMemory;
import com.cal.util.CalculatorUtil;

public class RPNCalculatorServiceImpl implements RPNCalculatorService{

	@Override
	public CalculatorResponse calculateRPN(String inputList){
		
		CalculatorResponse response = new CalculatorResponse();
		List<String> myList = new ArrayList<String>(Arrays.asList(inputList.split(" ")));
		Stack<String> rpnMainStack = CalculatorMemory.getRPNMainStackInstance();
		Stack<String> rpnUndoStack = CalculatorMemory.getRPNUndoStackInstance();
		
		BigDecimal bigValue1 = new BigDecimal(0.00);
		BigDecimal bigValue2 = new BigDecimal(0.00);
		
		String firstPop = null;
		String secondPop = null;
		
		BigDecimal finalresult = new BigDecimal(0.00);
		
		for (String inputlist : myList){
			System.out.println(inputlist.toString().toUpperCase());

			if (inputlist.equalsIgnoreCase(CalculatorUtil.SQRT)){
				firstPop = rpnMainStack.pop().toString();
				bigValue1 = new BigDecimal((String) firstPop);
				rpnUndoStack.push(firstPop);
				finalresult = CalculatorUtil.initiateComputation(bigValue2, bigValue1, inputlist);
				rpnMainStack.push(finalresult.toString());
			
			}else if (CalculatorUtil.CALCULATOR_OPERATORS.indexOf(inputlist) >= 0){
				if (rpnMainStack.size() < 2 && !inputlist.equalsIgnoreCase(CalculatorUtil.SQRT)){
					
					response.setStatus(CalculatorUtil.FAILURE_STATUS);
					response.setMessage("operator " + inputlist + " : Insufficient Parameters");
					response.setResult(rpnMainStack);
					return response;
					
				}else{
					
					firstPop = rpnMainStack.pop().toString();
					secondPop = rpnMainStack.pop().toString();
					
					bigValue1 = new BigDecimal((String) firstPop);
					bigValue2 = new BigDecimal((String) secondPop);
					
					rpnUndoStack.push(firstPop);
					rpnUndoStack.push(secondPop);
					
					finalresult = CalculatorUtil.initiateComputation(bigValue2, bigValue1, inputlist);
					rpnMainStack.push(finalresult.toString());
				}
			
			}else if (CalculatorUtil.checkString(inputlist)){
				if (CalculatorUtil.CLEAR.equals(inputlist.toString().toUpperCase())){
					rpnMainStack = CalculatorMemory.resetRPNMainStackInstance();
				}
			
				if (CalculatorUtil.UNDO.equals(inputlist.toString().toUpperCase())){
					
					if (rpnUndoStack.size() == 0){
						rpnMainStack.pop();
					}else{
						rpnMainStack.push(rpnUndoStack.pop());
					}
				}
			
			}else{
				rpnMainStack.push(inputlist);
			}
		}
		response.setStatus(CalculatorUtil.SUCCESSFUL_STATUS);
		response.setMessage("Inserted into Stack");
		response.setResult(rpnMainStack);
		return response;
	}

}
