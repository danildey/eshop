package com.epam.preprod.eshop.task8.part3;

import com.epam.preprod.eshop.task8.Initializer;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

public class DemoSequenceFinder {
	public static void main(String[] args) throws Exception {
		Initializer initializer = new Initializer();
		InputInteraction interaction = initializer.init();

		SequenceFinder finder = new SequenceFinder(interaction);
		finder.startFinder();
	}
}
