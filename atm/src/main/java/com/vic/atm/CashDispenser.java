package com.vic.atm;

public interface CashDispenser {
	void setNextDispenser(CashDispenser cashDispenser);

	void dispense(Cash cash, Cash balance, int amount);
	
	CashDispenser getNext();
}
