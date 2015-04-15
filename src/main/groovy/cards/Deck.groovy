package cards

/**
 * Created by Lucas on 4/6/2015.
 */
class Deck {

    List<Card> cardList = [];

    Deck() {
        for (suit in (Suit.Club..Suit.Spade)) {
            for (rank in (Rank.One..Rank.Ace)) {
                Card c = new Card(rank, suit)
                cardList.add(c)
            }
        }
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
        return cardList.take(amount);
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
