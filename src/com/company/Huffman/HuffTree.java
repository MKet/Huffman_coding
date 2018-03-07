package com.company.Huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HuffTree {
    private Node startingNode;

    public HuffTree(String text) {
        startingNode = new Node();
        Compress(text);
    }

    private void Compress(String text) {
        Map<Character, Integer> frequentie = getFrequentie(text);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        frequentie.forEach((c , i) -> {
           Node node = new Node(c, i);
           queue.add(node);
        });

        Node currentNode = null;
        for (Node n : queue) {
            if (currentNode == null)
                currentNode = n;
            else {
                Node next = new Node(currentNode.getFreguentie() + n.getFreguentie());
                next.setLeftNode(currentNode);
                next.setRightNode(n);

                if (queue.size() == 1)
                    startingNode.setRightNode(next);
                else if (queue.size() == 0)
                    startingNode.setLeftNode(next);
                else
                    queue.add(next);
                
                currentNode = null;
            }
        }
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

}
