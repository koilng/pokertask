package pokerhands;

import com.pokerhands.exception.*;
import com.pokerhands.Card;
import com.pokerhands.Combination;
import com.pokerhands.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    PokerHand hand = new PokerHand("9S TS JS QS KC");

    @Test
    void getAllSuitsThatAreEquals() {
        Set<Card.Suit> suitSet = Set.of(Card.Suit.Spades, Card.Suit.Clubs);
        assertEquals(suitSet, hand.getAllSuits());
    }

    @Test
    void getAllSuitsThatAreNotEquals() {
        Set<Card.Suit> suitSet = Set.of(Card.Suit.Spades, Card.Suit.Clubs, Card.Suit.Hearts);
        assertNotEquals(suitSet, hand.getAllSuits());
    }

    @Test
    void getHighCardValueThatEquals() {
        Card.Value testCardValue = Card.Value.King;
        assertEquals(hand.getHighCardValue(), testCardValue);
    }

    @Test
    void getHighCardValueThatNotEquals() {
        Card.Value testCardValue = Card.Value.Ace;
        assertNotEquals(hand.getHighCardValue(), testCardValue);
    }

    @Test
    void getHandIntValueThatEquals() {
        int expectedValue = 55;
        assertEquals(hand.getHandIntValue(), expectedValue);
    }

    @Test
    void groupByValueThatNotEquals() {
        int expectedValue = 50;
        assertNotEquals(hand.getHandIntValue(), expectedValue);
    }

    @Test
    void getCombinationsThatExistsAndEquals() {
        Map<Combination, Card.Value> expected = new HashMap<>();
        expected.put(Combination.STRAIGHT, Card.Value.King);

        assertEquals(hand.getCombinations(), expected);
    }

    @Test
    void getCombinationsThatNotExistsAndNotEquals() {
        Map<Combination, Card.Value> expected = new HashMap<>();
        expected.put(Combination.ROYAL_FLUSH, Card.Value.Ace);

        assertNotEquals(hand.getCombinations(), expected);
    }

    @Test
    void checkCardLengthThatIsNotEqualsTwo() {
        assertThrows(CardLengthIsNotValid.class, () -> {
            PokerHand hand = new PokerHand("9S TS JS QS KKC");
        });
    }

    @Test
    void checkInsufficientCardSuit() {
        assertThrows(CardSuitDoesNotExists.class, () -> {
            PokerHand hand = new PokerHand("9S TS JS QS KL");
        });
    }

    @Test
    void checkInsufficientCardValue() {
        assertThrows(CardValueDoesNotExists.class, () -> {
            PokerHand hand = new PokerHand("0S TS JS QS KC");
        });
    }

    @Test
    void checkFiveSimilarCardsInHand() {
        assertThrows(FiveSimilarCards.class, () -> {
            PokerHand hand = new PokerHand("5S 5S 5S 5S 5C");
        });
    }

    @Test
    void checkInsufficientHandSize() {
        assertThrows(HandSizeIsNotValid.class, () -> {
            PokerHand hand = new PokerHand("6C");
        });
    }

    @Test
    void checkCombinationsIsOptimizedAndItsSizeIsOne() {
        PokerHand testHand = new PokerHand("TS JS QS KS AS");

        Map<Combination, Card.Value> combinationsNotExpected = new HashMap<>();
        combinationsNotExpected.put(Combination.STRAIGHT, Card.Value.Ace);
        combinationsNotExpected.put(Combination.FLUSH, Card.Value.Ace);

        Map<Combination, Card.Value> combinations = testHand.getCombinations();

        assertNotEquals(combinations, combinationsNotExpected);
        assertNotEquals(combinations.size(), combinationsNotExpected.size());
    }

    @Test
    void checkRoyalFlushIsCorrect() {
        PokerHand testHand = new PokerHand("TS JS QS KS AS");
        Map<Combination, Card.Value> combinations = testHand.getCombinations();

        Map<Combination, Card.Value> combinationsExpected = new HashMap<>();
        combinationsExpected.put(Combination.ROYAL_FLUSH, Card.Value.Ace);

        assertEquals(combinations, combinationsExpected);
    }

    @Test
    void checkStraightFlushIsCorrect() {
        PokerHand testHand = new PokerHand("9S TS JS QS KS");
        Map<Combination, Card.Value> combinations = testHand.getCombinations();

        Map<Combination, Card.Value> combinationsExpected = new HashMap<>();
        combinationsExpected.put(Combination.STRAIGHT_FLUSH, Card.Value.King);

        assertEquals(combinations, combinationsExpected);
    }

    @Test
    void checkStraightFlushComparison() {
        PokerHand testHand = new PokerHand("9S TS JS QS KS");
        PokerHand testHand2 = new PokerHand("9S TS JS QS KS");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("8S 9S TS JS QS");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkForOfAKindComparison() {
        PokerHand testHand = new PokerHand("9S 9S 9S 9S 1C");
        PokerHand testHand2 = new PokerHand("9S 9S 9S 9S 1C");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("1C 8S 8S 8S 8S");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkFullHouseComparison() {
        PokerHand testHand = new PokerHand("9S 9S 9S TS TC");
        PokerHand testHand2 = new PokerHand("9S 9S 9S TS TC");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("8S 8S 8S TS TC");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkFlushComparison() {
        PokerHand testHand = new PokerHand("9S 9S 7S TS TS");
        PokerHand testHand2 = new PokerHand("9S 9S 7S TS TS");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("8S 8S 7S 9S 9S");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkStraightComparison() {
        PokerHand testHand = new PokerHand("9S TS JS QS KC");
        PokerHand testHand2 = new PokerHand("9S TS JS QS KC");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("8C 9S TS JS QS");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkThreeOfAKindComparison() {
        PokerHand testHand = new PokerHand("9S 9S 9S 7S 6C");
        PokerHand testHand2 = new PokerHand("9S 9S 9S 7S 6C");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("8S 8S 8S 7S 6C");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkPairsComparison() {
        PokerHand testHand = new PokerHand("9S 9S 8S 6S 5C");
        PokerHand testHand2 = new PokerHand("9S 9S 8S 6S 5C");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("7S 7S 8S 6S 5C");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

    @Test
    void checkHighHandComparison() {
        PokerHand testHand = new PokerHand("9S 8S 7S 6S AC");
        PokerHand testHand2 = new PokerHand("9S 8S 7S 6S AC");
        assertEquals(testHand.getCombinations(), testHand2.getCombinations());
        assertEquals(testHand.compareTo(testHand2), 0);

        testHand2 = new PokerHand("9S 8S 7S 6S KC");
        assertNotEquals(testHand.getCombinations(), testHand2.getCombinations());

        assertEquals(testHand.compareTo(testHand2), -1);
        assertEquals(testHand2.compareTo(testHand), 1);
    }

}