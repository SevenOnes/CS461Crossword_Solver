package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class GridBlock extends JPanel {

	// Properties
	private boolean valid;
	private JLabel  name;
	private String alpha;
	private char   value;
	private boolean solved;
	
	// Constructor
	public GridBlock(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(valid)
					valid = false;
				else
					valid = true;
				repaint();
			}
		});
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(12, 12, 70, 15);
		add(label);
		name = label;
		valid = true;	
		alpha = "";
		solved = false;
	}

	// Methods
	public void setSolved(boolean value)
	{
		solved = value;
	}
	
	public boolean getSolved()
	{
		return solved;
	}
	
	public void setClueNo(char name)
	{
		String s = "" + name;
		this.name.setText(s);	
		//		repaint();
	}
	
	public String getClueNo()
	{
		return name.getText();
	}

	public void setValid(boolean value)
	{
		valid = value;
		repaint();
	}

	public boolean getValid()
	{
		return valid;
	}
	
	public void setAlpha(char value)
	{
		String s = "" + value;
		alpha = s;
	}
	
	public String getAlpha()
	{
		return alpha;
	}
	
	public void setValue(char value)
	{
		this.value = value;
		String h = name.getText() + " " + value;
		this.name.setText(h);
	}
	
	public String getValue()
	{
		String s = "" + value;
		return s;				
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (valid)
			this.setBackground(Color.WHITE);
		else
			this.setBackground(Color.BLACK);
	}
}
