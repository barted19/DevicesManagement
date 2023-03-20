package CoffeeExpress;

import java.util.Scanner;

import CoffeeExpress.Siemens.EXPsiemens123;



public class Runner {

	public static void main(String[] args) {
		expressService();
	}

	private static void expressService() {

		Scanner scanner = new Scanner(System.in);
		Express express = new EXPsiemens123();

		int option = 0;

		System.out.println("1.Make Coffee;");
		System.out.println("2.Turn Off;");
		System.out.println("3.Charge the battery;");
		System.out.println("4.Refill the water reservoir;");
		System.out.println("5.Refill the coffee reservoir;");
		System.out.println("6.Finish the program;");

		while (option != 6) {
			option = scanner.nextInt();

			switch (option) {
			case 1:
				express.makeCoffee();
				break;
			case 2:
				express.turnOff();
				break;
			case 3:
				express.charging();
				break;
			case 4:
				express.waterRefilling();
				break;
			case 5:
				express.coffeeRefilling();
				break;
			}
		}

	}

}
