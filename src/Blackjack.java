import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        // welcome message
        System.out.println("Welcome to the game!");

        Deck playingDeck = new Deck();
        //Create playing deck
        playingDeck.fullDeck();
        playingDeck.shuffle();
        //Creates deck for player & dealer
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 250.00;

        Scanner userInput = new Scanner(System.in);


        //Takes user input

        //Turn order for game
        boolean endRound;
        while (playerMoney > 0) {
            endRound = false;
            //Keep playing!
            //Takes next players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            while (!userInput.hasNextDouble())
            {
                System.out.println("Please enter a valid amount.");
                userInput = new Scanner(System.in);
            }
            // Asks how much money you have and asks for bet amount
            double playerBet = userInput.nextDouble();
            while (playerBet > playerMoney || playerBet < 0) {
                System.out.println("You can't bet that amount");
                playerBet = new Scanner(System.in).nextDouble();
            }

            //Starts dealing
            //Player obtains two cards
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            //Dealer obtains two cards

            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);


            while (true) {
                System.out.println("Your hand:");
                System.out.print(playerHand.toString());
                System.out.println(" Your hand is: " + playerHand.cardsValue());

                //Dealer hand
                System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [Something Hidden]");

                //Hit or stay player input
                System.out.println("Would you like to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                //Hits
                if (response == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You draw a:" + playerHand.getCard(playerHand.deckSize() - 1));

                    // Bust if > 21
                    if (playerHand.cardsValue() > 21) {
                        System.out.println("Bust! You got: " + playerHand.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;

                    }
                }
                //If player says Stand (2)
                if (response == 2) {
                    break;
                }
            }
            //Reveals cards
            if (endRound == false)
                System.out.println("Dealer Cards: " + dealerHand.toString());
            //Sees dealers hand to check if it's more than player
            if ((dealerHand.cardsValue() > playerHand.cardsValue() && endRound == false)) {
                System.out.println("Dealer beats you " + dealerHand.cardsValue() + " to " + playerHand.cardsValue());
                playerMoney -= playerBet;
                endRound = true;
            }

            //Dealer draws at 16 and stands at 17
            while ((dealerHand.cardsValue() < 17) && endRound == false) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
                //Indicates what the dealer just drew (added to arraylist)

            }
            //Displays total value for Dealer
            System.out.print("Dealer's hand is valued at: " + dealerHand.cardsValue());
            //See's if dealer busted
            if ((dealerHand.cardsValue() > 21) && endRound == false) {
                System.out.println(" Dealer busts! You win.");
                playerMoney += playerBet;
                endRound = true;
            }

            //Determine if push/time
            if ((dealerHand.cardsValue() == playerHand.cardsValue() && endRound == false)) {
                System.out.println(" Push");
                endRound = true;
            }

            if ((playerHand.cardsValue() > dealerHand.cardsValue()) && endRound == false) {
                System.out.println((" You win the hand!"));
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false) {
                //Dealer wins
                System.out.println(" You lose the hand!");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerHand = new Deck();
            dealerHand = new Deck();

            playingDeck.fullDeck();
            playingDeck.shuffle();
            //moves dealer cards and player decks back to the playing deck
            System.out.println(" End of hand!");
        }

        System.out.println("Game over, you lost all your money! Nice!");

        userInput.close();
        //Closes game afterwards
    }

}
