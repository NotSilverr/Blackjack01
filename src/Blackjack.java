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
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 250.00;

        Scanner userInput = new Scanner(System.in);
        //Takes user input

        //Turn order for game
        boolean endRound = false;
        while (playerMoney > 0) {
            //Keep playing!
            //Takes next players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            // Asks how much money you have and asks for bet amount
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You don't have that much money, poors out");
                break;
            }

            endRound = false;

            //Starts dealing
            //Player obtains two cards

            //Dealer obtains two cards
            playerDeck.draw(playerDeck);
            playerDeck.draw(playerDeck);

            while (true) {
                System.out.println("Your hand:");
                System.out.print(playerDeck.toString());
                System.out.println("Your hand is: " + playerDeck.cardsValue());

                //Dealer hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + "and [Hidden]");

                //Hit or stay player input
                System.out.println("Would you like to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                //Hits
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize() - 1));

                    // Bust if > 21
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust! You got: " + playerDeck.cardsValue());
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
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            //Sees dealers hand
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false));
            System.out.println("Dealer wins!");
            playerMoney -= playerBet;
            endRound = true;
        }
        //Dealer draws at 16 and stands at 17
        while ((dealerDeck.cardsValue() < 17) && endRound == false) {
            dealerDeck.draw(playerDeck);
            System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
        }


        System.out.println("Game over, you lost all your money! Nice!");
    }

 }
