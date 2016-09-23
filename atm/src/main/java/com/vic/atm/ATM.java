package com.vic.atm;

public class ATM {

	private final int DEFAULT_COUNT = 10;
	
	private Cash balance;

	private CashDispenser cashDispenser;

	public ATM() {
		balance = new Cash().addMoney(Currency.CUR10, DEFAULT_COUNT).addMoney(Currency.CUR100, DEFAULT_COUNT)
				.addMoney(Currency.CUR1000, DEFAULT_COUNT).addMoney(Currency.CUR20, DEFAULT_COUNT)
				.addMoney(Currency.CUR50, DEFAULT_COUNT).addMoney(Currency.CUR500, DEFAULT_COUNT);
		
		cashDispenser = CashDispenserFactory.getInstance().buildCashDispensery();
	}

	public ATM(Cash balance) {
		this.balance = balance;
		
		cashDispenser = CashDispenserFactory.getInstance().buildCashDispensery();
	}
	
	public Output getMoney(String userName, String password, int amount) {
		Output output = new Output();
		checkEnoughCash(output, amount);
		
		if(BalanceStatus.EB == output.status) {
			cashDispenser.dispense(output.getCash(), balance, amount);
		}
				
		return output;
	}
	
	private void checkEnoughCash(Output output, int amount) {
		int balAmt = balance.calculate();
		if(amount > balAmt) {
			output.status = BalanceStatus.NEB;
			output.setMsg("Insert money lesser than or equal to " + balAmt);
			return;
		}
		
		Currency cur = getSmallestAvailable();
		if(amount % cur.getValue() != 0) {
			output.status = BalanceStatus.EMO;
			output.setMsg("Insert money as multiple of " + cur.getValue());
			return;
		}
		
		output.status = BalanceStatus.EB;
		output.setMsg("Enough Cash");
	}

	private Currency getSmallestAvailable() {
		Currency cur = Currency.CUR1000;
		for(Currency currency : Currency.values()) {
			if(balance.isCurrencyPresent(currency) && cur.getValue() > currency.getValue()) {
				cur = currency;
			}
		}
		return cur;
	}

	private class Output {
		
		private Cash cash;
		private String msg;
		private BalanceStatus status;
		
		public Cash getCash() {
			return cash;
		}
		public String getMsg() {
			return msg;
		}
		public void setCash(Cash cash) {
			this.cash = cash;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}		
		
	}
	
}
