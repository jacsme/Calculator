package com.cal.stack;

import java.util.HashMap;
import java.util.Map;

public class CurrencyListing {
	
	private Map<Double, String> map = new HashMap<Double, String>();

	private static CurrencyListing instance = null;

	private CurrencyListing() {}

    public static CurrencyListing getInstance() {
        if (instance == null)
            instance = new CurrencyListing();
        return instance;
    }

    public void put(double amount, String currency) {
        map.put(amount, currency);       
    }

}
