package com.vic.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cash {
	private Map<Currency, Integer> map = new HashMap<Currency, Integer>();
	
	public int calculate(){
		int cash = 0;
		
		for(Entry<Currency, Integer> entry : map.entrySet()) {
			cash = cash + entryValue(entry);
		}
		return cash;
	}

	private int entryValue(Entry<Currency, Integer> entry) {
		if(entry.getValue() != null) {
			return entry.getKey().getValue() * entry.getValue().intValue();
		}
		return 0;
	}
	
	public void addMoney(Currency currency) {
		addMoney(currency, 1);
	}
	
	public Cash addMoney(Currency currency, int count) {
		Integer curCount = map.get(currency);
		if(null == curCount) {
			curCount = Integer.valueOf(count);
		} else {
			curCount = curCount + count;
		}
		map.put(currency, curCount);		
		return this;
	}
	
	public boolean isCurrencyPresent(Currency currency) {
		Integer count = map.get(currency);
		return count != null && count > 0;
	}
}
