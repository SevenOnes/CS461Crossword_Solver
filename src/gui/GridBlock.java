package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridBlock extends JPanel {

	// Properties
	private boolean valid;

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
		valid = true;		
	}
	
	// Methods
	public void setValid(boolean value)
	{
		valid = value;
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
