public class Cards {

    private Suit suit;
    private Value value;

    public Cards(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {
        //allows us to print the value and suit
        return this.suit.toString() + " " + this.value.toString();
        //returns suit and value
    }

    public Value getValue() {
        return this.value;
    }
}