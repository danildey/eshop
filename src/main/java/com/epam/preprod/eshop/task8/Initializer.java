package com.epam.preprod.eshop.task8;

import com.epam.preprod.eshop.consoleio.DataInput;
import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.consoleio.FacadeDataIo;
import com.epam.preprod.eshop.consoleio.implementation.ConsoleInput;
import com.epam.preprod.eshop.consoleio.implementation.ConsoleIo;
import com.epam.preprod.eshop.consoleio.implementation.ConsolePrinter;
import com.epam.preprod.eshop.tools.inputinteraction.ConsoleInteractionManually;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

public class Initializer {
	private DataInput input;
	private DataOutput output;
	private FacadeDataIo dataIo;
	private InputInteraction interaction;

	public InputInteraction init() {
		input = new ConsoleInput();
		output = new ConsolePrinter();
		dataIo = new ConsoleIo(input, output);
		interaction = new ConsoleInteractionManually(dataIo);
		return interaction;
	}
}
