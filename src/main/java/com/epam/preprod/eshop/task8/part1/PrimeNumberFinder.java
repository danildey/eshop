package com.epam.preprod.eshop.task8.part1;

import com.epam.preprod.eshop.task8.part1.strategy.ConcurrentSearcher;
import com.epam.preprod.eshop.task8.part1.strategy.ExecutorSearcher;
import com.epam.preprod.eshop.task8.part1.strategy.SearchStrategy;
import com.epam.preprod.eshop.task8.part1.strategy.ThreadSearcher;
import com.epam.preprod.eshop.task8.part1.thread.CashedSearcher;
import com.epam.preprod.eshop.task8.part1.thread.LocalSearcher;
import com.epam.preprod.eshop.task8.part1.thread.PrimeNumbersSearcher;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

public class PrimeNumberFinder {
    private static final String STRATEGY = "Enter strategy\n 1.THREAD\n 2.EXECUTOR(default)";
    private static final String FROM = "Enter from:";
    private static final String TO = "Enter to:";
    private static final String IS_CASHED = "Is cashed ( true/false ):";
    private static final String THREADS_NUMBER = "Enter the number of threads:";

    private InputInteraction interaction;

    public PrimeNumberFinder(InputInteraction interaction) {
        this.interaction = interaction;
    }

    public void startFinder() throws InterruptedException {
        int strategyId = initStrategyId();
        int from = interaction.readInteger(FROM);
        int to = interaction.readInteger(TO);
        boolean isCashed = interaction.readBoolean(IS_CASHED);
        int threadsNumber = interaction.readInteger(THREADS_NUMBER);

        PrimeNumbersSearcher searcherThread = initPrimeNumberSearcher(from, to, isCashed);
        ConcurrentSearcher concurrentSearcher = initConcurrentSearcher(strategyId, threadsNumber);

        System.out.println(concurrentSearcher.search(searcherThread));
        concurrentSearcher.stop();
    }

    private int initStrategyId() {
        int strategyId = 0;
        while (strategyId < 1 || strategyId > SearchStrategy.values().length) {
            strategyId = interaction.readInteger(STRATEGY);
        }
        return strategyId;
    }

    private ConcurrentSearcher initConcurrentSearcher(int strategyId, int threadsNumber) {
        ConcurrentSearcher concurrentSearcher;
        switch (SearchStrategy.getStrategy(strategyId)) {
            case THREAD:
                concurrentSearcher = new ThreadSearcher(threadsNumber);
                break;
            default:
                concurrentSearcher = new ExecutorSearcher(threadsNumber);
        }
        return concurrentSearcher;
    }

    private PrimeNumbersSearcher initPrimeNumberSearcher(int from, int to, boolean isCashed) {
        PrimeNumbersSearcher searcherThread;
        if (isCashed) {
            searcherThread = new CashedSearcher(from, to);
        } else {
            searcherThread = new LocalSearcher(from, to);
        }
        return searcherThread;
    }
}
