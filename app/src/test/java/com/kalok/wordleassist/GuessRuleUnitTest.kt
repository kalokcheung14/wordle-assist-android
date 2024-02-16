package com.kalok.wordleassist

import com.kalok.wordleassist.utilities.DictionaryDataSource
import com.kalok.wordleassist.utilities.GuessRule
import com.kalok.wordleassist.utilities.GuessRuleImpl
import org.junit.Test
import org.junit.Rule
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import kotlin.test.BeforeTest
import kotlin.test.assertContentEquals
import kotlin.test.assertNotEquals

class GuessRuleUnitTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
//        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private lateinit var dictionaryDataSource: DictionaryDataSource
    private lateinit var guessRule: GuessRule

    @BeforeTest
    fun initialize() {
        // Initialize variables before test
        dictionaryDataSource = mockDictionaryDataSource(
            setOf(
                "apple",
                "lemon",
                "melon",
                "grape",
            )
        )
        guessRule = GuessRuleImpl(dictionaryDataSource)
    }

    private fun mockDictionaryDataSource(vocabSet: Set<String>) = declareMock<DictionaryDataSource> {
        given(getDictionary()).willReturn(
            vocabSet
        )
    }

    @Test
    fun test_inputMatchedLetterA_expectApple() {
        guessRule.addMatchAlphabet(0, 'a')
        val result = guessRule.showGuessList()

        assertContentEquals(listOf("apple"), result)
    }

    @Test
    fun test_inputMisplacedLetterA_expectAppleAndGrape() {
        guessRule.addMisplacedAlphabet(1, 'a')
        val result = guessRule.showGuessList()

        assertContentEquals(
            listOf("apple", "grape").sortedBy {
                  it.first()
            },
            result.sortedBy {
                it.first()
            }
        )
    }

    @Test
    fun test_misplacedLetterN_matchedLetterO_expectLemonAndMelon() {
        guessRule.addMisplacedAlphabet(1, 'n')
        guessRule.addMatchAlphabet(3, 'o')
        val result = guessRule.showGuessList()

        assertContentEquals(
            listOf(
                "lemon",
                "melon",
            ).sorted(),
            result.sorted()
        )
    }

    @Test
    fun test_invalidLetterL_expectGrape() {
        guessRule.addMismatchAlphabet('l')
        val result = guessRule.showGuessList()

        assertContentEquals(listOf("grape"), result)
    }

    @Test
    fun test_invalidWord_expectEmptyList() {
        guessRule.addMatchAlphabet(0, 'x')
        guessRule.addMatchAlphabet(1, 'y')
        guessRule.addMatchAlphabet(2, 'z')
        val result = guessRule.showGuessList()

        assertContentEquals(emptyList(), result)
    }

    @Test
    fun test_noInput_expectFullList() {
        val result = guessRule.showGuessList()

        assertContentEquals(dictionaryDataSource.getDictionary().sorted(), result.sorted())
    }

    @Test
    fun test_InvalidVocabLength_expectFiltered() {
        val mockedDataSource = mockDictionaryDataSource(
            setOf(
                "apple",
                "orange",
            )
        )
        val result = GuessRuleImpl(mockedDataSource).showGuessList()

        assertContentEquals(setOf("apple"), result)
    }

    @Test
    fun test_duplicatedAlphabets_DifferentStates_expectMisplacedOverridesMismatch() {
        guessRule.addMisplacedAlphabet(0, 'e')
        guessRule.addMismatchAlphabet('e')

        val result = guessRule.showGuessList()
        assertNotEquals(0, result.size)
    }

    @Test
    fun test_duplicatedAlphabets_DifferentStates_expectMatchPositionOverridesMismatch() {
        guessRule.addMismatchAlphabet('e')
        guessRule.addMatchAlphabet(4, 'e')

        val result = guessRule.showGuessList()
        assertNotEquals(0, result.size)
    }

    @Test
    fun test_duplicatedAlphabets_DifferentStates_expectMatchOverridesMisplaced() {
        guessRule.addMisplacedAlphabet(0, 'e')
        guessRule.addMatchAlphabet(4, 'e')

        val result = guessRule.showGuessList()
        assertNotEquals(0, result.size)
    }
}