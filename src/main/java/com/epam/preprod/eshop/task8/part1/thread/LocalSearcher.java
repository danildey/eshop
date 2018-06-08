package com.epam.preprod.eshop.task8.part1.thread;

public class LocalSearcher extends PrimeNumbersSearcher {

	public LocalSearcher(int start, int end) {
		super(start, end);
	}


	@Override
	public void run() {
		calculatePrimeNumbers(results);
		phaser.arrive();
	}
}
