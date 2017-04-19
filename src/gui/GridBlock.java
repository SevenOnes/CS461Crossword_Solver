package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class GridBlock extends JPanel {

	// Properties
	private boolean valid;
	private JLabel  name;

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
	}
	
	// Methods
	public void setName(String name)
	{
		this.name.setText(name);	
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
