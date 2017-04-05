package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Hints extends JPanel {
	// Properties
	private String 	  hints[];
	private JTextPane panes[];

	/**
	 * Create the panel.
	 */
	public Hints() {
		setLayout(new GridLayout(0, 4, 0, 0));
		
		hints = new String[10];
		panes = new JTextPane[10];
		
		JLabel lblAcross = new JLabel("   Across:");
		add(lblAcross);
		
		JLabel label_2 = new JLabel("");
		add(label_2);
		
		JLabel lblDown = new JLabel("    Down:");
		add(lblDown);
		
		JLabel label_3 = new JLabel("");
		add(label_3);
		
		JLabel label_1 = new JLabel("        1:");
		add(label_1);
		
		JTextPane textPane1A = new JTextPane();
		textPane1A.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane1A);
		panes[0] = textPane1A;
		
		JLabel label = new JLabel("        1:");
		add(label);
		
		JTextPane textPane1D = new JTextPane();
		textPane1D.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane1D);
		panes[1] = textPane1D;
		
		JLabel label_4 = new JLabel("        6:");
		add(label_4);
		
		JTextPane textPane6 = new JTextPane();
		textPane6.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane6);
		panes[6] = textPane6;
		
		JLabel label_6 = new JLabel("        2:");
		add(label_6);
		
		JTextPane textPane2 = new JTextPane();
		textPane2.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane2);
		panes[2] = textPane2;
		
		JLabel label_8 = new JLabel("        7:");
		add(label_8);
		
		JTextPane textPane7 = new JTextPane();
		textPane7.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane7);
		panes[7] = textPane7;
		
		JLabel lblNewLabel = new JLabel("        3:");
		add(lblNewLabel);
		
		JTextPane textPane3 = new JTextPane();
		textPane3.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane3);
		panes[3] = textPane3;
		
		JLabel label_5 = new JLabel("        8:");
		add(label_5);
		
		JTextPane textPane8 = new JTextPane();
		textPane8.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane8);
		panes[8] = textPane8;
		
		JLabel lblNewLabel_1 = new JLabel("        4:");
		add(lblNewLabel_1);
		
		JTextPane textPane4 = new JTextPane();
		textPane4.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane4);
		panes[4] = textPane4;
		
		JLabel lblNewLabel_2 = new JLabel("        9:");
		add(lblNewLabel_2);
		
		JTextPane textPane9 = new JTextPane();
		textPane9.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane9);
		panes[9] = textPane9;
		
		JLabel lblNewLabel_3 = new JLabel("        5:");
		add(lblNewLabel_3);
		
		JTextPane textPane5 = new JTextPane();
		textPane5.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane5);
		panes[5] = textPane5;
	}
	
	// Methods
	public String[] getHints()
	{
		updateHints();
		
		return hints;
	}
	
	public void updateHints()
	{
		for (int i = 0; i < panes.length; i++)
		{
			hints[i] = panes[i].getText();
		}
	}
}
