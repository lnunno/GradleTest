package cards

/**
 * Created by Lucas on 4/6/2015.
 */
class Deck {

    List<Card> cardList = [];

    static def freshDeck = []
    static {
        for (suit in (Suit.Club..Suit.Spade)) {
            for (rank in (Rank.Two..Rank.Ace)) {
                Card c = new Card(rank, suit)
                freshDeck.add(c)
            }
        }
        freshDeck = freshDeck.asImmutable() // Make immutable
    }

    Deck() {
        this.cardList = freshDeck.collect()
    }

    static Deck shuffledDeck(){
        Deck deck = new Deck()
        deck.shuffle()
        return deck
    }

    def shuffle() {
        Collections.shuffle(cardList)
    }

    def sort() {
        Collections.sort(cardList)
    }

    def draw() {
        return draw(1).first();
    }

    def draw(int amount) {
        def drawnCards = cardList.take(amount)
        cardList = cardList.drop(amount)
        return drawnCards;
    }

    @Override
    String toString() {
        StringBuilder builder = new StringBuilder()
        builder.append("(Deck)\n")
        cardList.each {
            builder.append("\t" + it.toString() + "\n")
        }
        builder.append("(/Deck)\n")
        return builder.toString()
    }
}
