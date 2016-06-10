package Blackjack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.border.LineBorder;

public class BlackjackGUI
{
	private static final long serialVersionUID = 1L;
	private JPanel userCardPanel;
	private JPanel dealerCardPanel;
	private JTextField enterBetJTF;
	private JTextField moneyLeftJTF;
	private JTextArea infoText;
	private double userMoney = 1000;
	private double userBet;
	private boolean gameStarted;
	private boolean userWins;
	private boolean dealerWins;
	private boolean hasBusted;
	private BlackjackHand userHand = new BlackjackHand();
	private BlackjackHand dealerHand = new BlackjackHand();
	private Deck deck = new Deck();

	public BlackjackGUI(JFrame frame) {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);		
		contentPane.setLayout(null);
		
		moneyLeftJTF = new JTextField();
		moneyLeftJTF.setEditable(false);
		moneyLeftJTF.setBounds(393, 14, 86, 20);
		contentPane.add(moneyLeftJTF);
		moneyLeftJTF.setColumns(10);
		
		enterBetJTF = new JTextField();
		enterBetJTF.setBounds(75, 14, 86, 20);
		contentPane.add(enterBetJTF);
		enterBetJTF.setColumns(10);
	
		infoText = new JTextArea();
		infoText.setEditable(false);
		infoText.setText("Welcome! Please place your bet.");
		infoText.setBackground(new Color(211, 211, 211));
		infoText.setBounds(155, 451, 451, 66);
		contentPane.add(infoText);
		
		JLabel lblEnterBet = new JLabel("Enter Bet:");
		lblEnterBet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterBet.setForeground(Color.BLACK);
		lblEnterBet.setBounds(10, 10, 82, 26);
		contentPane.add(lblEnterBet);
		
		JLabel lblCurrentMoney = new JLabel("Current Money: ");
		lblCurrentMoney.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentMoney.setBounds(291, 11, 109, 26);
		contentPane.add(lblCurrentMoney);
		
		JPanel userCardPanelBorder = new JPanel();
		userCardPanelBorder.setOpaque(false);
		userCardPanelBorder.setBackground(new Color(0, 128, 0));
		userCardPanelBorder.setBorder(new TitledBorder(null, "User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userCardPanelBorder.setBounds(61, 77, 635, 143);
		contentPane.add(userCardPanelBorder);
		userCardPanelBorder.setLayout(null);
		
		userCardPanel = new JPanel();
		userCardPanel.setOpaque(false);
		userCardPanel.setBackground(new Color(0, 128, 0));
		userCardPanel.setBounds(6, 16, 619, 120);
		userCardPanelBorder.add(userCardPanel);
		userCardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel dealerCardPanelBorder = new JPanel();
		dealerCardPanelBorder.setOpaque(false);
		dealerCardPanelBorder.setBackground(new Color(0, 128, 0));
		dealerCardPanelBorder.setBorder(new TitledBorder(null, "Dealer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dealerCardPanelBorder.setBounds(61, 223, 635, 143);
		contentPane.add(dealerCardPanelBorder);
		dealerCardPanelBorder.setLayout(null);
		
		dealerCardPanel = new JPanel();
		dealerCardPanel.setOpaque(false);
		dealerCardPanel.setBackground(new Color(0, 128, 0));
		dealerCardPanel.setBounds(6, 16, 619, 120);
		dealerCardPanelBorder.add(dealerCardPanel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameStarted == true) //If the user has already placed a bet for this hand, don't start another one
				{
					userBet = getBet();
					infoText.setText("You're already playing a hand.");
					return;
				}
				if (isNumeric(enterBetJTF.getText()) && getBet() >= 10) //If user has not bet yet, start the hand if minimum bet is given
				{
					gameStarted = true;
					playGame();
				}
				else
				{
					infoText.setText("The minimum bet is $10.");
				}
			}
		});
		btnSubmit.setBounds(171, 13, 89, 23);
		contentPane.add(btnSubmit);

		JButton btnHit = new JButton("");
		btnHit.setBorder(new LineBorder(new Color(0, 0, 0), 1
				));
		btnHit.setContentAreaFilled(false);
		btnHit.setBorderPainted(true);
		btnHit.setFocusPainted(false);
		btnHit.setIcon(new ImageIcon("cards/hit.png"));
		btnHit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHit.setToolTipText("Add a card to your hand");
		btnHit.setForeground(Color.BLACK);
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameStarted == true)
				{
					doHit(userHand);
				}
				else
				{
					infoText.setText("Please place a bet before hitting.");
				}
			}
		});
		btnHit.setBounds(61, 394, 137, 32);
		contentPane.add(btnHit);
		
		JButton btnStand = new JButton("");
		btnStand.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnStand.setContentAreaFilled(false);
		btnStand.setFocusPainted(false);
		btnStand.setIcon(new ImageIcon("cards/stand.png"));
		btnStand.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStand.setToolTipText("End turn");
		btnStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameStarted == true)
				{
					dealerTurn(); //Process dealer turn
				}
				else
				{
					infoText.setText("Please place a bet before standing.");
				}
			}
		});
		btnStand.setBounds(225, 394, 137, 32);
		contentPane.add(btnStand);
		
		JButton btnSurrender = new JButton("");
		btnSurrender.setContentAreaFilled(false);
		btnSurrender.setFocusPainted(false);
		btnSurrender.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnSurrender.setIcon(new ImageIcon("cards/surrender.png"));
		btnSurrender.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSurrender.setToolTipText("If you surrender, you lose half of your bet");
		btnSurrender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameStarted == true)
				{
					doSurrender();
				}
				else
				{
					infoText.setText("Please place a bet before surrendering.");
				}
			}
		});
		btnSurrender.setBounds(559, 394, 137, 32);
		contentPane.add(btnSurrender);
		
		JButton btnDouble = new JButton("");
		btnDouble.setContentAreaFilled(false);
		btnDouble.setFocusPainted(false);
		btnDouble.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDouble.setIcon(new ImageIcon("cards/double.png"));
		btnDouble.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDouble.setToolTipText("Double your bet, and get one, and only one more card");
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameStarted == true)
				{
					doDouble();
				}
				else
				{
					infoText.setText("Please place a bet before doubling");
				}
			}
		});
		btnDouble.setBounds(393, 394, 137, 32);
		contentPane.add(btnDouble);
		
		JLabel lblBackgroundImage = new JLabel("");
		lblBackgroundImage.setIcon(new ImageIcon("cards/table.jpg"));
		lblBackgroundImage.setBounds(0, 0, 761, 517);
		contentPane.add(lblBackgroundImage);
		
		frame.repaint(); //Initial paint
	}
	
	public void playGame()
	{
		userCardPanel.removeAll(); //Make sure panels are cleared when starting a new hand
		dealerCardPanel.removeAll();
		hasBusted = false; //Reset the has busted boolean to false
		
		//Give user and dealer two cards each
		doHit(userHand);
		doHit(userHand);
		doHit(dealerHand);
		doHit(dealerHand);
		
		infoText.setText("Value of your hand is: " + userHand.getBlackjackValue()); //Tell user the current value of his hand
	}
	
	public void updateMoney(double money) //Updates the money displayed on screen
	{
		moneyLeftJTF.setText("$" + Double.toString(money));
	}
	
	public double getBet() //Returns the user's bet
	{
		if (!enterBetJTF.getText().isEmpty()) //If the text box for user bet is not empty and contains numeric digits, store the bet in userBet variable
		{
			userBet = Double.parseDouble(enterBetJTF.getText());
		}

		return userBet;
	}
	
	public boolean isNumeric(String s)
	{
		if (s.matches("\\d+")) //If s only contains numeric values return true
		{
			return true;
		}
		return false; //Else, return false
	}
	
	public boolean isBusted(BlackjackHand h)
	{
		if (h.getBlackjackValue() > 21) //If the hand we are checking is busted return true
		{
			return true;
		}
		return false; //Else return false
	}

	public void dealerTurn() //Do the dealer's turn and compare hands
	{
		while (dealerHand.getBlackjackValue() < 17) //Dealer hits if he has less than 17 total
		{
			doHit(dealerHand);
		}
		
		doPayouts(); //Compare the user's hand and the dealer's hand and do payouts
	}
	
	public void doHit(Hand h) //Give a card if hitting
	{
		h.addCard(deck.dealCard()); //Add a card to the hand
		int i = userHand.getNumberOfCards() - 1; //Set i to be the last card in the user's hand
		int j = dealerHand.getNumberOfCards() - 1; //Set j to be the last card in the dealer's hand
		
		if (h.equals(userHand)) //If the hand we are hitting is the user's hand, draw the new card
		{
			drawCard(0, i);
		}
		if (h.equals(dealerHand)) //If the hand we are hitting is the dealer's hand, draw the new card
		{
			drawCard(1, j);
		}
		
		infoText.setText("Value of your hand is: " + userHand.getBlackjackValue()); //Update the total of the current hand in the console

		if(isBusted(userHand)) //If user busts, he loses
		{
			hasBusted = true;
			doPayouts();
		}
	}
	
	public void doSurrender()
	{
		infoText.setText("You have opted to surrender this hand.");
		userMoney -= (getBet() / 2); //Subtract half of the current bet from the user's money
		updateMoney(userMoney); //Update the money displayed
		newGame(); //Start a new game
	}
	
	public void doDouble() //Do this if user wants to double down
	{
		userBet += userBet; //Double the user's bet
		doHit(userHand); //Give the user one, and only more card
		if (hasBusted == false)
		{
			dealerTurn(); //If user did not bust, continue to the dealer's turn
		}
	}

	public void drawCard(int ID, int position) //Draws a card on screen. The argument ID is used to differentiate between user and dealer
	{
		if (ID == 0) //If ID == 0, we are drawing a card in the userCardPanel
		{
			userCardPanel.add(new JLabel(userHand.getImage(userHand, position)));
			userCardPanel.repaint();
		}
		else if (ID == 1) //If ID == 1, we are drawing a card in the dealerCardPanel
		{
			if (position == 0)
			{
				dealerCardPanel.add(new JLabel(new ImageIcon("cards/Back.gif"))); //Hide the dealer's first card
				dealerCardPanel.repaint();//Update the JFrame
			}
			else
			{
				dealerCardPanel.add(new JLabel(dealerHand.getImage(dealerHand, position)));
				dealerCardPanel.repaint();//Update the JFrame
			}
		}
		
	}

	public void doPayouts()
	{
		if(isBusted(userHand)) //If dealer busted, set userWins to true
		{
			dealerWins = true;
			infoText.setText("You have busted, the dealer wins.");
		}
		else if(isBusted(dealerHand)) //If dealer busted, set userWins to true
		{
			userWins = true;
			infoText.setText("Dealer has busted, you win.");
		}
		else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) //If dealer has a higher hand than user, dealer wins
		{
			dealerWins = true;
			infoText.setText("Sorry, you lost.\n The dealer's hand had a total value of: " + dealerHand.getBlackjackValue());
		}
		else if (userHand.getBlackjackValue() > dealerHand.getBlackjackValue()) //If user has a higher hand than dealer, user wins
		{
			userWins = true;
			infoText.setText("You won, congratulations.");
		}
		else if (userHand.getBlackjackValue() == dealerHand.getBlackjackValue()) //If user and dealer tie, dealer wins
		{
			dealerWins = true;
			infoText.setText("Sorry, you had a tie, therefore you lost");
		}
		if (userWins) //If user wins the hand
		{
			userMoney += userBet;
			updateMoney(userMoney);
			newGame();
		}
		if (dealerWins) //If dealer wins the hand
		{
			userMoney -= userBet;
			updateMoney(userMoney);
			newGame();
		}
	}
	 
	public void newGame()
	{
		infoText.setText(infoText.getText() + "\nThe dealers hidden card was " + dealerHand.getCardAt(0).toString());
		userWins = false;
		dealerWins = false;
		gameStarted = false;
		userHand.emptyHand();
		dealerHand.emptyHand();
	}
}