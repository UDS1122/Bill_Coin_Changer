package com.uds.demo.bill.changer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.demo.bill.changer.utils.CoinsQtyCache;

/*
 * Controlled responsible for providing the current status of all the coins. 
 *  1) Quantity available for each coin type
 *  2) In case we have exhausted then provide and end point where coins can be re-filled
 *  In actual scenario we would have some kind authorization so that only authorized people can perform this task  
 */

@RestController
@RequestMapping(path="/coins")
public class CoinStatusController {

	@GetMapping("/all")
	public ResponseEntity<?> getAllCoins() {
		return (new ResponseEntity<>(CoinsQtyCache.getCoinCache(), HttpStatus.OK));
	}
	
	@PutMapping("/update/{value}")
	public ResponseEntity<?> updateCoins(@PathVariable Integer value) {
		CoinsQtyCache.updateAllCoinsValue(value);
		return (new ResponseEntity<>(CoinsQtyCache.getCoinCache(), HttpStatus.ACCEPTED));
	}
}