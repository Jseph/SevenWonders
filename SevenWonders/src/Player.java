import java.util.ArrayList;

//Represents a player of the game
//He will have a hand of cards, a set of played cards,
//a left and right player, a dummy status, a number of coins
//and military counters
public class Player 
{
	//This will be dealt to the player
	ArrayList<Card> hand;
	ArrayList<Card> playedCards;
	//Something about players wonder
	boolean isDummy;
	Player leftPlayer;
	Player rightPlayer;
	int numCoins;
	int militaryCounter;
}
