package com.epam.preprod.eshop.task8.part1.strategy;

import com.epam.preprod.eshop.task8.part1.thread.PrimeNumbersSearcher;

import java.util.ArrayList;
import java.util.List;

public class ThreadSearcher extends ConcurrentSearcher {
	private List<Thread> threadList;

	public ThreadSearcher(int threads) {
		super(threads);
		threadList = new ArrayList<>();
	}

	@Override
	public List<Integer> search(PrimeNumbersSearcher searchAction) throws InterruptedException {
		searchAction.setPhaser(PHASER);
		for (int i = 0; i < THREADS; i++) {
			Thread thread = new Thread(searchAction);
			thread.start();
			threadList.add(thread);
		}

		PHASER.arriveAndAwaitAdvance();
		return searchAction.getResults();
	}

	@Override
	public void stop() {
		for (Thread threads : threadList)
			threads.interrupt();
		System.out.println("Searcher stopped.");
	}
}
