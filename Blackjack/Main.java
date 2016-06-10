package Blackjack;
import javax.swing.JFrame;

public class Main
{
	private final static int STARTINGMONEY = 1000; //Starting money for the user

	public static void main(String[] args)
	{
		JFrame frame = new JFrame(); //Create the frame
		frame.setVisible(true);
		frame.setTitle("Sam's House of Blackjack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 767, 546);
		
		BlackjackGUI game = new BlackjackGUI(frame);
		game.updateMoney(STARTINGMONEY); //Display starting money
	}
}
