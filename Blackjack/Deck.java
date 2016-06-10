package Blackjack;
import java.util.Random;

public class Deck
{
	Card[] deckOfCards;
	private int cardsUsed;
	private final int NUMBEROFSUITS = 4;
	private final int NUMBEROFRANKS = 13;
	
	public Deck()
	{
		deckOfCards = new Card[52];
		int i = 0;
		
		for (int suit = 1; suit <= NUMBEROFSUITS; suit++)
		{
			for (int rank = 1; rank <= NUMBEROFRANKS; rank++)
			{
				deckOfCards[i++] = new Card(suit, rank);
			}
		}
		
		shuffleDeck();
	}
	
	public void shuffleDeck()
	{
		Random randomGenerator = new Random();
		
		for (int i = 0; i < 50; i++)
		{
			Card tempCard = new Card(0,0); //Temporary card object
			int randomInt = randomGenerator.nextInt(52); //Random card 1
			int randomInt2 = randomGenerator.nextInt(52); //Random card 2
			
			tempCard = deckOfCards[randomInt];
			deckOfCards[randomInt] = deckOfCards[randomInt2];
			deckOfCards[randomInt2] = tempCard;
		}
		
		cardsUsed = 0;
	}
	
	public int cardsLeft()
	{
		return (52-cardsUsed);
	}
	
    public Card dealCard()
    {
    	if (cardsUsed == 52)
    		shuffleDeck();
    	
    	cardsUsed++;
    	return deckOfCards[cardsUsed - 1];
  }

}
