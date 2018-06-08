package com.epam.preprod.eshop.task8.part1;

import com.epam.preprod.eshop.task8.part1.strategy.ConcurrentSearcher;
import com.epam.preprod.eshop.task8.part1.strategy.ExecutorSearcher;
import com.epam.preprod.eshop.task8.part1.strategy.ThreadSearcher;
import com.epam.preprod.eshop.task8.part1.thread.CashedSearcher;
import com.epam.preprod.eshop.task8.part1.thread.LocalSearcher;
import com.epam.preprod.eshop.task8.part1.thread.PrimeNumbersSearcher;

import java.util.List;

public class DemoSearcherBenchmark {
	private static final int FROM = 0;
	private static final int TO = 50_000;
	private static final int THREADS = 4;

	public static void main(String[] args) throws InterruptedException {
		PrimeNumbersSearcher cashedSearchAction = new CashedSearcher(FROM, TO);
		PrimeNumbersSearcher localSearchAction = new LocalSearcher(FROM, TO);

		ConcurrentSearcher searcher = new ExecutorSearcher(THREADS);
		benchmark(searcher, cashedSearchAction, "cashed search with ExecutorSearcher :");
		benchmark(searcher, localSearchAction, "local search with ExecutorSearcher :");
		searcher.stop();

		searcher = new ThreadSearcher(THREADS);
		benchmark(searcher, cashedSearchAction, "cashed search with ThreadSearcher :");
		benchmark(searcher, localSearchAction, "local search with ThreadSearcher :");
		searcher.stop();
	}

	private static void benchmark(ConcurrentSearcher searcher, PrimeNumbersSearcher action, String msg) throws InterruptedException {
		long start = System.currentTimeMillis();
		List<Integer> result = searcher.search(action);
		long end = System.currentTimeMillis();
		System.out.println("\n" + msg + (end - start));
		System.out.println(result);
	}
}
