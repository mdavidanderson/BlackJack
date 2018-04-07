public class BlackjackDeck
{
	private BlackjackCardNode beginDeck;
		
	private String [] suits = new String [] {"Spades", "Clubs", "Hearts", "Diamonds"};
	private String [] types = new String [] {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight",

								 "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	private int [] values = new int [] { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
	
	public BlackjackDeck()
	{
		buildDeck();
	}
	
	public BlackjackDeck( BlackjackCardNode beginDeck )
	{
		this.beginDeck = beginDeck;
	}
	
	public void setFirstNode( BlackjackCardNode beginDeck)
	{
		this.beginDeck = beginDeck;	
	}
	
	public void buildDeck()
	{
		BlackjackCardNode temp = null;
		for( int i = 0; i < suits.length; i++ )
		{
			for( int j = 0; j < types.length; j++)
			{
				if (beginDeck != null)
				{
					temp.setNextNode( new BlackjackCardNode(suits[i], types[j], values[j], null, temp ) );
					temp = temp.getNextNode();
				}
				else
				{
					beginDeck = new BlackjackCardNode(suits[i], types[j], values[j]);
					temp = beginDeck;
				}
			}
		}
	}
	
	public static BlackjackCardNode compileDecks( BlackjackDeck [] decks )
	{
		BlackjackCardNode deck = null;
		BlackjackCardNode temp = null;
		
		for( int i = 0; i < decks.length; i++)
		{
			if( deck == null && decks[i].getFirstNode() != null )
			{
				deck = decks[i].getFirstNode();
				temp = BlackjackDeck.findLastNode( deck );
			}
			else if( decks[i].getFirstNode() != null )
			{
				temp.setNextNode(decks[i].getFirstNode());
				temp = BlackjackDeck.findLastNode( temp );
			}
		}
		
		return deck;
	}
	
	public BlackjackCardNode getFirstNode()
	{
		return beginDeck;
	}
	
	public static BlackjackCardNode findLastNode( BlackjackCardNode start )
	{
		BlackjackCardNode temp = start;
		
		while( temp.getNextNode() != null )
		{
			temp = temp.getNextNode();
		}
		
		return temp;
	}

	public BlackjackCardNode draw()
	{
		BlackjackCardNode card;
		
		card = null;
		
		if(beginDeck != null)
		{
			card = beginDeck;
			beginDeck = beginDeck.getNextNode();
			if(beginDeck != null)
				beginDeck.setPrevNode(null);
			card.setNextNode(null);
		}
		
		return card;
	}
	
	public void printDeck()
	{
		BlackjackCardNode temp;
		temp = beginDeck;
		
		while( temp != null )
		{
			System.out.printf( "Suit: %s || Type: %s || Value: %d\n", 
								temp.getSuit(), temp.getType(), temp.getValue() );
			temp = temp.getNextNode();
		}	
	}
	
	public int cardsInDeck()
	{
		BlackjackCardNode temp = beginDeck;
		int count = 0;
		
		while( temp != null )
		{
			temp = temp.getNextNode();
			count++;
		}
		
		return count;
	}
	
	public boolean isDeckEmpty()
	{
		if( this.beginDeck == null )
			return true;
		else
			return false;
	}
	
	public void addCard( BlackjackCardNode card )
	{
		if( !isDeckEmpty() )
		{
			BlackjackCardNode temp;
		
			temp = BlackjackDeck.findLastNode( this.beginDeck );
			
			
			temp.setNextNode( card );
			card.setPrevNode( temp );
			card.setNextNode( null );
		}
		else
		{
			this.beginDeck = card;
			card.setNextNode( null );
			card.setPrevNode( null );
		}
		
	}
}