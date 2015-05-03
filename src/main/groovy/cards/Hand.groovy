package cards

enum HandType {
    High_Card,
    Pair,
    Two_Pair,
    Three_of_a_Kind,
    Straight,
    Flush,
    Full_House,
    Four_of_a_Kind,
    Straight_Flush;
}

/**
 * A Hand in a game of poker.
 * Created by Lucas on 4/14/2015.
 */
class Hand {
    def cardList = []
    HandType type

    Hand() {}

    Hand(List<Card> cards) {
        this.cardList = cards;
    }

    def addToHand(Card card) {
        cardList += card;
    }

    def addToHand(List<Card> cardList) {
        this.cardList += cardList
    }

    /**
     * Discard whole hand.
     */
    def discard() {
        this.cardList.clear();
    }

    /**
     * Discard a specific card.
     * @param card The card to discard.
     */
    def discard(Card card) {
        this.cardList.remove(card);
    }

    static def combinations(def ls, int size) {
        def sets = [] as Set
        ls.eachPermutation {
            it = it.take(size)
            it.sort()
            sets << it
        }
        return sets as List
    }

    static def bestHand(List<Card> myCards, List<Card> communityCards) {
        def combos = combinations(myCards + communityCards, PokerGame.NUM_CARDS_IN_HAND)
        def evaluatedHands = combos.collect { List<Card> it ->
            Hand hand = new Hand(it)
            hand.evaluate()
        }
        def bestHand = evaluatedHands.max()
        return bestHand
    }

    static def bestHand(Hand myCards, Hand communityCards) {
        return bestHand(myCards.cardList, communityCards.cardList)
    }

    def rankUnique = { a, b -> a.rank <=> b.rank }

    HandType evaluate() {
        if (cardList.size() != 5) {
            throw new UnsupportedOperationException("Cannot evaluate hand with size not equal to 5!")
        }
        def sortedCardListRank = cardList.sort(false);
        Rank firstRank = sortedCardListRank[0].rank
        Rank lastRank = sortedCardListRank[4].rank
        Rank secondLastRank = sortedCardListRank[3].rank
        def rankDifference = Math.abs(firstRank.ordinal() - lastRank.ordinal())
        final def uniqueRanks = cardList.unique(false, rankUnique).size() == cardList.size()
        boolean isStraight = (rankDifference == 4) && (uniqueRanks)
        if (lastRank == Rank.Ace &&
                Math.abs(firstRank.ordinal() - secondLastRank.ordinal()) == 3 &&
                uniqueRanks
        ) {
            // Ace low straight.
            isStraight = true
        }
        def sortedCardListSuit = cardList.sort(false, { a, b -> a.suit <=> b.suit } as Comparator)
        Suit firstSuit = sortedCardListSuit[0].suit
        Suit lastSuit = sortedCardListSuit[4].suit
        def suitDifference = Math.abs(firstSuit.ordinal() - lastSuit.ordinal())
        boolean isFlush = suitDifference == 0
        def rankCounts = cardList.countBy { it.rank }
        def quadEntries = rankCounts.findAll { it.value == 4 }
        def threeEntries = rankCounts.findAll { it.value == 3 }
        def pairEntries = rankCounts.findAll { it.value == 2 } // Find all pairs
        if (isStraight && isFlush) {
            return HandType.Straight_Flush
        }
        if (quadEntries.size() >= 1) {
            return HandType.Four_of_a_Kind
        }
        if (threeEntries.size() >= 1 && pairEntries.size() >= 1) {
            return HandType.Full_House
        }
        if (isFlush) {
            return HandType.Flush
        }
        if (isStraight) {
            return HandType.Straight
        }
        if (threeEntries.size() >= 1) {
            return HandType.Three_of_a_Kind
        }
        if (pairEntries.size() == 2) {
            return HandType.Two_Pair
        }
        if (pairEntries.size() == 1) {
            return HandType.Pair
        }
        return HandType.High_Card
    }

    static Hand fromString(String s) {
        Hand hand = new Hand()
        String[] arr = s.split(",")
        arr.each {
            Card c = Card.fromString(it)
            hand.addToHand(c)
        }
        return hand
    }

    @Override
    String toString() {
        return "Hand: " + cardList.toString()
    }
}
