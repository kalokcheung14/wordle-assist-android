# Wordle Assist (Android)
Wordle Assist on Android

This is a personal project I have been working on. I have created a Wordle Assist class in Java that can aid in playing Wordle the game: https://www.nytimes.com/games/wordle/index.html.
In this project I want to integrate that class into an Android application using Kotlin.

The core of the Wordle Assist is the GuessRule class. 
Basically, the GuessRule class maintains three sets of data for each guess of a word:
* Mismatch alphabets: a set of alphabets that do not in the word
* Misplaced alphabets: a set of alphabets that exist in the word, but are misplaced in wrong positions
* Match alphabets: a set of alphabets that exist in the word and are put in the correct positions

The class also contains an array of 5-letter words copied from the website source code. My future plan would be separating the array to a text file.

## Demostration

Let's say we are playing Wordle and we already guessed once.

<img src="https://res.cloudinary.com/dlqyw4big/image/upload/f_auto,q_auto/v1/wordle-assist/jcpcia3ljqupsd4plqpf" style="width:40%"/>

In each guess, the letters that are in the answer but are misplaced in the wrong positions will be highlighted in yellow, while letters that are not included in the answer will remain gray.

Then, we can input this exact input into the Wordle Assist app and get a list of vocabs that matches the guess result. The letter will change to red when it is selected.

<img src="https://res.cloudinary.com/dlqyw4big/image/upload/f_auto,q_auto/v1/wordle-assist/zxnxuhvgoooiawzimtfc" style="width:40%"/>

We can pick one of the possible answers and input it in the Wordle game. The letters highlighted in green are those placed in the correct positions.

<img src="https://res.cloudinary.com/dlqyw4big/image/upload/f_auto,q_auto/v1/wordle-assist/ommpfwxruklw539bpdid" style="width:40%"/>

We can then input this result to the Wordle Assist app and get a list of possible answers again.

<img src="https://res.cloudinary.com/dlqyw4big/image/upload/f_auto,q_auto/v1/wordle-assist/jkgpuq0jqvs3aukde1eh" style="width:40%"/>

Then we can pick one of the possible answers and input it in the Wordle game again.

<img src="https://res.cloudinary.com/dlqyw4big/image/upload/f_auto,q_auto/v1/wordle-assist/qndvjruasglbwnsi2nm5" style="width:40%"/>

Jackpot! We got the correct answer!

