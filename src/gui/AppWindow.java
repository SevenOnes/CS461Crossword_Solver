package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppWindow {
	
	// Properties
	private Grid   grid;
	private Hints  hints;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Hints hints1 = new Hints();
		hints1.setBounds(424, 12, 264, 400);
		frame.getContentPane().add(hints1);
		hints = hints1;
		
		Stats stats1 = new Stats();
		stats1.setBounds(12, 424, 400, 64);
		frame.getContentPane().add(stats1);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test();
			}
		});
		btnStart.setBounds(424, 440, 117, 25);
		frame.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(571, 440, 117, 25);
		frame.getContentPane().add(btnStop);
		
		Grid grid1 = new Grid();
		grid1.getContentPane().setBackground(Color.WHITE);
		grid1.getContentPane().setForeground(Color.WHITE);
		grid1.setBounds(12, 12, 400, 400);
		frame.getContentPane().add(grid1);
		grid = grid1;
	}
	
	// Methods
	public void test()
	{
		System.out.println("\nGrid Config: ");
		boolean[][] gridConfig = getGridConfig();
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				System.out.print(gridConfig[i][j] + "  ");
			}
			System.out.println();
		}
		
		System.out.println("\nHints: ");
		String[] hintsData = getHints();
		for(int i = 0; i < hintsData.length; i++)
			System.out.println(i + " " + hintsData[i] + '\n');
	}
	
	public boolean[][] getGridConfig()
	{
		return grid.getGridConfig();
	}
	
	public String[] getHints()
	{
		return hints.getHints();
	}
}
