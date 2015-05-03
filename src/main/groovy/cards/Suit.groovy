package cards

/**
 * Created by Lucas on 4/6/2015.
 */
enum Suit {
    Club(),
    Diamond(),
    Heart(),
    Spade()

    static def symbolMap = [
            "C": Club,
            "D": Diamond,
            "H": Heart,
            "S": Spade
    ]

    static def fromSymbol(String symbol) {
        return symbolMap[symbol]
    }
}