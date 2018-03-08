package com.company.Huffman;

class Node implements Comparable<Node>{
    private Node leftNode;
    private Node rightNode;
    private char Character;
    private int Freguentie;

    public Node(char character, int freguentie) {
        Character = character;
        Freguentie = freguentie;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;

        Freguentie = 0;
        if (leftNode != null)
            Freguentie = leftNode.Freguentie;
        if (rightNode != null)
            Freguentie += rightNode.Freguentie;

    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public char getCharacter() {
        return Character;
    }

    public int getFreguentie() {
        return Freguentie;
    }

    @Override
    public int compareTo(Node o) {
        int frequentieResult = getFreguentie() - o.getFreguentie();
        if (frequentieResult == 0)
            return getCharacter() - o.getCharacter();
        else
            return  frequentieResult;
    }

    public boolean isCharacterNode() {
        return leftNode == null && rightNode == null;
    }
}
