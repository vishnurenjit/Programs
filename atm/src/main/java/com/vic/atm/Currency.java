package com.vic.atm;

public enum Currency {
	CUR1000(1000), CUR500(500), CUR100(100), CUR50(50), CUR20(20), CUR10(10);
	
	private int value;
	
	private Currency(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
