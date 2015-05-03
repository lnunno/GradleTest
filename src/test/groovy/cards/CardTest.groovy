package cards

/**
 * Created by lnunno on 5/3/15.
 */
class CardTest extends GroovyTestCase {
    void testFromString() {
        assertEquals(new Card(Rank.Ace, Suit.Spade), Card.fromString("Ace Spade"))
        assertEquals(new Card(Rank.Ace, Suit.Spade), Card.fromString("Ace of Spades"))
        assertEquals(new Card(Rank.Ace, Suit.Spade), Card.fromString("AS"))
        assertEquals(new Card(Rank.Ten, Suit.Spade), Card.fromString("10S"))
    }
}
