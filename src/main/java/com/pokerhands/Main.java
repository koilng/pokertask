package com.pokerhands;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        PokerHand pokerHand3 = new PokerHand("9S TS JS QS KS");
        PokerHand pokerHand2 = new PokerHand("8S 9S TS JS QS");
        PokerHand pokerHand1 = new PokerHand("TS TH TC TC KC");
        PokerHand pokerHand = new PokerHand("JS JH QC KC AC");
        ArrayList<PokerHand> pokerHandList = new ArrayList<>();

        pokerHandList.add(pokerHand3);
        pokerHandList.add(pokerHand2);
        pokerHandList.add(pokerHand1);
        pokerHandList.add(pokerHand);

        Collections.sort(pokerHandList);

        pokerHandList.forEach(e -> {
            System.out.println(e.getCombinations());
            System.out.println(e.groupByValue());
//            System.out.println(e.getHandIntValue());
//            System.out.println(e.getAllSuits());
//            System.out.println(e);
        });
    }
}
