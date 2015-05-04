package cards

class PlayerTest extends GroovyTestCase {

    void testCalculateHandProbabilities() {
        for (int communityCardsSize = 1; communityCardsSize <= 5; communityCardsSize++) {
            for (int i = 0; i < 5; i++) {
                println "=====HAND====="
                Deck deck = Deck.shuffledDeck()
                Hand communityCards = new Hand()
                Player me = new Player("Lucas")
                me.drawFromDeck(deck, 2)
                communityCards.addToHand(deck.draw(communityCardsSize))
                println me
                println communityCards
                me.calculateHandProbabilities(communityCards, deck)
                println me.probabilities
                println "=====ENDHAND====="
            }
        }
    }
}
