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
        Hand aceHighStraight = Hand.fromString("Ace of Clubs,  Five Heart,Four Heart,Two Heart,Three Spade")
        assertEquals(HandType.Straight, aceHighStraight.evaluate())

        Hand h1 = Hand.fromString("AC,3D,4C,5C,6D")
        assertEquals(HandType.High_Card, h1.evaluate())
    }

    void testBestHand() {
        Hand communityCards = Hand.fromString("2D,3D,4D,5D,6D")
        Hand myCards = Hand.fromString("3C,4C")
        assertEquals(HandType.Straight_Flush, Hand.bestHand(myCards, communityCards))

        communityCards = Hand.fromString("2C,3D,4C,5C,6D")
        myCards = Hand.fromString("3C,4C")
        assertEquals(HandType.Flush, Hand.bestHand(myCards, communityCards))

        communityCards = Hand.fromString("AC,3D,4C,5C,6D")
        myCards = Hand.fromString("2C,KH")
        assertEquals(HandType.Straight, Hand.bestHand(myCards, communityCards))

        communityCards = Hand.fromString("AC,3D,4C,5C,6D")
        myCards = Hand.fromString("3H,JH")
        assertEquals(HandType.Pair, Hand.bestHand(myCards, communityCards))
    }
}
