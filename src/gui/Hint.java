package gui;

public class Hint {
	// Properties
	private String value;
	private String text;
	private String[] googleResult;
	private boolean solved;
	private int wordLength;
	
	// Constructors
	Hint(String value, String text)
	{
		this.value = value;
		this.text = text;
		googleResult = null;
		solved = false;
		wordLength = 0;
	}	
	
	Hint()
	{
		value = "";
		text  = "";
		googleResult = null;
		solved = false;
		wordLength = 0;
	}
	
	// Methods
	public String getValue()
	{
		return value;
	}

	public String getText()
	{
		return text;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}	
	
	public void setGoogleResult(String[] result)
	{
		googleResult = result;		
	}
	
	public String[] getGoogleResult()
	{
		return googleResult;
	}
	
	public void setSolved(boolean value)
	{
		solved = value;
	}
	
	public boolean getSolved()
	{
		return solved;
	}
	
	public void setWordLength(int value)
	{
		wordLength = value;
	}
	
	public int getWordLength()
	{
		return wordLength;
	}
}
