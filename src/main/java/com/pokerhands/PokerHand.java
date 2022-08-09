package com.pokerhands;

import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PokerHand implements Comparable<PokerHand> {

    private List<Card> cards;

    private CombinationParser combinationParser;

    public PokerHand(String cards) {
        this.cards = PokerHandParser.parseHand(cards);
        this.combinationParser = new CombinationParser();
    }

    public Set<Card.Suit> getAllSuits() {
        return this.cards.stream()
                .map(Card::getSuit)
                .collect(Collectors.toSet());
    }

    public Card.Value getHighCardValue() {
        return this.cards.stream().max(Card::compareTo).get().getValue();
    }

    public Integer getHandIntValue() {
        return this.cards.stream()
                .mapToInt(Card::getIntegerValue)
                .sum();
    }

    public Collection<List<Card.Value>> groupByValue() {
        return this.cards.stream()
                .map(Card::getValue)
                .collect(Collectors.groupingBy(c -> c))
                .values();
    }

    public Map<Combination, Card.Value> getCombinations() {
        return combinationParser.parseCombinations(this);
    }

    @Override
    public int compareTo(PokerHand o) {
        Map<Combination, Card.Value> combinations = getCombinations();
        Map<Combination, Card.Value> oCombinations = o.getCombinations();

        Set<Combination> keySet = combinations.keySet();
        Set<Combination> oKeySet = oCombinations.keySet();

        if (keySet.equals(oKeySet)) {
            for (Combination combination : keySet) {
                return Integer.compare(
                        oCombinations.get(combination).getValue(),
                        combinations.get(combination).getValue());
            }
        }

        for (Combination combination : keySet) {
            for (Combination oCombination : oKeySet) {

                return Integer.compare(
                        Integer.parseInt(oCombination.getValue()),
                        Integer.parseInt(combination.getValue()));
            }
        }

        return 0;
    }
}
