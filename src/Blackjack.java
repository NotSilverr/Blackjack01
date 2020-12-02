import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        // welcome message
        System.out.println("Welcome to the game!");

        Deck playingDeck = new Deck();
        //Create playing deck
        playingDeck.fullDeck();
        playingDeck.shuffle();9
        //Creates deck for player & dealer
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 250.00;

        Scanner userInput = new Scanner(System.in);
        //Takes user input

        //Turn order for game
        while(playerMoney > 0) {
            //Keep playing!
            //Takes next players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            // Asks how much money you have and asks for bet amount
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney) {
                System.out.println("You don't have that much money, poors out");
                break;
            }
            //Starts dealing
            //Player obtains two cards

            //Dealer obtains two cards
            playerDeck.draw(playerDeck);
            playerDeck.draw(playerDeck);

            while(true) {
                System.out.println("Your hand:");
                System.out.print(playerDeck.toString());
            }

        }

        System.out.println("Game over, you lost all your money! Nice!");
        }

 }
