package com.uds.demo.bill.changer.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.demo.bill.changer.service.BillChangeService;
import com.uds.demo.bill.changer.utils.BillsConst;
import com.uds.demo.bill.changer.utils.CoinsQtyCache;

@RestController
@RequestMapping(path="/bill")
public class BillChangeController {

	private static Set<Integer> BILLS_SUPPORTED = new HashSet<Integer>(BillsConst.getBillValues());
	
	@Autowired
	private BillChangeService billChangeService;

	@GetMapping("/change/{bill}")
	public ResponseEntity<?> getChange(@PathVariable Optional<Integer> bill) {
		if(!bill.isPresent()) {
			return (new ResponseEntity<>("No Bill provided. Please provide among : "+BILLS_SUPPORTED.toString(), HttpStatus.BAD_REQUEST));
		}
		if(!BILLS_SUPPORTED.contains(bill.get())) {
			return (new ResponseEntity<>("Bill provided is not supported. Please provide among : "+BILLS_SUPPORTED.toString(), HttpStatus.BAD_REQUEST));
		}
		
		if(!CoinsQtyCache.currentTotalValueValid()) {
			return (new ResponseEntity<>("Not enough coins to change. "+CoinsQtyCache.getCoinCache().toString()+"\nPlease do either :\n"
					+ " 1) Re-Fill.  Endpoint-> /coins/update/{num_of_coins}\n"
					+ " 2) Shutdown. Endpoint-> /shutdown",HttpStatus.PRECONDITION_FAILED));
		}
		
		return (new ResponseEntity<>(billChangeService.findChangeForBill(bill.get()), HttpStatus.OK));
	}
}