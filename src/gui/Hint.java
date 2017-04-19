package gui;

public class Hint {
	// Properties
	private String value;
	private String text;
	
	// Constructors
	Hint(String value, String text)
	{
		this.value = value;
		this.text = text;
	}
	
	Hint()
	{
		value = "";
		text  = "";
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
}
