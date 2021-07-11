package com.uds.demo.bill.changer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.uds.demo.bill.changer.service.BillChangeService;
import com.uds.demo.bill.changer.utils.BillsConst;
import com.uds.demo.bill.changer.utils.CoinsConst;
import com.uds.demo.bill.changer.utils.CoinsQtyCache;

@SpringBootTest
public class BillChangeControllerTest {
	
	@Autowired
	private BillChangeService service;
	private static Set<Integer> BILLS_SUPPORTED = new HashSet<Integer>(BillsConst.getBillValues());
	@Value("${default.coins.lot.size}")
	private int defaultCoinsLot;
	
	
	@BeforeAll
	public static void init(){
		Arrays.asList(CoinsConst.values()).stream().forEach(c->{
			CoinsQtyCache.getCoinCache().put(c, 100);
		});
	}
	
	@Test
	public void isValidBill() {
		assertThat(BILLS_SUPPORTED.contains(1)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(2)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(5)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(10)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(20)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(50)).isTrue();
		assertThat(BILLS_SUPPORTED.contains(100)).isTrue();
	}

	@Test
	public void isNotValidBill() {
		assertThat(BILLS_SUPPORTED.contains(200)).isFalse();
	}
	
	@Test
	public void defaultCoinSizeNotZero() {
		assertThat(defaultCoinsLot).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void cacheNotEmpty() {
		assertThat(CoinsQtyCache.getCoinCache()).isNotEmpty();
	}
	
	@Test
	public void noChangeAfterCoinsExhausted() {
		CoinsQtyCache.updateAllCoinsValue(0);
		assertThat(CoinsQtyCache.currentTotalValueValid()).isFalse();
	}
	
	@Test
	public void changePossible() {
		CoinsQtyCache.updateAllCoinsValue(100);
		assertThat(service.findChangeForBill(20)).isNotBlank().contains("Total");
	}
	
	@Test
	public void changeNotPossible() {
		assertThat(service.findChangeForBill(100)).isNotBlank().contains("Sorry");
	}
}