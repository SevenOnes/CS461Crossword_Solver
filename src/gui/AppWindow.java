package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AppWindow {

	// Properties
	private GridGUI   gridGUI;
	private HintsGUI  hintsGUI;
	private JFrame frame;
	private Hint hints[];	// lower half contains down hints, the other contains across hints.

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
		hints = null;
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

		HintsGUI hints1 = new HintsGUI();
		hints1.setBounds(424, 12, 264, 400);
		frame.getContentPane().add(hints1);
		hintsGUI = hints1;

		Stats stats1 = new Stats();
		stats1.setBounds(12, 424, 400, 64);
		frame.getContentPane().add(stats1);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test();
			}
		});
		btnStart.setBounds(424, 440, 70, 25);
		frame.getContentPane().add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(618, 440, 70, 25);
		frame.getContentPane().add(btnStop);

		GridGUI grid1 = new GridGUI();
		grid1.getContentPane().setBackground(Color.WHITE);
		grid1.getContentPane().setForeground(Color.WHITE);
		grid1.setBounds(12, 12, 400, 400);
		frame.getContentPane().add(grid1);
		gridGUI = grid1;

		JButton btnNewButton = new JButton("Get NYT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// NYT Button Pressed
				getNYT();
			}
		});
		btnNewButton.setBounds(506, 440, 100, 25);
		frame.getContentPane().add(btnNewButton);
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
		Hint[] hintsData = getHints();
		for(int i = 0; i < hintsData.length; i++)
			System.out.println(hintsData[i].getValue() + ": " + hintsData[i].getText() + '\n');
	}

	public boolean[][] getGridConfig()
	{
		return gridGUI.getGridConfig();
	}

	public Hint[] getHints()
	{
		return hints;
	}

	public void getNYT()
	{
		// Parse the HTML
		String URL = "https://www.nytimes.com/crosswords/game/mini";

		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("URL Connection Failed!");
		}

		// Forming the hints
		Elements clueList = doc.getElementsByClass("clue-list");
		int hintsCount = 5;
		if(hints == null)
			hints = new Hint[10];
		
		for (Element element : clueList) 
		{
			Elements subClueList = element.getAllElements();

			for(Element subElement : subClueList)
			{		            	
				if(subElement.val() != "")
				{
					hints[hintsCount] = new Hint(subElement.val(), subElement.text());
					hintsCount++;
					if(hintsCount > 9)
						hintsCount = 0;
				}
			}
		}
		hintsGUI.setHints(hints);	// update GUI with NYT hints
		
		// Testing hints on console
//		for(int i = 0;i< hints.length;i++)
//		{
//			System.out.println(hints[i].getValue() + ": " + hints[i].getText());
//		}

		// Forming the Grid
		boolean gridConfig[][] = new boolean[5][5];
		Elements gridConfigNYT = doc.getElementsByClass("flex-cell");
		int countGridNYT = 0;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(gridConfigNYT.get(countGridNYT).className().contains("black"))
					gridConfig[i][j] = false;
				else
					gridConfig[i][j] = true;
//				System.out.println(gridConfigNYT.get(countGridNYT).data());
				countGridNYT++;
			}
		}
		gridGUI.setGridConfig(gridConfig);
		
//		Elements test = doc.getElementsByClass("flex-table");
//		System.out.println(test.toString());
	}
}
