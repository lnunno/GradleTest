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
    def cardList = [];

    Hand() {}

    Hand(List<Card> cards) {
        this.cardList = cards;
    }

    def addToHand(Card card) {
        cardList.add(card);
    }

    def addToHand(List<Card> cardList){
        this.cardList.addAll(cardList)
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

    HandType evaluate(){
        sortedCardListRank = cardList.sort();
        sortedCardListSuit = cardList.sort({ a,b -> a.suit <=> b.suit } as Comparator)
    }
}
