package com.company.Huffman;

import java.util.Map;
import java.util.TreeMap;

public class HuffTree {
    private Node startingNode;

    public HuffTree(String text) {
        startingNode = new Node();
        Compress(text);
    }

    private void Compress(String text) {
        Map<Character, Integer> frequentie = getFrequentie(text);

        frequentie.forEach((c, i) -> {
            
        });
    }

    private Map<Character, Integer> getFrequentie(String text) {
        Map<Character, Integer> frequentieMap = new TreeMap<>();
        for (char w : text.toCharArray()){
            Integer n = frequentieMap.get(w);

            if (n == null) {
                frequentieMap.put(w, 1);
            } else {
                frequentieMap.replace(w, n+1);
            }
        }
        return frequentieMap;
    }

}
