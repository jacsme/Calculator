package com.cal.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.cal.cache.CurrencyCache;
import com.cal.response.CalculatorResponse;
import com.cal.stack.CalculatorMemory;
import com.cal.util.CalculatorUtil;
import com.cal.util.ResourceUtil;

public class FXCalculatorServiceImpl implements FXCalculatorService{

	@Override
	public CalculatorResponse calculateFX(String inputList){
		
		CalculatorResponse response = new CalculatorResponse();
		List<String> myList = new ArrayList<String>(Arrays.asList(inputList.split(" ")));
		
		Stack<String> fxInitStack = CalculatorMemory.resetFxInitStackInstance();
		Stack<String> fxMainStack = CalculatorMemory.resetFxMainStackInstance();

		BigDecimal requestValue = new BigDecimal(0.00);
		BigDecimal outputValue = new BigDecimal(0.00);
		BigDecimal crossValue = new BigDecimal(0.00);
		BigDecimal pairValue = new BigDecimal(0.00);
		
		String firstCurrency = null;
		String secondCurrency = null;
		String pairedCurrency = null;
		String reversepairedCurrency = null;
		
		String strPairValue = null;
		String strReversePairValue = null;
		
		for (String inputlist : myList){
			System.out.println(inputlist.toString().toUpperCase());
			if (!inputlist.equalsIgnoreCase("in")){
				if(!CalculatorUtil.checkString(inputlist)){
					requestValue = new BigDecimal(inputlist);
				}else{	
					fxInitStack.push(inputlist.toString());
				}
			}
		}	
		
		firstCurrency = fxInitStack.firstElement().toString();
		secondCurrency = fxInitStack.lastElement().toString();
		
		pairedCurrency = firstCurrency + secondCurrency;
		reversepairedCurrency = secondCurrency + firstCurrency;
		
		//make a validation here
		strPairValue = ResourceUtil.getMessage(pairedCurrency);
		strReversePairValue = ResourceUtil.getMessage(reversepairedCurrency);
		
		if (strPairValue!=null){
			
			pairValue = new BigDecimal(strPairValue);	
			outputValue = requestValue.multiply(pairValue);
			
			return generateResponse(response, fxMainStack, requestValue, outputValue.setScale(2, RoundingMode.HALF_UP), firstCurrency, secondCurrency);
			
		}else{
			Map<String, Double> currencyPairListing = CurrencyCache.getCurrencyListing();
			for (Map.Entry<String, Double> deepentry : currencyPairListing.entrySet()) {
				
				String deepEntryKey = deepentry.getKey();
				if (deepEntryKey.indexOf(firstCurrency) >= 0){
					String tmpCurrency = deepEntryKey.substring(3);
					String tmpPair = tmpCurrency + secondCurrency;
					String strCrossValue = ResourceUtil.getMessage(tmpPair);
					if (strCrossValue!=null){
						crossValue = new BigDecimal(strCrossValue);
						outputValue = requestValue.multiply(new BigDecimal(deepentry.getValue()).multiply(crossValue)).setScale(2, RoundingMode.HALF_UP);
						
						return generateResponse(response, fxMainStack, requestValue, outputValue.setScale(2, RoundingMode.HALF_UP), firstCurrency, secondCurrency);
					}
				}
			}
			
			if (strReversePairValue!=null){
			
				BigDecimal defaultValue = new BigDecimal(1.00);
				pairValue = new BigDecimal(strReversePairValue);
				outputValue = pairValue.add(defaultValue).multiply(requestValue);
			
				return generateResponse(response, fxMainStack, requestValue, outputValue.setScale(2, RoundingMode.HALF_UP), firstCurrency, secondCurrency);
			}
		}
		
		if (outputValue.compareTo(new BigDecimal(0.00)) == 0 ){
			response.setStatus(CalculatorUtil.FAILURE_STATUS);
			response.setMessage("Unable to find the Rate for " + pairedCurrency);
		}
		
		return response;
	}

	private CalculatorResponse generateResponse(CalculatorResponse response, Stack<String> fxMainStack, BigDecimal requestValue,
			BigDecimal outputValue, String firstCurrency, String secondCurrency) {
		fxMainStack.push(firstCurrency);
		fxMainStack.push(requestValue.toString());
		fxMainStack.push("=");
		fxMainStack.push(secondCurrency);
		fxMainStack.push(outputValue.toString());
			
		response.setStatus(CalculatorUtil.SUCCESSFUL_STATUS);
		response.setMessage("Inserted into Stack");
		response.setResult(fxMainStack);
		
		return response;
	}

}
