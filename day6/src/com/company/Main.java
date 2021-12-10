package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File("input.in"));

        String currentLine = null;

        List<Integer> list = new ArrayList<>();

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();

            String[] parts = currentLine.split(",");

            for(int i=0; i<parts.length; i++){
                list.add(Integer.parseInt(parts[i]));
            }

        }

//        for(int k=0; k<256; k++) {
//
//            int n = list.size();
//
//            for(int i=0; i<n; i++){
//
//                if(list.get(i) != 0){
//                    list.set(i, list.get(i)-1);
//                }
//                else{
//                    list.set(i, 6);
//                    list.add(8);
//                }
//
//            }
//
//        }

        long[] c = new long[10000];

        for(int i=0; i<9; i++){
            c[i] = 0;
        }

        for(int i=0; i < list.size(); i++){
            c[list.get(i)]++;
        }

        for(int i=0; i<256; i++){
            long births = c[0];
            c[0] = c[1];
            c[1] = c[2];
            c[2] = c[3];
            c[3] = c[4];
            c[4] = c[5];
            c[5] = c[6];
            c[6] = c[7] + births;
            c[7] = c[8];
            c[8] = births;
        }

        long sum = 0;
        for(int i=0; i<9; i++)
            sum+=c[i];

        System.out.print(sum);

    }
}
