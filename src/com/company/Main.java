package com.company;

import com.company.Huffman.HuffTree;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
	    System.out.println("Give a string");

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        System.out.println("Processing....");
        final long startTimeTree = System.currentTimeMillis();

        HuffTree tree = new HuffTree(text);

        final long endTimeTree = System.currentTimeMillis();
        System.out.println("Tree build time: " + (endTimeTree - startTimeTree) );

        System.out.println("decompression test");
        final long startTimeDeco = System.currentTimeMillis();

        System.out.println(tree.toString());

        final long endTimeDeco = System.currentTimeMillis();
        System.out.println("Decompress Time: " + (endTimeDeco - startTimeDeco) );

        tree.forEach(System.out::print);

    }
}
