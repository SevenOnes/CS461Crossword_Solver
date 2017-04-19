package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HintsGUI extends JPanel {
	// Properties
	private Hint 	  hints[];
	private JTextPane panes[];
	private JLabel   labels[];

	/**
	 * Create the panel.
	 */
	public HintsGUI() {
		setLayout(new GridLayout(0, 4, 0, 0));

		hints = new Hint[10];
		panes = new JTextPane[10];
		labels = new JLabel[10];

		JLabel lblAcross = new JLabel("   Across:");
		add(lblAcross);

		JLabel label_2 = new JLabel("");
		add(label_2);

		JLabel lblDown = new JLabel("    Down:");
		add(lblDown);

		JLabel label_3 = new JLabel("");
		add(label_3);

		JLabel label5 = new JLabel("        1:");
		add(label5);
		labels[5] = label5;

		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane_5);
		panes[5] = textPane_5;

		JLabel label0 = new JLabel("        1:");
		add(label0);
		labels[0] = label0;

		JTextPane textPane0 = new JTextPane();
		textPane0.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane0);
		panes[0] = textPane0;

		JLabel label6 = new JLabel("        6:");
		add(label6);
		labels[6] = label6;

		JTextPane textPane6 = new JTextPane();
		textPane6.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane6);
		panes[6] = textPane6;

		JLabel label1 = new JLabel("        2:");
		add(label1);
		labels[1] = label1;

		JTextPane textPane1 = new JTextPane();
		textPane1.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane1);
		panes[1] = textPane1;

		JLabel label7 = new JLabel("        7:");
		add(label7);
		labels[7] = label7;

		JTextPane textPane7 = new JTextPane();
		textPane7.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane7);
		panes[7] = textPane7;

		JLabel label2 = new JLabel("        3:");
		add(label2);
		labels[2] = label2;

		JTextPane textPane2 = new JTextPane();
		textPane2.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane2);
		panes[2] = textPane2;

		JLabel label8 = new JLabel("        8:");
		add(label8);
		labels[8] = label8;

		JTextPane textPane8 = new JTextPane();
		textPane8.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane8);
		panes[8] = textPane8;

		JLabel label3 = new JLabel("        4:");
		add(label3);
		labels[3] = label3;

		JTextPane textPane3 = new JTextPane();
		textPane3.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane3);
		panes[3] = textPane3;

		JLabel label9 = new JLabel("        9:");
		add(label9);
		labels[9] = label9;

		JTextPane textPane9 = new JTextPane();
		textPane9.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane9);
		panes[9] = textPane9;

		JLabel label4 = new JLabel("        5:");
		add(label4);
		labels[4] = label4;

		JTextPane textPane4 = new JTextPane();
		textPane4.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(textPane4);
		panes[4] = textPane4;
	}

	// Methods
	public Hint[] getManualHints()
	{
		for (int i = 0; i < panes.length; i++)
		{
			hints[i].setText(panes[i].getText());
		}

		return hints;
	}

	public void updateHints()
	{
		for(int i = 0; i < hints.length; i++)
		{
			String value = "        " + hints[i].getValue();
			String text  = hints[i].getText();
			panes[i].setText(text);
			labels[i].setText(value);
		}
	}

	public void setHints(Hint[] hints)
	{
		this.hints = hints;
		updateHints();
	}
}
