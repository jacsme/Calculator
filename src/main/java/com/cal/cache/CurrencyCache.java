package com.cal.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cal.util.ResourceUtil;

public class CurrencyCache implements ServletContextListener{
	
	public static Map<String, Double> currencyPairListing = new HashMap<String, Double>();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("File Location " + new File(".").getAbsolutePath());
		System.out.println("ServletContextListener destroyed");
	}

    public static Map<String, Double> getCurrencyListing() {
		return currencyPairListing;
	}

	public void setCurrencyListing(Map<String, Double> currencyListing) {
		CurrencyCache.currencyPairListing = currencyListing;
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		if (currencyPairListing == null) {
			currencyPairListing = new HashMap<String, Double>();
		}
		
		String currencyPairs = ResourceUtil.getMessage("CURRENCY_PAIRS");
		List<String> currencyPairList = new ArrayList<String>(Arrays.asList(currencyPairs.split(" ")));
		
		for (String currency : currencyPairList){
			
			String currencyPair = ResourceUtil.getMessage(currency);
			Double currencyPairValue = Double.parseDouble(currencyPair);
			
			currencyPairListing.put(currency, currencyPairValue);
		}
	}

}
