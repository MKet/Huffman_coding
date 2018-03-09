package com.company.Huffman;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class HuffTree implements Iterable<Character>, Serializable {
    private Node startingNode;
    private BitSet bitSet;
    private int textLength;

    public HuffTree(String text) {
        if (text == null)
            throw new IllegalArgumentException("text cannot be null");

        textLength = text.length();

        if (textLength > 0)
            compress(text);
    }

    private void compress(String text) {
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
        HashMap<Character, List<Boolean>> map = new HashMap<>();
        fillBitmap(map, startingNode);

        BitSet encodedText = new BitSet();

        int I = 0;
        for (char w : text.toCharArray()) {
            List<Boolean> bits = map.get(w);

            for (boolean b : bits) {
                encodedText.set(I, b);
                I++;
            }
        }

        return encodedText;
    }

    private void fillBitmap(HashMap<Character, List<Boolean>> map, Node node) {
        fillBitmap(map, node,  new ArrayList<>(), 0);
    }

    private void fillBitmap(HashMap<Character, List<Boolean>> map, Node node, List<Boolean> bits, int index) {
        if (node.isCharacterNode()) {
            char character = node.getCharacter();
            map.put(character, new ArrayList<>(bits));
            return;
        }

        if (node.getLeftNode() != null) {
            Node leftNode = node.getLeftNode();
            bits.add(false);
            fillBitmap(map, leftNode, bits, index + 1);
            bits.remove(index);
        }

        if (node.getRightNode() != null) {
            Node rightNode = node.getRightNode();
            bits.add(true);
            fillBitmap(map, rightNode, bits, index + 1);
            bits.remove(index);
        }

    }

    private class HuffTreeIterator implements  Iterator<Character> {
        private int i = 0;
        private int currentCharIndex = 0;

        @Override
        public boolean hasNext() {
            return currentCharIndex < textLength && i < bitSet.size();
        }

        @Override
        public Character next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node currentNode = startingNode;
            try {
                while (hasNext()) {
                    boolean bit = bitSet.get(i);
                    i++;

                    if (bit) {
                        currentNode = currentNode.getRightNode();
                    } else {
                        currentNode = currentNode.getLeftNode();
                    }

                    if (currentNode.isCharacterNode()) {
                        currentCharIndex++;
                        return currentNode.getCharacter();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            throw new TreeMalformedException();
        }
    }

    @Override
    public Iterator<Character> iterator() {
        return new HuffTreeIterator();
    }

    @Override
    public void forEach(Consumer<? super Character> action) {
        for (char c: this) {
            action.accept(c);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (char c: this) {
            builder.append(c);
        }

        return  builder.toString();
    }
}
