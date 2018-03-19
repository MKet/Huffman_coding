package com.company;

import com.company.Huffman.HuffTree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Huffman");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(tree);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           try {
               if (fos != null)
                   fos.close();
               if (oos != null)
                   oos.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }

    }
}
