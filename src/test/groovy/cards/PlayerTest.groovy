package cards

class PlayerTest extends GroovyTestCase {

    void testCalculateHandProbabilities() {
        Hand communityCards = Hand.fromString("AC,2C,3C,5C")
        Player me = new Player("Lucas")
        Deck deck = new Deck()
        deck.shuffle()
        me.drawFromDeck(deck, 2)
        println me
        me.calculateHandProbabilities(communityCards, deck)
        println me.probabilities
    }
}
