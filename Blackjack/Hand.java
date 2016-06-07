package Blackjack;

import java.util.Vector;
import javax.swing.ImageIcon;

public class Hand
{
	private Vector<Card> hand;
	
	public Hand()
	{
		hand = new Vector<Card>();
	}
	
	public void addCard(Card c)
	{
		hand.addElement(c);
	}
	
	public void removeCard(int position)
	{
		hand.removeElementAt(position);
	}
	
	public void emptyHand()
	{
		hand.removeAllElements();
	}
	
	public Card getCardAt(int position)
	{
		return (Card)hand.elementAt(position);
	}
	
	public int getNumberOfCards()
	{
		return hand.size();
	}
	
	public ImageIcon getImage(BlackjackHand h, int position)
	{
		ImageIcon cardImage = new ImageIcon("cards/" + h.getCardAt(position).toString() + ".gif");
		return cardImage;
	}


}
