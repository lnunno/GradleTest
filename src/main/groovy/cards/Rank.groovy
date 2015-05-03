package cards

/**
 * Card ranks.
 * Created by Lucas on 4/6/2015.
 */
enum Rank {
    Two(),
    Three(),
    Four(),
    Five(),
    Six(),
    Seven(),
    Eight(),
    Nine(),
    Ten(),
    Jack(),
    Queen(),
    King(),
    Ace()

    static def symbolMap = [
            "2" : Two,
            "3" : Three,
            "4" : Four,
            "5" : Five,
            "6" : Six,
            "7" : Seven,
            "8" : Eight,
            "9" : Nine,
            "10": Ten,
            "J" : Jack,
            "Q" : Queen,
            "K" : King,
            "A" : Ace
    ].asImmutable()

    static def fromSymbol(String symbol) {
        return symbolMap[symbol]
    }
}