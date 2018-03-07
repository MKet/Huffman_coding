package com.company.Huffman;

class Node implements Comparable<Node>{
    private Node leftNode;
    private Node rightNode;
    private char Character;
    private int Freguentie;

    public Node() { }

    public Node(char character, int freguentie) {
        Character = character;
        Freguentie = freguentie;
    }

    public Node(int freguentie) {
        Freguentie = freguentie;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public char getCharacter() {
        return Character;
    }

    public void setCharacter(char character) {
        Character = character;
    }


    public int getFreguentie() {
        return Freguentie;
    }

    public void setFreguentie(int freguentie) {
        Freguentie = freguentie;
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
