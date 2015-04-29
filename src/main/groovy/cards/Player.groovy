package cards

import groovy.transform.ToString

/**
 * A Player in a poker game.
 * Created by Lucas on 4/14/2015.
 */
@ToString
class Player {

    String name;
    Hand hand = new Hand();

    Player() {}

    Player(String name){
        this.name = name;
    }

    Player(Hand hand) {
        this.hand = hand;
    }

    def drawFromDeck(Deck deck) {
        hand.addToHand(deck.draw());
    }

    def drawHand(Deck deck){
        hand.discard()
        List<Card> drawnCards = deck.draw(PokerGame.NUM_CARDS_IN_HAND)
        hand.addToHand(drawnCards)
    }

}
