package CoffeeExpress;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CoffeExpress.Samsung.EXPsamsung123;
import CoffeExpress.Samsung.Samsung;
import CoffeeExpress.Siemens.EXPsiemens123;
import CoffeeExpress.Siemens.Siemens;

public class Runner {

	public static void main(String[] args) {
		expressService();
	}

	private static void expressService() {
		JPanel expressPanel = new JPanel();
		JButton expressButton = new JButton("Express");
		Siemens siemens = new EXPsiemens123();
		Samsung samsung = new EXPsamsung123();

		expressButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JPanel panel = new JPanel();
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
						//System.exit(0);
						JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(expressPanel);
				        frame.dispose();
					}
				});
				panel.add(samsungButton);
				panel.add(siemensButton);
				panel.add(finishButton);
				expressPanel.removeAll();
				expressPanel.add(panel);
				expressPanel.revalidate();
				expressPanel.repaint();
			}
		});
		expressPanel.add(expressButton);

		JFrame frame = new JFrame("Coffee Express");
		
		
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
		frame.add(expressPanel);
		frame.setVisible(true);
	}

}
