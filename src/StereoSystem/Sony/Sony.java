package StereoSystem.Sony;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import StereoSystem.StereoSystem;

public abstract class Sony implements StereoSystem {

	public boolean operationStatus = false;
	protected int volume = 1;
	protected int maxVolume = 10;
	protected int minVolume = 1;
	protected int battery = 10;
	protected int channelNumber = 1;
	protected int maxChannelNumber = 10;
	protected int minChannelNumber = 1;
	protected JFrame frame;
	public JPanel panel;

	JTextArea textArea = new JTextArea(20, 20);

	@Override
	public void playSound() {
		operationStatus = true;
		action();
	}

	@Override
	public void turnOff() {
		operationStatus = false;
		textArea.append("Bye bye!" + "\n");
	}

	@Override
	public void powerAction() {
		if (operationStatus) {
			playSound();
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
				while (operationStatus && battery > 0) {
					textArea.setText("");
					textArea.append("Channel: " + channelNumber + " | Power: " + battery + " | Volume: " + volume + "\n");

					batteryCycle++;
					if (batteryCycle % 6 == 0) {
						battery--;
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {

					}
				}
				if (battery == 0) {
					textArea.append("POWER LOW" + "\n");
					textArea.append("Press 'Charge Battery'" + "\n");
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
	public void channelUp() {

		channelNumber += 1;
		if (channelNumber > maxChannelNumber) {
			channelNumber = minChannelNumber;
		}

	}

	@Override
	public void channelDown() {

		channelNumber -= 1;
		if (channelNumber < minChannelNumber) {
			channelNumber = maxChannelNumber;

		}

	}

	@Override
	public void changeChannelNumber(int number) {
		channelNumber = number;
	}

	@Override
	public void turnUpVolume() {

		volume += 1;
		if(volume > maxVolume) {
			volume = maxVolume;
		}

	}

	@Override
	public void turnDownVolume() {

		volume -= 1;
		if (volume < minVolume) {
			volume = minVolume;
		}

	}

	@Override
	public void createGUI() {
		JButton playSoundButton = new JButton("Play Sound");
		JButton turnOffButton = new JButton("Turn Off");
		JButton chargingButton = new JButton("Charge");
		JButton channelUpButton = new JButton("Channel UP");
		JButton channelDownButton = new JButton("Channel DOWN");
		JButton turnUpVolumeButton = new JButton("Turn Volume UP");
		JButton turnDownVolumeButton = new JButton("Turn Volume DOWN");
		JButton changeChannelNumberButton = new JButton("Change Channel Number");

		JButton start2Button = new JButton("Start Playing");
		JButton finishButton = new JButton("Finish Program");

		frame = new JFrame("Stereo System");

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure to close program", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
		frame.setSize(500, 500);
		textArea.setEditable(false);

		panel = new JPanel();

		playSoundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound();
			}
		});

		turnOffButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				turnOff();

			}
		});

		chargingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				charging();

			}
		});

		channelUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				channelUp();

			}
		});

		channelDownButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				channelDown();

			}
		});

		turnUpVolumeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				turnUpVolume();

			}
		});

		turnDownVolumeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				turnDownVolume();

			}
		});

		finishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// sprobuj czegoś innego zamiast cast i zobacz co się stanie
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
				frame.dispose();

			}
		});

		start2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.add(playSoundButton);
				panel.add(turnOffButton);
				panel.add(chargingButton);
				panel.add(channelUpButton);
				panel.add(channelDownButton);
				panel.add(turnUpVolumeButton);
				panel.add(turnDownVolumeButton);

				panel.add(finishButton);

				panel.remove(start2Button);

				panel.revalidate();
				panel.repaint();

			}
		});

		changeChannelNumberButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panel.add(start2Button);
		panel.add(textArea);

		frame.add(panel);
		frame.setVisible(true);

	}

}
