package com.company;

import com.company.Huffman.HuffTree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Give a strrrrrring");

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        System.out.println("Processing....");
        HuffTree tree = new HuffTree(text);


        System.out.println("decompression test");

        System.out.println(tree.Decompress());

    }
}
