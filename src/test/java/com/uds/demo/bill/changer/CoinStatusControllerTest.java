package com.uds.demo.bill.changer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.uds.demo.bill.changer.utils.CoinsQtyCache;

@SpringBootTest
public class CoinStatusControllerTest {

	@Test
	public void updateCoins() {
		CoinsQtyCache.updateAllCoinsValue(50);
		assertThat(CoinsQtyCache.getCoinCache()).containsValue(50);
	}
}