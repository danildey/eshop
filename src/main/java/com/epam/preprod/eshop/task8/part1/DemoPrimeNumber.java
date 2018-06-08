package com.epam.preprod.eshop.task8.part1;

import com.epam.preprod.eshop.task8.Initializer;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

public class DemoPrimeNumber {

	public static void main(String[] args) throws InterruptedException {
		Initializer initializer = new Initializer();
		InputInteraction interaction = initializer.init();

		PrimeNumberFinder finder = new PrimeNumberFinder(interaction);
		finder.startFinder();
	}
}
