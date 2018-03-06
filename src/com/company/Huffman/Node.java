package com.company.Huffman;

class Node {
    private Node leftNode;
    private Node rightNode;
    private char Character;
    private int Freguentie;

    public Node() { }

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
}
