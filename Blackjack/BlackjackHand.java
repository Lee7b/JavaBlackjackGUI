package Blackjack;

public class BlackjackHand extends Hand
{

	public int getBlackjackValue()
	{
	     // Returns the value of this hand for the
	     // game of Blackjack.
	    int val = 0;  // The value computed for the hand.
	    boolean ace = false;
	    int cards = getNumberOfCards();    // Number of cards in the hand.
	    
	    for ( int i = 0;  i < cards;  i++ )
	    {
	        Card card; 
	        int cardVal;
	        card = getCardAt(i);
	        cardVal = card.getValue();
	        
	        if (cardVal > 10)
	        {
	            cardVal = 10;   // For a Jack, Queen, or King.
	        }
	        
	        if (cardVal == 1) //If card is an ace, make the value 11
	        {
	        	cardVal = 11;
	        	ace = true; 
	        }
	        
	    	val += cardVal;
	 
	     }
	    
	    if (ace == true && val > 21) //If hand has an ace, and total goes over 20, make ace worth 1
	    {
		    val -= 10;
		}
	    
	    return val;
	
	}
}