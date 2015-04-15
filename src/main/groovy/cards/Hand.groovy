package cards

import groovy.transform.ToString

/**
 * Created by Lucas on 4/14/2015.
 */
@ToString
class Hand {
    List<Card> cardList = [];

    Hand() {}

    Hand(List<Card> cards) {
        this.cardList = cards;
    }

    def addToHand(Card card) {
        cardList.add(card);
    }
}
