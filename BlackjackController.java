import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class BlackjackController implements ActionListener
{
	BlackjackModel model;
	BlackjackView view;
	
	private boolean blackjack;
	private boolean busted;
	private boolean winner;
	private int turn;
	
	public BlackjackController( BlackjackModel model, BlackjackView view)
	{
		this.model = model;
		this.view = view;
		
		blackjack = false;
		busted = false;
		winner = false;
		
		turn = 0;
	}
	
	public void actionPerformed( ActionEvent event )
	{	
		BlackjackButton b = (BlackjackButton)event.getSource();
		if( "Deal".equals(b.getText()))
		{
			model.initializeDecks(1);
			for(int i = 0; i < 2; i++)
			{
				for(int j = 0; j < model.getNumOfPlayers(); j++)
				{
					turn = model.getNextTurn();
					view.addCards( turn, model.initialDeal(turn) );
				}
			}
			checkBlackJack();
			
		}
		else if( "hit".equals(b.getText()))
		{
			view.addCards(turn, model.initialDeal(turn));
			checkBusted();
		}
		else if( "stay".equals(b.getText()))
		{
			turn = model.getNextTurn();
			checkDealerTurn();
		}
		else if( "Play Again".equals(b.getText()))
		{
			model.initialize(1);
			turn = 0;
			view.clearView();
		}
		else if( "Quit".equals(b.getText()))
		{
			System.exit(-1);
		}
	}
	
	private void checkDealerTurn()
	{
		if( model.isDealerTurn( turn ) )
			checkWinner();
	}
	
	private void checkWinner()
	{
		BlackjackPlayer player;
		BlackjackCard[] dealerCards;
		view.dealerView();
		dealerCards = model.lastPlays();
		
		if( dealerCards != null)
			for(int i = 0; i < dealerCards.length; i++)
				view.addCards( turn, dealerCards[i]);
		
		String s;
		
		s = model.getWinner();
		JOptionPane.showMessageDialog(null, s);
		view.continueView();
		
	}
	
	private void checkBusted()
	{
		BlackjackPlayer player;
		player = model.isBusted();
	
		if( player != null )
		{
			view.dealerView();
			JOptionPane.showMessageDialog(null, player.getName() + " is busted!");
			view.continueView();
		}
			
	}
	
	private void checkBlackJack()
	{
		BlackjackPlayer player;
		
		player = null;
		
		player = model.blackJack();
		if( player != null )
		{
			view.dealerView();
			JOptionPane.showMessageDialog(null, player.getName() + " wins!");
			view.continueView();
		}
		else{
		turn = model.getNextTurn();
		view.hitView();
		}
	}
	
}