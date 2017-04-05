package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;

public class Grid extends JInternalFrame {
	
	// Properties
	private GridBlock grid[][];

	// Constructor
	public Grid() {
		setTitle("                                              HOLMES                                              ");
		// Sets up the internal frame
		setBorder(null);
		getContentPane().setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(5, 5, 0, 0));
		setVisible(true);
		setFrameIcon(null);
		
		// Draws the grid of blocks
		grid = new GridBlock[5][5];
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				GridBlock block = new GridBlock();
				getContentPane().add(block);
				grid[i][j] = block;
			}
		}
		grid[0][0].setName("1");
		grid[0][1].setName("2");
		grid[0][2].setName("3");
		grid[0][3].setName("4");
		grid[0][4].setName("5");
		grid[1][0].setName("6");
		grid[2][0].setName("7");
		grid[3][0].setName("8");
		grid[4][0].setName("9");
	}
	
	// Methods
	public boolean getBlockValid(int x, int y)
	{
		return grid[x][y].getValid();
	}
	
	// Returns the validity of gridblocks in a corresponding 2-d array 
	public boolean[][] getGridConfig()
	{
		boolean gridConfig[][] = new boolean[5][5];
				
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				gridConfig[i][j] = grid[i][j].getValid();
			}
		}
		
		return gridConfig;
	}
}