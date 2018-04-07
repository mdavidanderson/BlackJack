import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class BlackjackView extends JFrame
{
	BlackjackModel model;
	JLabel [] dealerHand;
	JLabel [] playerHand;
	JLabel label;
	
	BlackjackButton deal;
	BlackjackButton dealagain;
	BlackjackButton hit;
	BlackjackButton stay;
	BlackjackButton doubleDown;
	BlackjackButton split;
	BlackjackButton quit;
	
	JPanel [] cardPanels;
	JPanel buttonPanel;
	
	ImageIcon test = new ImageIcon("images/1.png");
	
	public BlackjackView(BlackjackModel model)
	{
		this.model = model;
		setSize(500, 500);
		setLayout(new BorderLayout());
		
		JPanel playersPanels = new JPanel(new GridLayout(model.getNumOfPlayers(),1));
		cardPanels = new JPanel[model.getNumOfPlayers()];
		
		buttonPanel = new JPanel();	
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		label = new JLabel();
				
		hit = new BlackjackButton("hit");
		stay = new BlackjackButton("stay");
		doubleDown = new BlackjackButton("double down");
		split = new BlackjackButton("split");
		deal = new BlackjackButton("Deal");
		dealagain = new BlackjackButton("Play Again");
		quit = new BlackjackButton("Quit");
		for(int i = 0; i < cardPanels.length; i++)
			cardPanels[i] = new JPanel(new FlowLayout());
		
		playersPanels.add(cardPanels[cardPanels.length - 1]);
		for( int i = 0; i < cardPanels.length - 1; i++ )
		{
			playersPanels.add(cardPanels[i]);	
		}
		
		
		
		//buttonPanel.add(hit);
		//buttonPanel.add(stay);
		//buttonPanel.add(doubleDown);
		//buttonPanel.add(split);

		buttonPanel.add(deal);
		
		add(playersPanels, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.EAST);
	}

	public void changeText(String s)
	{
		label.setText(s);
	}
	
	public void hitView()
	{
		buttonPanel.removeAll();
		buttonPanel.add(hit);
		buttonPanel.add(stay);
	}
	
	public void clearView()
	{
		buttonPanel.removeAll();
		for(int i=0; i < cardPanels.length; i++)
			cardPanels[i].removeAll();
		
		buttonPanel.add(deal);
		buttonPanel.revalidate();
	}
	
	public void continueView()
	{
		System.out.println("Continue View");
		buttonPanel.add(dealagain);
		buttonPanel.add(quit);
		buttonPanel.revalidate();
	}
	
	public void dealerView()
	{
		buttonPanel.removeAll();
	}
	
	public void addListener( BlackjackController controller )
	{
		deal.addActionListener( controller );
		hit.addActionListener( controller );
		stay.addActionListener( controller );
		dealagain.addActionListener( controller );
		quit.addActionListener( controller );
		//doubleDown.addActionListener( controller );
		//split.addActionListener( controller );
	}
	
	public void addCards(int player, BlackjackCard card)
	{
		JLabel l = new JLabel();
		cardPanels[player].add(l);
		l.setIcon(new ImageIcon("images/" + card.getSuit() + card.getType() + ".png"));
	}
	
}