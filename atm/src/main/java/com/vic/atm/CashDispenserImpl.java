package com.vic.atm;

public class CashDispenserImpl implements CashDispenser {
	
	private Currency currency;
	
	private CashDispenser next;
	
	public CashDispenserImpl(Currency currency) {
		this.currency = currency;
	}

	public void setNextDispenser(CashDispenser cashDispenser) {
		this.next = cashDispenser;
	}

	public void dispense(Cash cash, Cash balance, int amount) {
		int count = amount % currency.getValue();
		if(count > 0) {
			cash.addMoney(currency, count);
		}
		
		if(null != next) {
			next.dispense(cash, balance, amount);
		}
		
	}

	public CashDispenser getNext() {
		return next;
	}

}
