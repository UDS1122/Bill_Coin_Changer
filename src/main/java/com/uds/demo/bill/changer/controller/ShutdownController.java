package com.uds.demo.bill.changer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uds.demo.bill.changer.utils.CoinsQtyCache;

@RestController
public class ShutdownController {

	@Autowired
	private ConfigurableApplicationContext context;

	@RequestMapping("/shutdown")
    public void shutdown() {
		if(!CoinsQtyCache.currentTotalValueValid()) {
			SpringApplication.exit(context, () -> 0);
		}
    }
}