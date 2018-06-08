package com.epam.preprod.eshop.task8.part1.thread;

import java.util.ArrayList;
import java.util.List;

public class CashedSearcher extends PrimeNumbersSearcher {

	public CashedSearcher(int start, int end) {
		super(start, end);
	}

	@Override
	public void run() {
		List<Integer> cashList = new ArrayList<>();
		calculatePrimeNumbers(cashList);
		results.addAll(cashList);
		phaser.arrive();
	}

}
