package App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CoffeeExpress.Samsung.EXPsamsung123;
import CoffeeExpress.Samsung.Samsung;
import CoffeeExpress.Siemens.EXPsiemens123;
import CoffeeExpress.Siemens.Siemens;
import StereoSystem.Sony.SSsony123;
import StereoSystem.Sony.Sony;

public class Runner {

	public static void main(String[] args) {
		expressService();
		stereoSystemService();
		createAndShowGUI();
	}

	
	static JPanel mainPanel = new JPanel();
	static JPanel stereoPanel = new JPanel();
	static JPanel expressPanel = new JPanel();
	
	private static void expressService() {
		
		JButton expressButton = new JButton("Express");
		
		Siemens siemens = new EXPsiemens123();
		Samsung samsung = new EXPsamsung123();

		expressButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel();
				JButton returnButton = new JButton("Return Button");//
				JButton samsungButton = new JButton("Samsung");
				JButton siemensButton = new JButton("Siemens");
				JButton finishButton = new JButton("Finish Program");

				samsungButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						samsung.createGUI();

					}
				});
				siemensButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						siemens.createGUI();

					}
				});
				finishButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(expressPanel);
						frame.dispose();
					}
				});
				
				returnButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						
					}});
				
				panel.add(returnButton);
				panel.add(samsungButton);
				panel.add(siemensButton);
				panel.add(finishButton);
				expressPanel.removeAll();
				expressPanel.add(panel);
				expressPanel.revalidate();
				expressPanel.repaint();

				// hide button Stereo System
				stereoPanel.setVisible(false);
			}
		});
		expressPanel.add(expressButton);

		// add buttons to main panel

		mainPanel.add(expressPanel);

		

	}

	private static void stereoSystemService() {
		
		JButton stereoButton = new JButton("Stereo System");//
		Sony sony = new SSsony123();//
		

		stereoButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JButton sonyButton = new JButton("Sony");
				
				sonyButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sony.createGUI();
						
					}});
				panel.add(sonyButton);
				
				stereoPanel.removeAll();
				stereoPanel.add(panel);
				stereoPanel.revalidate();
				stereoPanel.repaint();

				// hide button Stereo System
				expressPanel.setVisible(false);
				
			}
		});
		stereoPanel.add(stereoButton);
		mainPanel.add(stereoPanel);

		
	}
	
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Devices Management");

		// CLOSING WINDOW

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure to close program?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				}
			}
		});

		frame.setSize(500, 500);

		frame.add(mainPanel);

		frame.setVisible(true);
	}

}
