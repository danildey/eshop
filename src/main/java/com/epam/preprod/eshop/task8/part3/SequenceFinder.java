package com.epam.preprod.eshop.task8.part3;

import com.epam.preprod.eshop.task8.part3.thread.BiggestSequenceSearcher;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SequenceFinder {
	private static final String ENTER_PATH = "\nEnter path (or stop):";
	private static final String FIRST_INPUT = "First Input: ";
	private static final String SECOND_INPUT = "Second Input: ";
	private static final String SEQUENCE = "\nSequence: ";
	private static final String MAX_LENGTH = "Max length: ";
	private static final String CURRENT_LENGTH = "Current Sequence: ";

	private InputInteraction interaction;
	private BiggestSequenceSearcher searcher;
	private Thread thread;

	public SequenceFinder(InputInteraction interaction) {
		this.interaction = interaction;
		this.searcher = new BiggestSequenceSearcher();
		this.thread = new Thread(searcher);
	}

	public void startFinder() throws Exception {
		thread.start();
		String resource;
		while (!(resource = interaction.readString(ENTER_PATH)).equals("stop")) {
			Path path = null;
			try {
				path = Paths.get(Thread.currentThread().getContextClassLoader().getResource(resource).toURI());
			} catch (URISyntaxException | NullPointerException e) {
				System.out.println("Wrong path, or file does not exist.");
				continue;
			}
			searcher.initData(path);
			searcher.search();
			monitorProgress();
			showResults();
		}
		stopFinder();
	}

	private void monitorProgress() throws InterruptedException {
		while (searcher.isAlive()) {
			System.out.println(CURRENT_LENGTH + searcher.getMaxLength());
			Thread.sleep(1);
		}
	}

	private void showResults() {
		String result = new String(searcher.getResult());
		System.out.println(SEQUENCE + result);
		System.out.println(MAX_LENGTH + searcher.getMaxLength());
		System.out.println(FIRST_INPUT + searcher.getFirstInput());
		System.out.println(SECOND_INPUT + searcher.getSecondInput());
	}

	private void stopFinder() {
		thread.interrupt();
		System.out.println("Searcher stopped.");
	}
}
