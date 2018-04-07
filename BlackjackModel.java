import java.util.Random;

public class BlackjackModel
{
	private BlackjackPlayer[] players;
	private BlackjackPlayer player;
	private BlackjackPlayer dealer;
	private BlackjackDeck [] unstackedDecks;
	private BlackjackDeck stackDeck;
	private int turn;
	
	public BlackjackModel()
	{
		this.players = 	new BlackjackPlayer[] {new BlackjackPlayer("Player"), 
						new BlackjackPlayer("Dealer")};
		this.dealer = players[players.length - 1 ];
		this.player = players[0];
		unstackedDecks = null;
		stackDeck = null;
		turn = players.length-1;
	}
	
	public boolean isDealerTurn( int turn )
	{
		if( turn == players.length-1 )
			return true;
		else
			return false;
	}
	
	public BlackjackCard[] lastPlays()
	{
		BlackjackCard[] cards;
		BlackjackCardNode card;
		BlackjackCard[] temp;
		
		cards = null;
		card = null;
		temp = null;
		
		while( dealer.getHandTotal() < 17 )
		{
			card = stackDeck.draw();
			dealer.dealCardToHand(card);
			
			if(cards != null)
			{
				temp = cards;
				cards = new BlackjackCard[cards.length + 1];
				for(int i = 0; i < temp.length; i++)
					cards[i] = temp[i];
				cards[cards.length - 1] = card;
			}
			else
			{
				cards = new BlackjackCard[1];
				cards[0] = card;
			}
		}
		
		return cards;
	}
	
	public String getWinner()
	{
		if( dealer.getHandTotal() >= player.getHandTotal() && dealer.getHandTotal() <= 21 )
		{
			return "Dealer Wins!";
		}
		else if( dealer.getHandTotal() == player.getHandTotal() )
		{
			return "It's a push!";
		}
		else
		{
			return "Player wins!";
		}
	}
	
	public int getNumOfPlayers()
	{
		return players.length;
	}
	
	public void initializeDecks( int NumOfDecks)
	{
		loadDecks( NumOfDecks );
	}
	
	public void initialize(int NumOfDecks)
	{
		this.players = 	new BlackjackPlayer[] {new BlackjackPlayer("Player"), 
				new BlackjackPlayer("Dealer")};
		this.dealer = players[players.length - 1 ];
		this.player = players[0];
		unstackedDecks = null;
		stackDeck = null;
		turn = players.length-1;
		
	}
	
	public BlackjackCard initialDeal(int i)
	{
		BlackjackCardNode card;
		
		card = stackDeck.draw();
		players[i].dealCardToHand( card );
		return card;
	}
	
	/*TEMP*/ public void printScores()
	{
		for(int i = 0; i < players.length; i++)
		{
			System.out.printf("%s's hand total is: %d\n", 
							players[i].getName(), players[i].getHandTotal());
		}
	}
	
	private void loadDecks( int NumOfDecks )
	{
		BlackjackDeck temp;
		unstackedDecks = new BlackjackDeck [NumOfDecks];
		fillArrayOfDecks( unstackedDecks);
		temp = new BlackjackDeck( BlackjackDeck.compileDecks( unstackedDecks ) );
		stackDeck = shuffleDeck( temp );
	}
	
	public String [][] getHandsAsImageFiles()
	{
		String[][] fileNames = new String[players.length][2];
		for(int i = 0; i < fileNames.length; i++)
		{
			BlackjackCardNode card = players[i].getHand();
			
			for(int j = 0; j < 2; j++)
			{
				fileNames[i][j] = "images/" + card.getSuit() + card.getType() + ".png";
				card = card.getNextNode();
			}
		}
		
		return fileNames;
	}
	
	private BlackjackDeck shuffleDeck( BlackjackDeck temp )
	{
		BlackjackDeck newDeck;
		BlackjackCardNode card;
		BlackjackCardNode swapNext;
		BlackjackCardNode swapPrev;
		
		newDeck = new BlackjackDeck( null );
		
		int element;
		int count = temp.cardsInDeck();
		
		Random randomgen = new Random();
		
		for( int i = 0; i < count; i++ )
		{
			element = randomgen.nextInt(count - i);
			card = temp.getFirstNode();
			
			for(int j = 0; j < element; j++)
			{
				card = card.getNextNode();
			}
			
			swapNext = card.getNextNode();
			swapPrev = card.getPrevNode();
			
			if( swapPrev != null && swapNext != null )
			{
				swapPrev.setNextNode( swapNext );
				swapNext.setPrevNode( swapPrev );
			}
			else if( swapPrev == null && swapNext != null )
			{
				swapNext.setPrevNode( swapPrev );
				temp.setFirstNode( swapNext );
			}
			else if ( swapPrev != null && swapNext == null )
			{
				swapPrev.setNextNode( swapNext );
			}
			
			newDeck.addCard( card );
			
		}
		
		return newDeck;
	}
	
	public int getNextTurn()
	{
		if( turn == players.length - 1)
			turn = 0;
		else
			turn++;
		
		return turn;
	}
	
	public BlackjackPlayer isBusted()
	{
		for(int i = 0; i < players.length; i++)
		{
			if( players[i].getHandTotal() > 21 )
				return players[i];
		}
		
		return null;
	}
	
	public BlackjackPlayer blackJack()
	{
		for(int i = 0; i < players.length; i++)
		{
			if( isBlackjack(players[i].getHandTotal()))
			{
				return players[i];
			}
		}
		
		return null;
		
		
	}
	
	private boolean isBlackjack(int i)
	{
		if( i == 21)
			return true;
		else
			return false;
	}
	
	private boolean isBusted(int i)
	{
		if( i > 21)
			return true;
		else
			return false;
	}
	
	private void fillArrayOfDecks( BlackjackDeck [] decks )
	{
		for( int i = 0; i < decks.length; i++ )
		{
			decks[i] = new BlackjackDeck();
		}
	}
}