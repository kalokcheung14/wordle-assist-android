# wordle-assist-android
Wordle Assist on Android

This is a personal project I have been working on. I have created a Wordle Assist class in Java that can aid in playing Wordle the game: https://www.nytimes.com/games/wordle/index.html.
In this project I want to integrate that class into an Android application using Kotlin.

The core of the Wordle Assist is the GuessRule class. 
Basically, the GuessRule class maintains three sets of data for each guess of a word:
* Mismatch alphabets: a set of alphabets that do not in the word
* Misplaced alphabets: a set of alphabets that exist in the word, but are misplaced in wrong positions
* Match alphabets: a set of alphabets that exist in the word and are put in the correct positions

The class also contains an array of 5-letter words copied from the website source code. My future plan would be separating the array to a text file.
