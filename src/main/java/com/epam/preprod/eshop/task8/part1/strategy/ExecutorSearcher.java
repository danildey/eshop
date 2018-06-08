package com.epam.preprod.eshop.task8.part1.strategy;

import com.epam.preprod.eshop.task8.part1.thread.PrimeNumbersSearcher;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorSearcher extends ConcurrentSearcher {
	private ExecutorService executor;

	public ExecutorSearcher(int threads) {
		super(threads);
		this.executor = Executors.newFixedThreadPool(threads);
	}

	@Override
	public List<Integer> search(PrimeNumbersSearcher searchAction) throws InterruptedException {
		searchAction.setPhaser(PHASER);
		for (int i = 0; i < THREADS; i++) {
			executor.execute(searchAction);
		}
		PHASER.arriveAndAwaitAdvance();
		return searchAction.getResults();
	}

	@Override
	public void stop() throws InterruptedException {
		executor.shutdown();
		final boolean done = executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println("Searcher stopped. All was done : " + done);
	}
}
