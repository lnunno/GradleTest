package cards

/**
 * Created by Lucas on 4/30/2015.
 */
def a = Hand.combinations([1,2,3,4,5,6,7],5)
println a
println a.size()
println Hand.bestHand(
        [new Card(Rank.Two,Suit.Club),
         new Card(Rank.Three,Suit.Club)],
        [new Card(Rank.Ten,Suit.Heart),
         new Card(Rank.Jack,Suit.Spade),
         new Card(Rank.Queen,Suit.Club),
         new Card(Rank.King,Suit.Club),
         new Card(Rank.Ace,Suit.Club)]
)