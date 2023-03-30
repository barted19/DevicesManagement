package CoffeeExpress;

import java.awt.event.ActionEvent;

public interface Express {

	void makeCoffee();

	void turnOff();

	void powerAction();

	void charging();

	void waterRefilling();

	void coffeeRefilling();

	void createGUI();

	void actionPerformed(ActionEvent e);
}
