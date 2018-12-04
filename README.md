# poc-repo
used for poc projects 

#################################################################
######################## Project Card :########################
#################################################################


_______________________________
Prerequisites :

JAVA 1.8
MAVEN 3.3

Main class :
CardApplication
_______________________________


_______________________________
1) Create game
_______________________________
Post : localhost:8080/games
return the identifier of the created game

delete a game
Delete : localhost:8080/games/{gameId}

_______________________________
2) Create a deck
_______________________________
Post : localhost:8080/games/decks
localhost:8080/games/decks

_______________________________
3)Add a deck to a game deck
_______________________________
Put : localhost:8080/games/{gameId}/decks/{deckId}
localhost:8080/games/53/decks/54

_______________________________
4)Add players to a game
_______________________________
Post : localhost:8080/games/{gameId}/players/{login}
Example : To create a user "Alex" localhost:8080/games/53/players/Alex

Remove players from a game
Delete : localhost:8080/games/{gameId}/players/{login}
Example : To delete a user "Alex" localhost:8080/games/53/players/Alex
################################

_______________________________
5) Deal cards to each player in a game from the game deck
_______________________________
Get localhost:8080/games/{gameId}/cards/players/{login}
Example : localhost:8080/games/53/cards/players/Alex

Specifically, for a game deck containing only one deck of cards, a call to shuffle followed by 52 calls to dealCards(1) for the same player should result in the caller being provided all 52 cards of the deck in a random order. If the caller then makes a 53rd call to dealCard(1), no card is dealt. This approach is to be followed if the game deck contains more than one deck.
_______________________________
6)Get the list of cards for a player
GET localhost:8080/games/53/players/Alex/cards
_______________________________
7)Get the list of players in a game along with the total added value of all the cards each player holds; use face values of cards only. Then sort the list in descending order, from the player with the highest value hand to the player with the lowest value hand:
For instance if player ‘A’ holds a 10 + King then her total value is 23 and player ‘B’ holds a 7 + Queen then his total value is 19,  so player ‘A’ will be listed first followed by player ‘B’.

GET localhost:8080/games/53/players/sort
_______________________________

8)Get the count of how many cards per suit are left undealt in the game deck (example: 5 hearts, 3 spades, etc.)
GET localhost:8080/games/{gameId}/suit
_______________________________
9)Get the count of each card (suit and value) remaining in the game deck sorted by suit ( hearts, spades, clubs, and diamonds) and face value from high value to low value (King, Queen, Jack, 10….2, Ace with value of 1)
GET localhost:8080/games/53/countcards
_____________________________
10)Shuffle the game deck (shoe)
Shuffle returns no value, but results in the cards in the game deck being randomly permuted. Please do not use library-provided “shuffle” operations to implement this function. You may use library- provided random number generators in your solution.
Shuffle can be called at any time
localhost:8080/games/53/shuffle
_______________________________
