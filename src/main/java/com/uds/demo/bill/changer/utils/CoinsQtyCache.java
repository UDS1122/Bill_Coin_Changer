package com.uds.demo.bill.changer.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CoinsQtyCache {

	private static Map<CoinsConst, Integer> cache;
	
	private CoinsQtyCache(){
	}
	
	public static Map<CoinsConst, Integer> getCoinCache(){
		if(cache == null) {
			cache = new ConcurrentHashMap<CoinsConst, Integer>();
		}
		return cache;
	}
	
	public static void updateAllCoinsValue(Integer value) {
		cache.replaceAll((k,v)->v=value);
	}
	
	public static boolean currentTotalValueValid() {
		double value = 0.0;
		for(Map.Entry<CoinsConst, Integer> map : cache.entrySet()) {
			value += map.getKey().getCoinValue() * map.getValue();
		}
		return value>=1.0;//minimum 1 dollar
	}
}