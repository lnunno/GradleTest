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

    static Card fromString(String s) {
        s = s.trim()
        boolean hasWhitespace = s.find("\\s") != null
        if (!hasWhitespace) {
            if (s.length() == 3) {
                return new Card(Rank.fromSymbol(s[0..1]), Suit.fromSymbol(s[2]))
            } else {
                return new Card(Rank.fromSymbol(s[0]), Suit.fromSymbol(s[1]))
            }
        }
        String[] strArray = s.split()
        if (strArray.length == 3) {
            strArray[1] = strArray[2]
            strArray[1] = strArray[1][0..-2]
        }
        Rank rank = strArray[0] as Rank
        Suit suit = strArray[1] as Suit
        return new Card(rank, suit)
    }

    @Override
    String toString() {
        return rank.toString() + ' of ' + suit.toString() + 's'
    }

    String toSymbol() {
        return rank.symbol + suit.symbol
    }
}
