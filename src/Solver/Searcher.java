package Solver;

import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gui.GridBlock;
import gui.Hint;

public class Searcher{

	// Properties
	private String phantomJSpath = "/home/hamza/Dropbox/Courses/Semester 6/CS461/Project/eclipse/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";
	Hint[] 		  hints;	// lower half contains Down hints, the other contains Across hints.
	// Has a mehod getGoogleResult which returns an array of size 5, each entry of array contains the entire text 
	// of the corresponding google hit of that hint.
	GridBlock[][] grid;		// has methods getValid, which tells if the block is valid or not, and getClueNo, which tells if this block 
	// has a clue number or not. Empty String means that there is no clueNo

	// Constructor
	public Searcher(Hint hints[], GridBlock grid[][])
	{
		this.hints = hints;
		this.grid = grid;
	}

	// Methods
	// Refines the goolgeResult of all hints to only the possible length of the word that might fight in the grid for that hint
	public boolean refine()
	{
		System.out.println("	There is too much data for each hint.Lets refine it to the size of word which will fit the grid for that hint!");
		// Process each hint
		for(int i = 0; i < hints.length; i++)
		{
			// Find the possible word length for this hint
			System.out.println("    	Finding the word-length for hint number " + hints[i].getValue());
			int wordLength = 0;
			for(int x = 0; x < 5; x++)
			{
				for(int y = 0; y < 5; y++)
				{
					// Find the start grid block of the hint
					//					System.out.println("grid value is " + grid[j][k].getClueNo() + " and hint value is " + hints[i].getValue());
					if(grid[x][y].getClueNo().equals(hints[i].getValue()))
					{
						//						System.out.println("Hint " + i + " Starts at block " + x + y);
						if(i > 4) // It's an across hint
						{
							int tempY = y;
							while(tempY < 5 && grid[x][tempY].getValid())
							{
								wordLength++;
								tempY++;
							}
						}
						else	  // It's a down hint
						{							
							int tempX = x;
							while(tempX < 5  && grid[tempX][y].getValid())
							{
								wordLength++;
								tempX++;
							}
						}
					}
				}
			}			
			System.out.println("			Word length of hint number " + hints[i].getValue() + " is " + wordLength);

			// Refine the googleResult according to the possible word-length			
			String[] googleResults = hints[i].getGoogleResult();
			for(int j = 0; j < googleResults.length; j++)
			{
				String result = googleResults[j];
				StringTokenizer tokenizer = new StringTokenizer(result);
				String resultRefined = "";
				while(tokenizer.hasMoreTokens())
				{
					String word = tokenizer.nextToken();
					if(word.length() == wordLength)
					{
						resultRefined = resultRefined + " " + word;
					}
				}
				googleResults[j] = resultRefined;
			}

			hints[i].setGoogleResult(googleResults);
			hints[i].setWordLength(wordLength);
		}

		System.out.println("	Now lets get rid of all the data which does not fit the wordlength of these hints!");
		return true;
	}

	public boolean search()
	{
		System.out.println(" Lets solve the puzzle using this refined data set!");
		// Process each hint
		for(int i = 0; i < hints.length;i++)
		{
			// Process each google result for that hint
			String[] googleResults = hints[i].getGoogleResult();
			for(int j = 0; j < googleResults.length; j++)
			{
				StringTokenizer tokenizer = new StringTokenizer(googleResults[j]);
				String word = "";
				// Process each possible word
				while(tokenizer.hasMoreTokens())
				{
					String testWord = tokenizer.nextToken();
					int    testWordCount = 0;

					for(int x = 0; x < 5; x++)
					{
						for(int y = 0; y < 5; y++)
						{
							// Find the start grid block of the hint
							//					System.out.println("grid value is " + grid[j][k].getClueNo() + " and hint value is " + hints[i].getValue());
							if(grid[x][y].getClueNo().equals(hints[i].getValue()))
							{
								//						System.out.println("Hint " + i + " Starts at block " + x + y);
								if(i > 4) // It's an across hint
								{
									int tempY = y;
									while(tempY < 5 && grid[x][tempY].getValid())
									{
										if(Character.toLowerCase(grid[x][tempY].getAlpha().charAt(0)) == Character.toLowerCase(testWord.charAt(testWordCount)))
										{
											if(grid[x][tempY].getSolved() == false)
											{
												System.out.println(Character.toUpperCase(testWord.charAt(testWordCount)) + " Alphabet found for position " + tempY + " " + x);
												grid[x][tempY].setValue(Character.toUpperCase(testWord.charAt(testWordCount)));
												grid[x][tempY].setSolved(true);
											}
										}
										testWordCount++;	
										tempY++;
									}
								}
								else	  // It's a down hint
								{							
									int tempX = x;
									while(tempX < 5  && grid[tempX][y].getValid())
									{
										if(Character.toLowerCase(grid[y][tempX].getAlpha().charAt(0)) == Character.toLowerCase(testWord.charAt(testWordCount)))
										{
											if(grid[y][tempX].getSolved() == false)
											{
												System.out.println(Character.toLowerCase(testWord.charAt(testWordCount)) + " Alphabet found for position " + tempX + " " + y);
												grid[y][tempX].setValue(Character.toUpperCase(testWord.charAt(testWordCount)));
												grid[y][tempX].setSolved(true);
											}
										}			

										testWordCount++;
										tempX++;
									}
								}
							}
						}
					}

				}
			}
		}

		return true;
	}
}
