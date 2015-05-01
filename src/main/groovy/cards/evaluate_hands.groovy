package cards

import groovy.time.TimeCategory
import groovy.time.TimeDuration

/**
 * Evaluate a bunch of poker hands.
 * Created by Lucas on 4/30/2015.
 */
Player me = new Player("Lucas")
Date start = new Date()
for (int i = 0; i < 1000000; i++) {
    def d = Deck.shuffledDeck()
    me.drawHand(d)
    def handType = me.hand.evaluate()
    me.discardHand()
}
Date stop = new Date()
def td = TimeCategory.minus(stop,start)
println 'Took: ' + td.toString()