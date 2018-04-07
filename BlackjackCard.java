public class BlackjackCard
{
	private String suit;
	private String type;
	private int value; 
	
	public BlackjackCard( String suit, String type, int value )
	{
		this.suit = suit;
		this.type = type;
		this.value = value;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setSuit( String suit )
	{
		this.suit = suit;
	}
	
	public void setType( String type )
	{
		this.type = type;
	}
	
	public void setValue( int value )
	{
		this.value = value;
	}
}