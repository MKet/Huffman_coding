package com.company.Huffman;

import java.util.*;
import java.util.function.Consumer;

public class HuffTree implements Iterable<Character> {
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

    private class HuffTreeIterator implements  Iterator<Character> {
        private int i = 0;
        private int currentCharIndex = 0;

        @Override
        public boolean hasNext() {
            return i < bitSet.size() && currentCharIndex < textLength;
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
}
