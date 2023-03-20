package CoffeeExpress.Siemens;

import CoffeeExpress.Express;

public abstract class Siemens implements Express {

	protected boolean operationStatus = false;
	protected int cup = 1;
	protected int waterReservoir = 40;
	protected int coffeeReservoir = 10;
	protected int battery = 10;

	@Override
	public void makeCoffee() {
		operationStatus = true;
		action();

	}

	@Override
	public void turnOff() {
		operationStatus = false;
		System.out.println("See you soon :)");
	}

	@Override
	public void powerAction() {
		if (operationStatus) {
			makeCoffee();
		} else {
			turnOff();
		}
	}

	private void action() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				operationStatus = true;
				int batteryCycle = 0;
				int coffeeCycle = 0;
				while (operationStatus && battery > 0 && cup <= 100 && waterReservoir > 0 && coffeeReservoir > 0) {
					System.out.println("Cup: " + cup + "%" + " | Battery: " + battery + "%" + " | Water reservoir: "
							+ waterReservoir + " ml" + " | Coffee reservoir: " + coffeeReservoir + " gr");
					cup++;
					waterReservoir--;
					// in cycles, there are 6 of the same z values ​​every 1 second batteries and 8 from coffee

					batteryCycle++;
					coffeeCycle++;
					if (batteryCycle % 6 == 0 && coffeeCycle % 1 == 0) {
						battery--;
						coffeeReservoir--;

					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {

					}

				}
				if (cup > 100) {
					System.out.println("READY! Enjoy your coffee :)");
					System.out.println("Press '1' to make another coffee");
					System.out.println("Press '2' to disable express");
					cup = 0;
				}
				if (battery == 0) {
					System.out.println("BATTERY LOW");
					System.out.println("Press '3' to charge the battery");
				}
				
				if (waterReservoir == 0) {
					System.out.println("REFILL THE WATER RESERVOIR");
					System.out.println("Press '4' to refill the water reservoir");
				}
				if (waterReservoir == 200) {
					System.out.println("REFILL THE WATER RESERVOIR");
					System.out.println("Press '1' to continue");
				}
				
				if (coffeeReservoir == 0) {
					System.out.println("REFILL THE COFFEE RESERVOIR");
					System.out.println("Press '5' to refill the coffee reservoir");
				}
				operationStatus = false;
			}
		}).start();
	}

	@Override
	public void charging() {
		battery = 100;

	}

	@Override
	public void waterRefilling() {
		waterReservoir = 200;

	}

	@Override
	public void coffeeRefilling() {
		coffeeReservoir = 100;

	}

}
