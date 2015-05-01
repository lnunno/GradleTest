package cards

import groovy.transform.ToString

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

    static def combinations(def ls){
        if (ls.size() == 0 ){
            return []
        }
        def head = ls[0]
        def cdr = ls.subList(1,ls.size)
        def acc = []
        cdr.eachWithIndex { it, index ->
            def sub = cdr.sublist(index,cdr.size())
            acc += [head] + combinations(sub)
        }
        return acc
    }

    def rankUnique = { a, b -> a.rank <=> b.rank }

    HandType evaluate() {
        if (cardList.size() != 5) {
            throw new UnsupportedOperationException("Cannot evaluate hand with size not equal to 5!")
        }
        def sortedCardListRank = cardList.sort(false);
        Rank firstRank = sortedCardListRank[0].rank
        Rank lastRank = sortedCardListRank[4].rank
        def rankDifference = Math.abs(firstRank.ordinal() - lastRank.ordinal())
        boolean isStraight = (rankDifference == 4) && (cardList.unique(false, rankUnique).size() == cardList.size())
        def sortedCardListSuit = cardList.sort(false, { a, b -> a.suit <=> b.suit } as Comparator)
        Suit firstSuit = sortedCardListSuit[0].suit
        Suit lastSuit = sortedCardListSuit[4].suit
        def suitDifference = Math.abs(firstSuit.ordinal() - lastSuit.ordinal())
        boolean isFlush = suitDifference == 0
        def rankCounts = cardList.countBy {it.rank}
        def quadEntries = rankCounts.findAll { it.value == 4}
        def threeEntries = rankCounts.findAll { it.value == 3}
        def pairEntries = rankCounts.findAll { it.value == 2} // Find all pairs
        if (isStraight && isFlush){
            return HandType.Straight_Flush
        }
        if (quadEntries.size() >= 1){
            return HandType.Four_of_a_Kind
        }
        if(threeEntries.size() >= 1 && pairEntries.size() >= 1){
            return HandType.Full_House
        }
        if (isFlush) {
            return HandType.Flush
        }
        if (isStraight) {
            return HandType.Straight
        }
        if(threeEntries.size() >= 1){
            return HandType.Three_of_a_Kind
        }
        if(pairEntries.size() == 2){
            return HandType.Two_Pair
        }
        if(pairEntries.size() == 1){
            return HandType.Pair
        }
        return HandType.High_Card
    }

    @Override
    String toString() {
        return "Hand: " + cardList.toString()
    }
}
