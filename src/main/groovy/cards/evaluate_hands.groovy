package cards

/**
 * Evaluate a bunch of poker hands.
 * Created by Lucas on 4/30/2015.
 */
Player me = new Player("Lucas")
for (int i = 0; i < 100; i++) {
    def d = Deck.shuffledDeck()
    me.drawHand(d)
    def handType = me.hand.evaluate()
    println me
    println handType
    me.discardHand()
}