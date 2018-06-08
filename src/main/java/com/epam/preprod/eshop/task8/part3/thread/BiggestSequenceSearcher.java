package com.epam.preprod.eshop.task8.part3.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class BiggestSequenceSearcher implements Runnable {
	private byte[] data;

	private int maxI = 0;
	private int maxJ = 0;
	private volatile int maxLength = 1;

	private volatile boolean isAlive;

	@Override
	public synchronized void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("thread was Interrupted");
			}
			calculateSequence();
			isAlive = false;
		}
	}

	public synchronized void search() {
		isAlive = true;
		notify();
	}

	public synchronized void initData(Path path) throws IOException {
		this.data = Files.readAllBytes(path);
		this.maxI = 0;
		this.maxJ = 0;
		this.maxLength = 0;
	}

	public byte[] getResult() {
		return Arrays.copyOfRange(data, maxJ - maxLength + 1, maxJ + 1);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getFirstInput() {
		return maxI - maxLength + 1;
	}

	public int getSecondInput() {
		return maxJ - maxLength + 1;
	}

	private void calculateSequence() {
		int[][] matrix = new int[data.length][data.length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				if (data[i] == data[j]) {
					if (i != j && i != 0 && j != 0) {
						matrix[i][j] = matrix[i - 1][j - 1] + 1;
					} else {
						matrix[i][j] = 1;
					}
					if (matrix[i][j] > maxLength) {
						maxLength = matrix[i][j];
						maxI = i;
						maxJ = j;
					}
				}
			}
		}
	}
}
