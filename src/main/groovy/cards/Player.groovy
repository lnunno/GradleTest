package cards

/**
 * A Player in a poker game.
 * Created by Lucas on 4/14/2015.
 */
class Player {

    String name
    Hand hand = new Hand()
    def probabilities = [:]
    static def NUM_MONTE_CARLO_HANDS = 500

    Player() {
        HandType.values().each {
            probabilities[it] = 0.0
        }
    }

    Player(String name) {
        this()
        this.name = name;
    }

    Player(Hand hand) {
        this()
        this.hand = hand;
    }

    /**
     * Reset all probabilities to 0.
     * @return
     */
    def clearProbabilities() {
        probabilities.collect { 0.0 }
    }

    /**
     * For texas hold 'em
     * @param communityCards
     * @param deck
     */
    def calculateHandProbabilities(List<Card> communityCards, Deck deck) {
        clearProbabilities()
        def handTypeFreqs = [:]
        HandType.values().each { handTypeFreqs[it] = 0 }
        def communityCardsSize = communityCards.size()
        def numCardsToDraw = PokerGame.NUM_COMMUNITY_CARDS - communityCardsSize
        if (numCardsToDraw <= 0) {
            def handType = bestHand(communityCards)
            probabilities[handType] = 1.0
            return
        }
        for (int i = 0; i < NUM_MONTE_CARLO_HANDS; i++) {
            def drawnCards = deck.drawRandomWReplacement(numCardsToDraw)
            def newCommunityCards = communityCards + drawnCards
            def bestHandType = bestHand(newCommunityCards)
            handTypeFreqs[bestHandType] += 1
        }
        probabilities = probabilities.collectEntries { handType, _ ->
            def p = handTypeFreqs[handType] / NUM_MONTE_CARLO_HANDS // Calculate the probability of getting this hand.
            [handType, p]
        }
    }

    def calculateHandProbabilities(Hand communityCards, Deck deck) {
        return calculateHandProbabilities(communityCards.cardList, deck)
    }

    def bestHand(List<Card> communityCards) {
        HandType handType = Hand.bestHand(hand.cardList, communityCards)
        return handType
    }

    def bestHand(Hand communityCards) {
        HandType handType = Hand.bestHand(hand, communityCards)
        return handType
    }

    /**
     * Evaluate hand and update probabilities accordingly.
     * @return The HandType that the player currently holds.
     */
    def evaluateHand() {
        HandType handType = hand.evaluate()
        return handType
    }

    def drawFromDeck(Deck deck) {
        hand.addToHand(deck.draw());
    }

    def drawFromDeck(Deck deck, int amount) {
        hand.addToHand(deck.draw(amount))
    }

    /**
     * Discard current hand and draw a new one.
     * @param deck Deck to draw from.
     */
    def drawHand(Deck deck) {
        hand.discard()
        List<Card> drawnCards = deck.draw(PokerGame.NUM_CARDS_IN_HAND)
        hand.addToHand(drawnCards)
    }

    def discardHand() {
        hand.discard()
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                '}';
    }
}
