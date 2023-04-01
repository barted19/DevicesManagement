package StereoSystem;

import java.awt.event.ActionEvent;

public interface StereoSystem {
	
	void playSound();

	void turnOff();

	void powerAction();
	
	void turnUpVolume();
	
	void turnDownVolume();
	
	void changeChannelNumber(int number);
	
	void channelUp();
	
	void channelDown();

	void charging();

	void createGUI();

	void actionPerformed(ActionEvent e);
	
	void closeGUI();
}
