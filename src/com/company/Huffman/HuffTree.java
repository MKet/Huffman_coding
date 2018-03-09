package com.company.Huffman;

import java.util.*;

public class HuffTree {
    private Node startingNode;
    private BitSet bitSet;
    private int textLength;

    public HuffTree(String text) {
        Compress(text);
        textLength = text.length();
    }

    public String Decompress() {
        StringBuilder builder = new StringBuilder();

        Node currentNode = startingNode;
        for(int i = 0; i < bitSet.size(); i++) {
            boolean bit = bitSet.get(i);

            if (bit) {
                currentNode = currentNode.getRightNode();
            } else {
                currentNode = currentNode.getLeftNode();
            }

            if (currentNode.isCharacterNode()) {
                builder.append(currentNode.getCharacter());
                currentNode = startingNode;
            }
        }

        return  builder.substring(0, textLength);
    }

    private void Compress(String text) {
        Map<Character, Integer> frequentie = getFrequentie(text);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        frequentie.forEach((c , i) -> {
           Node node = new Node(c, i);
           queue.add(node);
        });

        while (!queue.isEmpty()) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();

            Node next = new Node(node1, node2);

            if (queue.isEmpty())
                startingNode = next;
            else
                queue.add(next);
        }
        bitSet = generateBitmap(text);
    }

    private Map<Character, Integer> getFrequentie(String text) {
        Map<Character, Integer> frequentieMap = new HashMap<>();
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

    private BitSet generateBitmap(String text) {
        HashMap<Character, String> map = new HashMap<>();
        fillBitmap(map, startingNode);

        BitSet encodedText = new BitSet();

        int I = 0;
        for (char w : text.toCharArray()) {
            String bits = map.get(w);

            for (Character c : bits.toCharArray()) {
                encodedText.set(I, c == '1');
                I++;
            }
        }

        return encodedText;
    }

    private BitSet concatenateBitSets(BitSet bits1, BitSet bits2) {
        if (bits1 == null)
            throw new IllegalArgumentException("Null not allowed. bits1 is null");
        if (bits2 == null)
            throw new IllegalArgumentException("Null not allowed. bits2 is null");
        BitSet bits1Clone = (BitSet)bits1.clone();
        BitSet bits2Clone = (BitSet)bits2.clone();
        int n = 5;//_desired length of the first (leading) vector
        int index = -1;
        while (index < (bits2Clone.length() - 1)) {
            index = bits2Clone.nextSetBit((index + 1));
            bits1Clone.set((index + n));
        }
        return bits1Clone;
    }

    private void fillBitmap(HashMap<Character, String> map, Node node) {
        fillBitmap(map, node, new StringBuilder(), 0);
    }

    private void fillBitmap(HashMap<Character, String> map, Node node, StringBuilder bits, int index) {
        if (node.isCharacterNode()) {
            char character = node.getCharacter();
            map.put(character, bits.toString());
            return;
        }

        if (node.getLeftNode() != null) {
            Node leftNode = node.getLeftNode();
            bits.append('0');
            fillBitmap(map, leftNode, bits, index + 1);
            bits.deleteCharAt(index);
        }

        if (node.getRightNode() != null) {
            Node rightNode = node.getRightNode();
            bits.append('1');
            fillBitmap(map, rightNode, bits, index + 1);
            bits.deleteCharAt(index);
        }

    }
}
