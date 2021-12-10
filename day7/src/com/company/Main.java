package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("input.in"));

        String currentLine;

        currentLine = scanner.nextLine();

        String[] parts = currentLine.split(",");

        int[] entries = new int[parts.length];

        int maxim = Integer.MIN_VALUE;

        for(int i=0; i<parts.length; i++){
            int x = Integer.parseInt(parts[i]);
            entries[i] = x;
            if(x > maxim)
                maxim = x;
        }

        long minim = Integer.MAX_VALUE;

        for(int i=0; i<=maxim; i++){

            long sum = 0;

            for(int j = 0; j<entries.length; j++){
                int x = Math.abs(entries[j] - i);
                sum+=((x*(x+1))/2);
            }

            if(sum < minim)
                minim = sum;

        }

        System.out.println(minim);


    }
}
