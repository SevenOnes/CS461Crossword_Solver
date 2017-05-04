package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Solver.Scrapper;
import Solver.Searcher;

public class AppWindow {

	// Properties
	private GridGUI   gridGUI;
	private HintsGUI  hintsGUI;
	private JFrame frame;
	private Hint hints[];	// lower half contains Down hints, the other contains Across hints.
	private String phantomJSpath = "/home/hamza/Dropbox/Courses/Semester 6/CS461/Project/eclipse/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";
	private GridBlock grid[][];

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
		grid  = null;
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
	public void setVisible(boolean value)
	{
		frame.setVisible(value);
	}

	// Run Button Pressed 
	public void test()
	{
		//		System.out.println("\nGrid Config: ");
		//		for(int i = 0; i < 5; i++)
		//		{
		//			for(int j = 0; j < 5; j++)
		//			{
		//				System.out.print(grid[i][j].getValid() + "  ");
		//			}
		//			System.out.println();
		//		}

		//		System.out.println("\nHints: ");
		//		for(int i = 0; i < hints.length; i++)
		//			System.out.println(hints[i].getValue() + ": " + hints[i].getText() + '\n');

		// Instantiate and Run Scrapper
		Scrapper scrapper = new Scrapper(hints);
		scrapper.scrap();	

		// Instantiate and Run Searcher
		Searcher searcher = new Searcher(hints, grid);
		searcher.refine();
		searcher.search();
	}

	public GridBlock[][] getGridConfig()
	{
		return grid;
	}

	public Hint[] getHints()
	{
		return hints;
	}

	public void getNYT()
	{
		System.out.println("Getting Today's Puzzle from NYT!");
		// Parse the HTML
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);                
		//		caps.setCapability("takesScreenshot", true);  
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSpath);
		WebDriver driver = new  PhantomJSDriver(caps);
		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.get("https://www.nytimes.com/crosswords/game/mini");	

		// Clear the puzzle and timer
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[1]/button")));
		driver.findElement(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[1]/button")).click();

		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[1]/ul/li[4]/span")));
		driver.findElement(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[1]/ul/li[4]/span")).click();

		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modals-container']/div[4]/div/div[2]/button[2]")));
		driver.findElement(By.xpath(".//*[@id='modals-container']/div[4]/div/div[2]/button[2]")).click();




		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[2]/button")));
		driver.findElement(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[2]/button")).click();

		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[2]/ul/li[3]/span")));
		driver.findElement(By.xpath(".//*[@id='puzzle-toolbar']/div/div[5]/div[2]/ul/li[3]/span")).click();

		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='modals-container']/div[4]/div/div[2]/button[2]")));
		driver.findElement(By.xpath(".//*[@id='modals-container']/div[4]/div/div[2]/button[2]")).click();

		(new WebDriverWait(driver, 10))
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='puzzle-container']/div[2]/div/ol/li")));


		// Forming the hints
		List<WebElement> clueList = driver.findElements(By.xpath(".//*[@id='puzzle-container']/div[2]/div/ol/li"));
		int hintsCount = 5;
		if(hints == null)
			hints = new Hint[10];

		for (WebElement element : clueList) 
		{         	
			if(element.getText() != "")
			{

				//				System.out.println(element.toString());
				hints[hintsCount] = new Hint(element.getAttribute("value"), element.getText());
				hintsCount++;
				if(hintsCount > 9)
					hintsCount = 0;
			}

		}
		hintsGUI.setHints(hints);	// update GUI with NYT hints

		// Forming the Grid				
		List<WebElement> gridConfigNYT = driver.findElements(By.xpath(".//*[@id='clue-bar-and-grid-container']/figure/div/div/div"));

		if (grid == null)
			grid = new GridBlock[5][5];		

		int countGridNYT = 0;
		for(int i = 0; i < 5; i++)
		{			
			for(int j = 0; j < 5; j++)
			{	
				GridBlock block = new GridBlock();

				WebElement flexCell = gridConfigNYT.get(countGridNYT); // Getting flex-cell					

				// Getting grid color
				if(flexCell.getAttribute("class").contains("black"))
					block.setValid(false);
				else
					block.setValid(true);

				// Getting clue number				
				String text = flexCell.getText();
				//				block.setClueNo(text);
				//				System.out.print(text);
				//				System.out.println(" of size " + text.length());
				if(text.length() > 1)
				{
//					System.out.println(text.length());
					char c = flexCell.getText().charAt(0);
					//					System.out.println(c);
					block.setClueNo(c);
					
					char a = flexCell.getText().charAt(2);
					block.setAlpha(a);
				}
				if(text.length() == 1)
				{
					char a = flexCell.getText().charAt(0);
					block.setAlpha(a);
				}

				grid[i][j] = block;
				countGridNYT++;
			}
		}
		gridGUI.setGridConfig(grid);

		driver.quit();
	}
}
