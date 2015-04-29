package cards

/**
 * Created by lnunno on 4/28/15.
 */
class HandTest extends GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void tearDown() {

    }

    void testAddToHand() {

    }

    void testAddToHand1() {

    }

    void testDiscard() {

    }

    void testDiscard1() {

    }

    void testEvaluate() {
        Hand straight = new Hand([new Card(Rank.Seven, Suit.Club),
                                  new Card(Rank.Three, Suit.Club),
                                  new Card(Rank.Four, Suit.Heart),
                                  new Card(Rank.Five, Suit.Diamond),
                                  new Card(Rank.Six, Suit.Spade)])
        assertEquals(HandType.Straight, straight.evaluate())
    }
}
