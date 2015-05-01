package cards

/**
 * Card ranks.
 * Created by Lucas on 4/6/2015.
 */
enum Rank {
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Ten("10"),
    Jack("J"),
    Queen("Q"),
    King("K"),
    Ace("A")

    def symbol

    Rank(String symbol){
        this.symbol = symbol
    }
}