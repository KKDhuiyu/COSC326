# Shark Quick Reference

Read SharkInstructions.pdf first.

## Getting Started

To get started with the project, import `shark-impl` into eclipse as a maven project.

To build your jar, either run maven package from eclipse or `mvn package` in the shark-impl directory, your jar will be called `please-change-this-to-a-unique-name.jar`, please rename before you submit.

To run the tournament, place your built jar in ai-jars and run: `java -jar tournament.jar config.json`.

## Writing your AI

Please implement the `playCardFromHand` method of CardGameStrategy. A skeleton has been provided for you. For javadocs, see: http://shark.odl.io/docs

## Last card rules

You start with a hand of seven cards, your goal is to get rid of all of them.

To play a card, it must have the same suit or value as the most recently played card (`GameState.lastPlayed`).

You may play zero to four cards per turn (`Move.cardsPlayed`),they must all be the same value, the first card you play must match the suit or value of the most recently played card.

If you cannot play any cards, you can pass (`Move.Pass_Move`), you will be made to draw one card.

An ace can be played on anything, and lets you choose the currently active suit (`Move.suitCalled`).

If a two or a five is played, you may not play and you must draw that many cards, unless you can play another two (if a two was played) or five (if a five was played), which will cause the next player to need to draw two/five cards for each card played. An ace cannot cancel this effect.

Playing a ten will cause the next player to miss their turn.

Playing a jack will reverse the direction of play.

You must call last card (`Move.lastCardCalled`) if you think you will win next turn. If you finish without calling last card, you must draw two more cards, putting you at a disadvantage.  

You cannot end on an ace.

### Card Values

Ace: 5
Two, Five: 2
Everything else: 1
