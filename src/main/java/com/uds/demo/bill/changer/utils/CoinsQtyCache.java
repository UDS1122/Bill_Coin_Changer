package com.uds.demo.bill.changer.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Cache responsible for keeping the track of all the coins type and its respective available quantity
 * As user's request are entertained quantities will be updated.
 * Singleton instance that will be used throughout the application life time.
 */
public class CoinsQtyCache {

	private static volatile Map<CoinsConst, Integer> cache;
	
	//private constructor
	private CoinsQtyCache(){
		//no-one should be able to call it
	}
	
	//Double Checking with lazy loading and thread-safety
	public static Map<CoinsConst, Integer> getCoinCache(){
		if(cache == null) {
            synchronized(CoinsQtyCache.class) {
                // Double checking Singleton instance
                if(cache == null) {
                	cache = new ConcurrentHashMap<CoinsConst, Integer>();//for better performance in case of multiple reads and write
                }
            }
         }
		return cache;
	}
	
	//update all coins to the provided value
	public static void updateAllCoinsValue(Integer value) {
		cache.replaceAll((k,v)->v=value);
	}
	
	//to tell if currently coins can be used to make any minimum change or not
	public static boolean currentTotalValueValid() {
		double value = 0.0;
		for(Map.Entry<CoinsConst, Integer> map : cache.entrySet()) {
			value += map.getKey().getCoinValue() * map.getValue();
		}
		return value>=1.0;//minimum 1 dollar
	}
}