public class BlackjackCardNode extends BlackjackCard
{
	private BlackjackCardNode next;
	private BlackjackCardNode prev;
	
	public BlackjackCardNode(String suit, String type, int value)
	{
		super( suit, type, value );
		this.next = null;
		this.prev = null;
	}
	
	public BlackjackCardNode(String suit, String type, int value, BlackjackCardNode next, BlackjackCardNode prev )
	{
		super( suit, type, value );
		this.next = next;
		this.prev = prev;
	}
	
	public void setNextNode( BlackjackCardNode next )
	{
		this.next = next;
	}
	
	public void setPrevNode( BlackjackCardNode prev )
	{
		this.prev = prev;
		 
	}

	public BlackjackCardNode getPrevNode()
	{
		return this.prev;
	}
	
	public BlackjackCardNode getNextNode()
	{
		return this.next;
	}
}