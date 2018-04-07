public class BlackjackHand
{
	private BlackjackCardNode handNode;
	
	public BlackjackHand()
	{
		this.handNode = null;
	}
	
	public BlackjackHand( BlackjackCardNode handNode )
	{
		this.handNode = handNode;		
	}
	
	private boolean isEmpty()
	{
		if( handNode != null )
			return false;
		else
			return true;
	}
	
	public int getHandTotal()
	{
		int sum = 0;
		BlackjackCardNode temp;
		
		temp = handNode;
		
		while( temp != null )
		{
			sum += temp.getValue();
			temp = temp.getNextNode();
		}
		
		return sum;
	}
	
	public BlackjackCardNode getHand()
	{
		return handNode;
	}
	
	public void addCard( BlackjackCardNode card )
	{
		if( !isEmpty() )
		{
			BlackjackCardNode temp;
		
			temp = BlackjackDeck.findLastNode( this.handNode );
			
			
			temp.setNextNode( card );
			card.setPrevNode( temp );
			card.setNextNode( null );
		}
		else
		{
			this.handNode = card;
			card.setNextNode( null );
			card.setPrevNode( null );
		}
		
	}
}