package com.uds.demo.bill.changer.utils;

public enum CoinsConst {
	//constant for the coin values
	ONE_CENT(0.01),
	FIVE_CENT(0.05),
	TEN_CENT(0.10),
	TWENTY_FIVE_CENT(0.25);
	
	private double coinValue;
	
	private CoinsConst(double value) {
		this.coinValue = value;
	}
	
	public double getCoinValue() {
		return this.coinValue;
	}
}