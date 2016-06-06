package Blackjack;

public class Card
{
	private int cardSuit;
	private int cardRank;
	private String[] cardSuits = {"Clubs", "Spades", "Hearts", "Diamonds"};
	private String[] cardRanks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "K", "Q", "K"};

	Card(int suit, int rank)
	{
		cardSuit = suit;
		cardRank = rank;
	}

	public int getSuit()
	{
		return (cardSuit);
	}
	
	public int getRank()
	{
		return (cardRank);
	}
	
	public String toString()
	{		
		String cardString = null;
		
		switch (cardSuit)
		{
		case 1: cardString = cardSuits[0]; break;
		case 2: cardString = cardSuits[1]; break;
		case 3: cardString = cardSuits[2]; break;
		case 4: cardString = cardSuits[3]; break;
		}
		
		switch (cardRank)
		{
		case 1: cardString += cardRanks[0]; break;
		case 2: cardString += cardRanks[1]; break;
		case 3: cardString += cardRanks[2]; break;
		case 4: cardString += cardRanks[3]; break;
		case 5: cardString += cardRanks[4]; break;
		case 6: cardString += cardRanks[5]; break;
		case 7: cardString += cardRanks[6]; break;
		case 8: cardString += cardRanks[7]; break;
		case 9: cardString += cardRanks[8]; break;
		case 10: cardString += cardRanks[9]; break;
		case 11: cardString += cardRanks[10]; break;
		case 12: cardString += cardRanks[11]; break;
		case 13: cardString += cardRanks[12]; break;
		}
		
		return cardString;
	}
	
	public int getValue()
	{
		return cardRank;
	}
}
