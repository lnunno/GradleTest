package cards

/**
 * Created by Lucas on 4/6/2015.
 */
Deck deck = new Deck();
println 'Unshuffled deck:'
println deck.toString()
deck.shuffle()
println 'Shuffled deck:'
println deck.toString()
deck.sort()
println 'Re-sorted deck:'
println deck.toString()
assert Rank.Eight > Rank.Seven
assert Rank.Queen > Rank.Jack
println Rank.Queen.toString()
Card c = new Card(Rank.Ace, Suit.Spade)
println c
Deck myDeck = new Deck();
Player me = new Player();
me.drawFromDeck(myDeck);
println me