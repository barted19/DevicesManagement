package CoffeeExpress.Siemens;

import CoffeeExpress.Express;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public abstract class Siemens implements Express {

	public boolean operationStatus = false;
	protected int cup = 1;
	protected int waterReservoir = 40;
	protected int coffeeReservoir = 10;
	protected int battery = 10;
	protected JFrame frame;
	public JPanel panel;

	JTextArea textArea = new JTextArea(20, 20);

	@Override
	public void makeCoffee() {
		operationStatus = true;
		action();

	}

	@Override
	public void turnOff() {
		operationStatus = false;
		cup = 0;
		textArea.append("See you soon :)" + "\n");
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
					// deleting old TEXT
					textArea.setText("");
					// adding new TEXT
					textArea.append("Cup: " + cup + "%" + " | Battery: " + battery + "%" + " | Water reservoir: "
							+ waterReservoir + " ml" + " | Coffee reservoir: " + coffeeReservoir + " gr" + "\n");
					cup++;
					waterReservoir--;

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

					textArea.append("READY! Enjoy your coffee :)" + "\n");
					textArea.append("Press 'Make Coffee' to make another coffee" + "\n");
					textArea.append("Press 'Turn Off' to disable express" + "\n");
					cup = 0;
				}
				if (battery == 0) {

					textArea.append("BATTERY LOW" + "\n");
					textArea.append("Press 'Charge Battery'" + "\n");
				}

				if (waterReservoir == 0) {

					textArea.append("Water Reservoir is EMPTY" + "\n");
					textArea.append("Press 'Refill Water'" + "\n");

				}

				if (coffeeReservoir == 0) {

					textArea.append("Coffee Reservoir is EMPTY" + "\n");
					textArea.append("Press 'Refill Coffee'" + "\n");
				}

				operationStatus = false;
			}
		}).start();
	}

	@Override
	public void charging() {
		battery = 100;
		action();
	}

	@Override
	public void waterRefilling() {
		waterReservoir = 200;
		action();

	}

	@Override
	public void coffeeRefilling() {
		coffeeReservoir = 100;
		action();
	}

	@Override
	public void createGUI() {

		JButton makeCoffeeButton = new JButton("Make Coffee"); //
		JButton turnOffButton = new JButton("Turn Off"); //
		JButton chargeBatteryButton = new JButton("Charge Battery"); //
		JButton refillWaterButton = new JButton("Refill Water"); //
		JButton refillCoffeeButton = new JButton("Refill Coffee"); //
		JButton finishButton = new JButton("Finish Program"); //
		JButton startButton = new JButton("Start Siemens"); //

		// Create the frame
		frame = new JFrame("Coffee Express");
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int result = JOptionPane.showConfirmDialog(frame, "Are you sure to close program?", "Confirm", JOptionPane.YES_NO_OPTION);
		        if (result == JOptionPane.YES_OPTION) {
		            e.getWindow().dispose();
		        }
		    }
		});
		frame.setSize(500, 500);
		textArea.setEditable(false);
		// Create the panel
		panel = new JPanel();

		// Add action listeners to the buttons
		makeCoffeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeCoffee();
			}
		});

		turnOffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turnOff();
			}
		});

		chargeBatteryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				charging();
			}
		});

		refillWaterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waterRefilling();
			}
		});

		refillCoffeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coffeeRefilling();
			}
		});

		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
		        frame.dispose();
			}
		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Create and add the remaining buttons
				panel.add(makeCoffeeButton);
				panel.add(turnOffButton);
				panel.add(chargeBatteryButton);
				panel.add(refillWaterButton);
				panel.add(refillCoffeeButton);
				panel.add(finishButton);

				// Remove the "Start" button
				panel.remove(startButton);

				// Refresh the panel
				panel.revalidate();
				panel.repaint();
			}
		});

		panel.add(startButton);

		// Add the text area to the panel

		panel.add(textArea);

		// Add the panel to the frame
		frame.add(panel);

		// Make the frame visible
		frame.setVisible(true);

	}

}
