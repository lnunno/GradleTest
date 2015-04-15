package cards

import groovy.transform.Sortable

/**
 * Created by Lucas on 4/6/2015.
 */
@Sortable
class Card {
    Rank rank
    Suit suit

    Card(Rank rank, Suit suit) {
        this.rank = rank
        this.suit = suit
    }

    @Override
    String toString() {
        return rank.toString() + ' of ' + suit.toString() + 's'
    }
}
