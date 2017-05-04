package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;

public class GridGUI extends JInternalFrame {
	
	// Properties
	private GridBlock grid[][];

	// Constructor
	public GridGUI() {
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
				grid[i][j] = block;
			}
		}
		grid[0][0].setClueNo('1');
		grid[0][1].setClueNo('2');
		grid[0][2].setClueNo('3');
		grid[0][3].setClueNo('4');
		grid[0][4].setClueNo('5');
		grid[1][0].setClueNo('6');
		grid[2][0].setClueNo('7');
		grid[3][0].setClueNo('8');
		grid[4][0].setClueNo('9');
		updateContentPane();
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
	
	public void setGridConfig(GridBlock[][] gridConfig)
	{		
		grid = gridConfig;
		updateContentPane();
	}
	
	private void updateContentPane()
	{
		this.getContentPane().removeAll();
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				getContentPane().add(grid[i][j]);
			}
		}
		
		repaint();
	}
}