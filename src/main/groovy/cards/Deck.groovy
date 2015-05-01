package cards

import static cards.PokerGame.*

/**
 * Created by Lucas on 4/6/2015.
 */
class Deck {

    List<Card> cardList = []
    Random rand = new Random(SEED)

    static def freshDeck = []
    static {
        [Suit.values(), Rank.values()].eachCombination {
            Suit s, Rank r -> freshDeck.add(new Card(r, s))
        }
        freshDeck = freshDeck.asImmutable()
    }

    Deck() {
        this.cardList = freshDeck.collect()
    }

    static Deck shuffledDeck() {
        Deck deck = new Deck()
        deck.shuffle()
        return deck
    }

    def shuffle() {
        Collections.shuffle(cardList)
    }

    def sort() {
        cardList.sort()
    }

    def draw() {
        return draw(1).first();
    }

    def draw(int amount) {
        def drawnCards = cardList.take(amount)
        cardList = cardList.drop(amount)
        return drawnCards;
    }

    private def randomIndex(){
        return rand.nextInt(cardList.size())
    }

    /**
     * Draw a non-sequential card from the deck and replace it.
     * @return The card.
     */
    def drawRandomWReplacement(){
        def index = this.randomIndex()
        return cardList.get(index)
    }

    /**
     * Draw a non-sequential card from the deck don't replace.
     * @return The card.
     */
    def drawRandom(){
        return cardList.remove(this.randomIndex())
    }

    def drawRandom(int amount){
        def ls = []
        for (int i = 0; i < amount; i++) {
            ls.add(drawRandom())
        }
        return ls
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
