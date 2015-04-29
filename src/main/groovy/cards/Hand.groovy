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
@ToString
class Hand {
    def cardList = []

    Hand() {}

    Hand(List<Card> cards) {
        this.cardList = cards;
    }

    def addToHand(Card card) {
        cardList += card;
    }

    def addToHand(List<Card> cardList){
        this.cardList += cardList
    }

    /**
     * Discard whole hand.
     */
    def discard(){
        this.cardList.clear();
    }

    /**
     * Discard a specific card.
     * @param card The card to discard.
     */
    def discard(Card card){
        this.cardList.remove(card);
    }

    def rankUnique = { a, b -> a.rank <=> b.rank }
    def suitUnique = { a, b -> a.suit <=> b.suit }

    HandType evaluate(){
        def sortedCardListRank = cardList.sort(false);
        Rank firstRank = sortedCardListRank[0].rank
        Rank lastRank = sortedCardListRank[4].rank
        def rankDifference = Math.abs(firstRank.ordinal()-lastRank.ordinal())
        boolean isStraight = (rankDifference == 5) && (cardList.unique(false,rankUnique).size() == cardList.size())
        def sortedCardListSuit = cardList.sort(false, { a,b -> a.suit <=> b.suit } as Comparator)
        Suit firstSuit = sortedCardListSuit[0].suit
        Suit lastSuit = sortedCardListSuit[4].suit
        def suitDifference = Math.abs(firstSuit.ordinal()-lastSuit.ordinal())
        boolean isFlush = suitDifference == 0
        if(isStraight){
            return HandType.Straight
        }
        if(isFlush){
            return HandType.Flush
        }
        return HandType.High_Card
    }
}
