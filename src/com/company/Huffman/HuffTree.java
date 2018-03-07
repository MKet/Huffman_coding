package com.company.Huffman;

import java.util.*;
import java.util.function.Consumer;

public class HuffTree {
    private Node startingNode;
    private BitSet bitSet;

    public HuffTree(String text) {
        startingNode = new Node();
        Compress(text);
        bitSet = generateBitmap(text);
    }

    public String Decompress() {
        StringBuilder builder = new StringBuilder();

        Node currentNode = startingNode;
        for(int i = 0; i< bitSet.size(); i++) {
            boolean bit = bitSet.get(i);

            if (bit) {
                currentNode = currentNode.getLeftNode();
            } else {
                currentNode = currentNode.getRightNode();
            }

            if (currentNode.isCharacterNode()) {
                builder.append(currentNode.getCharacter());
                currentNode = startingNode;
            }
        }

        return  builder.toString();
    }

    private void Compress(String text) {
        Map<Character, Integer> frequentie = getFrequentie(text);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        frequentie.forEach((c , i) -> {
           Node node = new Node(c, i);
           queue.add(node);
        });

        Node currentNode = null;
        while (queue.size() > 2) {
            Node n = queue.poll();
            if (currentNode == null)
                currentNode = n;
            else {
                Node next = new Node(currentNode.getFreguentie() + n.getFreguentie());
                next.setLeftNode(currentNode);
                next.setRightNode(n);

                queue.add(next);

                currentNode = null;
            }
        }

        startingNode.setLeftNode(queue.peek());
        startingNode.setRightNode(queue.peek());
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
        HashMap<Character, BitSet> map = new HashMap<>();
        fillBitmap(map, startingNode);

        BitSet encodedText = new BitSet();

        for (char w : text.toCharArray()) {
            BitSet bitSet = map.get(w);

            encodedText = concatenateBitSets(encodedText, bitSet);
        }

        return encodedText;
    }

    private BitSet concatenateBitSets(BitSet bits1, BitSet bits2) {
        if (bits1 == null || bits2 == null)
            return null;
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

    private void fillBitmap(HashMap<Character, BitSet> map, Node node) {
        fillBitmap(map, node, new BitSet(), 0);
    }

    private void fillBitmap(HashMap<Character, BitSet> map, Node node, BitSet bits, int index) {
        if (node.isCharacterNode()) {
            char character = node.getCharacter();
            map.put(character, (BitSet) bits.clone());
            return;
        }

        if (node.getLeftNode() != null) {
            bits.set(index, false);
            fillBitmap(map, node, bits, index +1);
        } else if (node.getRightNode() != null ) {
            node = node.getRightNode();
            bits.set(index, true);
            fillBitmap(map, node, bits, index +1);
        }
    }
}
