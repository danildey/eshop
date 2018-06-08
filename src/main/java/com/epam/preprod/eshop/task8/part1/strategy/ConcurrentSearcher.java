package com.epam.preprod.eshop.task8.part1.strategy;

import com.epam.preprod.eshop.task8.part1.thread.PrimeNumbersSearcher;

import java.util.List;
import java.util.concurrent.Phaser;

public abstract class ConcurrentSearcher {
	protected final Phaser PHASER;
	protected final int THREADS;

	public ConcurrentSearcher(int threads) {
		this.THREADS = threads;
		this.PHASER = new Phaser(threads + 1);
	}

	public abstract List<Integer> search(PrimeNumbersSearcher searchAction) throws InterruptedException;

	public abstract void stop() throws InterruptedException;
}
