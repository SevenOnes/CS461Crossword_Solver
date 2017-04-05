package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class Stats extends JPanel {

	/**
	 * Create the panel.
	 */
	public Stats() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attempts:");
		lblNewLabel.setBounds(12, 28, 70, 24);
		add(lblNewLabel);
		
		JLabel label = new JLabel("0");
		label.setBounds(94, 33, 35, 15);
		add(label);
		
		JLabel lblRevealed = new JLabel("Revealed:");
		lblRevealed.setBounds(141, 33, 70, 15);
		add(lblRevealed);
		
		JLabel label_1 = new JLabel("0");
		label_1.setBounds(223, 33, 35, 15);
		add(label_1);
		
		JLabel lblStats = new JLabel("Stats");
		lblStats.setBounds(188, 6, 70, 15);
		add(lblStats);
		
		JLabel lblTime = new JLabel("Time Taken:");
		lblTime.setBounds(270, 33, 85, 15);
		add(lblTime);
		
		JLabel label_2 = new JLabel("0");
		label_2.setBounds(367, 33, 22, 15);
		add(label_2);

	}

}
