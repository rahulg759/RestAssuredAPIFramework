package com.api.pojo;

public class PurchaseUnit {

	private Amount amount;

	public PurchaseUnit(String currency_code,String value) {
		this.amount=new Amount(currency_code, value);
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	
	


}
