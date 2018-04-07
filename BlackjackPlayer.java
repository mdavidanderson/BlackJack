public class BlackjackPlayer
{
	private String name;
	private BlackjackHand hand; 
	private boolean stayed;
	private boolean busted;
	
	public BlackjackPlayer( String name )
	{
		this.name = name;
		this.hand = new BlackjackHand();
		this.stayed = false;
		this.busted = false;
	}
	
	public BlackjackPlayer( String name, BlackjackHand hand )
	{
		this.name = name;
		this.hand = hand;
		this.stayed = false;
		this.busted = false;
	}
	
	public BlackjackPlayer( String name, BlackjackCardNode card )
	{
		this.name = name;
		this.hand = new BlackjackHand( card );
		this.stayed = false;
		this.busted = false;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public BlackjackCardNode getHand()
	{
		return hand.getHand();
	}
	
	public void dealCardToHand( BlackjackCardNode card )
	{
		this.hand.addCard( card );
	}
	
	public int getHandTotal()
	{
		return this.hand.getHandTotal();
	}
	
	public boolean isStayed()
	{
		return this.stayed;
	}
	
	public boolean isBusted()
	{
		return this.busted;
	}
	
	public void setStayed(boolean b)
	{
		this.stayed = b;
	}
	
	public void setBusted(boolean b)
	{
		this.busted = b;
	}
	
	
}