package com.uds.demo.bill.changer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uds.demo.bill.changer.utils.CoinsConst;
import com.uds.demo.bill.changer.utils.CoinsQtyCache;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${default.coins.lot.size}")
	private int defaultCointLot;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populateCoinsQtyCache();
	}
	
	private void populateCoinsQtyCache() {
		Arrays.asList(CoinsConst.values()).stream().forEach(c->{
			CoinsQtyCache.getCoinCache().put(c, defaultCointLot);
		});
	}
	
	/* SHUTDOWN Option
	 * We could have a thread running which will check the current coins cache status in every say 5 seconds
	 * and if value is not > 1 then optionally we could have chosen to exit rather then asking to re-fill
	 * the system gracefully. e.g. 
	 * SpringApplication.exit(context, () -> 0)
	 */

}
