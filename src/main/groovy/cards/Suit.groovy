package cards

/**
 * Created by Lucas on 4/6/2015.
 */
enum Suit {
    Club("?"),
    Diamond("?"),
    Heart("?"),
    Spade("?")

    def symbol

    Suit(String symbol){
        this.symbol = symbol
    }
}