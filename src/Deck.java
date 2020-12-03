import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Cards> cards;

    public Deck() {                                     // creates deck of cards
        this.cards = new ArrayList<Cards>();

    }

    public void fullDeck() {                            // adds 52 cards to the deck
        //Loop Through Suits
        // Generates cards
        for (Suit cardSuit : Suit.values()) {           // for each loop; 4*13 = 52
            //This will Loop through Values
            for (Value cardValue : Value.values()) {
                //Adds new card to the deck (13*4)
                this.cards.add(new Cards(cardSuit, cardValue));
            }
        }
    }

    //Shuffle deck of cards
    public void shuffle() {
        ArrayList<Cards> tempDeck = new ArrayList<Cards>();
        //Randomly will pick a from the old deck and copy values to the new deck
        Random random = new Random();
        int randomCard = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            //Generates random number / rand.nextInt((max - min) + 1) + min;
            randomCard = random.nextInt((this.cards.size() - 1) + 1);
            //Throws a random card into new deck
            tempDeck.add(this.cards.get(randomCard));
            //Removes picked from old deck
            this.cards.remove(randomCard);
        }
        //Sets this.deck to our newly shuffled deck
        this.cards = tempDeck;
    }

    public String toString() {
        String cardListOutput = "";
        int i = 0;
        for (Cards aCard : this.cards) {
            cardListOutput += "\n " + aCard.toString();
            //Makes a string with all card values in it adding
        }
        return cardListOutput;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Cards getCard(int i) {
        return this.cards.get(i);
    }



    public void addCard(Cards addCard){
        this.cards.add(addCard);
    }


    //Draw card from the deck
    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();

        // Put cards into moveTo deck
        for(int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

    public int cardsValue() {
        //Returns total value of cards in deck
        int totalValue = 0;
        int Aces = 0;
        //ace card tracker

        for(Cards aCard : this.cards) {
            // Checks values of the cards
            switch(aCard.getValue()){
                case Two: totalValue += 2; break;
                case Three: totalValue += 3; break;
                case Four: totalValue += 4; break;
                case Five: totalValue += 5; break;
                case Six: totalValue += 6; break;
                case Seven: totalValue += 7; break;
                case Eight: totalValue += 8; break;
                case Nine: totalValue += 9; break;
                case Ten: totalValue += 10; break;
                case Jack: totalValue += 10; break;
                case Queen: totalValue += 10; break;
                case King: totalValue += 10; break;
                case Ace: Aces += 1; break;
                }
            }

        for(int i = 0; i < Aces; i++) {
            //for every aces player has this check total value
            if(totalValue > 10) {
                totalValue += 1;
                //if ace > 10 value gets valued to 1 (> 1 ace)
            } else {
                totalValue += 11;
                // for first ace drawn
            }
        }
        return totalValue;
    }

    public int deckSize() {
        return this.cards.size();

    }
}
