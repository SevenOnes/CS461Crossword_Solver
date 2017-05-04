package Solver;

import java.util.ArrayList;
import java.util.List;

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

import gui.Hint;

public class Scrapper {
	// Properties
	Hint hints[];
//	private String phantomJSpath = "/home/hamza/Dropbox/Courses/Semester 6/CS461/Project/eclipse/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";

	// Constructor
	public Scrapper(Hint hints[])
	{
		this.hints = hints;
	}

	// Methods
	// After running this method, every hint will contain the text of top 5 google hits
	@SuppressWarnings("all") 
	public void scrap()
	{
		System.out.println("Lets Google The Hints!");
		// Setting up PhantomJS
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setJavascriptEnabled(true);                
//		//				caps.setCapability("takesScreenshot", true);  
//		caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSpath);		
//		WebDriver driver = new  PhantomJSDriver(caps);
//		WebDriver driver2 = new  PhantomJSDriver(caps);
//		driver.manage().window().setSize(new Dimension(1280, 1024));
//		driver2.manage().window().setSize(new Dimension(1280, 1024));
		
		WebDriver driver = new FirefoxDriver();		// Using Firefox		
		WebDriver driver2 = new FirefoxDriver();

		// Process each hint
		for(int i = 0; i < hints.length; i++)
		{
			ArrayList<String> googlePages  = new ArrayList<String>();
			System.out.println("	Googling Hint Number " + hints[i].getValue());
			String[] googleResult = new String[2];	// Change this size to change the number of hits to be visited for each hint				

			driver.get("http://www.google.com");
			WebElement element = driver.findElement(By.name("q"));
			CharSequence searchQuery = hints[i].getText() + " crossword clue\n";
			element.sendKeys(searchQuery);
			element.submit();
			// wait until the google page shows the result
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
			List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

			// this are all the links you like to visit
			for (WebElement webElement : searchResults)
			{
				googlePages.add(webElement.getAttribute("href"));
			}

			// visit those links
			for (int j = 0; j < googleResult.length; j++)
			{
				driver2.get(googlePages.get(j));
				
				(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
				
				WebElement text = driver2.findElement(By.tagName("body"));
//				System.out.println(googlePages.get(j));	
				googleResult[j] = text.getText();
				System.out.println("		Data from " + " Google Hit number " + (j+1) + " Retrieved!");
			}

			hints[i].setGoogleResult(googleResult);
		}

		driver.close();
		driver2.close();
	}
}
