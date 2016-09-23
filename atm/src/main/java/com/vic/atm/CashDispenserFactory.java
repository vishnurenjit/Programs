package com.vic.atm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CashDispenserFactory {
	
	private static CashDispenserFactory factory;
	
	private CashDispenserFactory() {}
	
	public static CashDispenserFactory getInstance() {
		if(null == factory) {
			factory = new CashDispenserFactory();
		}
		
		return factory;
	}
	
	public CashDispenser buildCashDispensery(Iterator<Currency> iterator) {
		CashDispenser cashDispenser = new CashDispenserImpl(null);
		CashDispenser cur = cashDispenser;
		while (iterator.hasNext()) {
			Currency currency = (Currency) iterator.next();
			CashDispenser newDisp = new CashDispenserImpl(currency);
			cur.setNextDispenser(newDisp);
			cur = newDisp;
		}
		return cashDispenser.getNext();		
	}
	
	public CashDispenser buildCashDispensery(List<Currency> list) {
		if(null == list || list.size() == 0) {			
			list = Arrays.asList(Currency.values());
		}
		
		return buildCashDispensery(list.iterator());
	}
	
	public CashDispenser buildCashDispensery() {
		return buildCashDispensery((List<Currency>) null);
	}
}
