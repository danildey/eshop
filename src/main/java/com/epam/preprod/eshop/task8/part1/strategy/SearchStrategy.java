package com.epam.preprod.eshop.task8.part1.strategy;

public enum SearchStrategy {
	EXECUTOR, THREAD;

	public static SearchStrategy getStrategy(int id) {
		return SearchStrategy.values()[id-1];
	}
}
