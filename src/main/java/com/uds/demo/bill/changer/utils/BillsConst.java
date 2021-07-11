package com.uds.demo.bill.changer.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum BillsConst {
	//constant for the bill types and values...if any new denominations can come then we have to add here
	ONE("1",1),
	TWO("2",2),
	FIVE("5",5),
	TEN("10",10),
	TWENTY("20",20),
	FIFTY("50",50),
	HUNDRED("100",100);
	
	private String billType;
	private int billValue;
	private static List<Integer> billValues;
	
	private BillsConst(String name, int value) {
		this.billType = name;
		this.billValue = value;
	}
	
	public static List<Integer> getBillValues(){
		if(billValues == null) {
			billValues = new ArrayList<Integer>();
			for(BillsConst bill : BillsConst.values()) {
				billValues.add(bill.billValue);
			}
		}
		return Collections.unmodifiableList(billValues);//any caller should not be able to make any changes to list of available Bills
	}
}