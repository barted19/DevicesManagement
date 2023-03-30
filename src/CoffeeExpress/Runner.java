package CoffeeExpress;

import CoffeeExpress.Siemens.EXPsiemens123;

public class Runner {

	public static void main(String[] args) {
		expressService();
	}

	private static void expressService() {

		Express express = new EXPsiemens123();
		express.createGUI();

	}

}
