package com.uds.demo.bill.changer.service;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.uds.demo.bill.changer.utils.CoinsConst;
import com.uds.demo.bill.changer.utils.CoinsQtyCache;
/*
 * This class is responsible for actually finding out the minimum coins to find change for the give bill
 * as coins are in double and we are using DP and array to find out...hence multiplied by 100.
 * If we will have integers then no need to multiply by 100.
 * It does not make any impact on the actual outcome or anything as it does make any changes to original.
 * This is synchronized to ensure only one thread is making changes.
 */
@Service
public class BillChangeService {
	
	private Map<CoinsConst, Integer> cache = CoinsQtyCache.getCoinCache();

	public synchronized String findChangeForBill(Integer bill) {
		bill = bill * 100;
		int[][] coinsUsed = new int[bill + 1][];
		int[] change = new int[] {};
		CoinsConst[] coins = new CoinsConst[cache.size()];
		Integer[] limits = new Integer[cache.size()];
		int index=0;
		for(Map.Entry<CoinsConst, Integer> map : cache.entrySet()) {
			coins[index] = map.getKey();
			limits[index] = map.getValue();
			index++;
		}
        
        for (int i = 0; i <= bill; ++i) {
            coinsUsed[i] = new int[coins.length];
        }

        int[] minCoins = new int[bill + 1];
        for (int i = 1; i <= bill; ++i) {
            minCoins[i] = Integer.MAX_VALUE - 1;
        }

        Integer[] limitsCopy = limits.clone();
        
        for (int i = 0; i < coins.length; ++i){
            while (limitsCopy[i] > 0){
                for (int j = bill; j >= 0; --j){
                    int currbill = j + (int)Math.round(coins[i].getCoinValue()*100);
                    if (currbill <= bill){
                        if (minCoins[currbill] > minCoins[j] + 1){
                            minCoins[currbill] = minCoins[j] + 1;
                            coinsUsed[currbill] = Arrays.copyOf(coinsUsed[j], coinsUsed[j].length);
                            coinsUsed[currbill][i] += 1;
                        }
                    }
                }

                limitsCopy[i] -= 1;
            }
        }

        if (minCoins[bill] == Integer.MAX_VALUE - 1){
            change = null;
            return "Sorry! Change is not possible with current available coins";
        }
        
        change = coinsUsed[bill];
        
        final StringBuilder result = new StringBuilder("Total change coins : "+minCoins[bill]);
        
        for(int idx=0;idx<change.length; idx++) {
        	if(change[idx] != 0) {
        		cache.put(coins[idx], cache.get(coins[idx])-change[idx]);
        		result.append("\n\t"+change[idx]+" coins of "+coins[idx]);
        	}
        }
        //finally return the result
        return result.toString();//minCoins[bill];
	}
}