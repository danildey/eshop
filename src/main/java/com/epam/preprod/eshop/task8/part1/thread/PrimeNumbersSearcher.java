package com.epam.preprod.eshop.task8.part1.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PrimeNumbersSearcher implements Runnable {
	protected Phaser phaser;

	protected List<Integer> results;
	private AtomicInteger start;

	private final int END;
	private final int RANGE = 4;

	public PrimeNumbersSearcher(int start, int end) {
		results = Collections.synchronizedList(new ArrayList<>());
		this.start = new AtomicInteger(start);
		this.END = end;
	}

	public List<Integer> getResults() {
		return results;
	}

	public void setPhaser(Phaser phaser) {
		this.phaser = phaser;
	}

	protected void calculatePrimeNumbers(List<Integer> inputList) {
		int from, to;
		while ((from = start.getAndAdd(RANGE)) < END && !Thread.currentThread().isInterrupted()) {
			to = from + RANGE;
			if (to > END) {
				to = END;
			}
			for (int i = from; i < to; i++) {
				if (isPrime(i)) {
					inputList.add(i);
				}
			}
		}
	}

	private boolean isPrime(int n) {
		if (n<2){
			return false;
		}
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
