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

        List<List<Integer>> list = new ArrayList<>();

        int[][] matrix = new int[1000][1000];

        while(scanner.hasNextLine()){

            currentLine = scanner.nextLine();
            String[] parts = currentLine.split(" -> ");
            List<Integer> tempList = new ArrayList<>();
            String[] parts2 = parts[0].split(",");
            tempList.add(Integer.parseInt(parts2[0]));
            tempList.add(Integer.parseInt(parts2[1]));

            String[] parts3 = parts[1].split(",");
            tempList.add(Integer.parseInt(parts3[0]));
            tempList.add(Integer.parseInt(parts3[1]));

            list.add(tempList);

        }

        for(int i=0; i<list.size(); i++){

            int x1 = list.get(i).get(0);
            int x2 = list.get(i).get(2);
            int y1 = list.get(i).get(1);
            int y2 = list.get(i).get(3);

            int dx = x2-x1;
            int dy = y2-y1;
            if(dx!=0){
                dx = Math.floorDiv(dx, Math.abs(dx));
            }
            if(dy!=0){
                dy = Math.floorDiv(dy, Math.abs(dy));
            }
            int x = x1;
            int y = y1;
            while(true){
                matrix[x][y]+=1;
                if(x==x2 && y==y2)
                    break;
                x+=dx;
                y+=dy;
            }

        }

        long sum = 0;

        for(int i=0; i<1000; i++){
            for(int j=0; j<1000; j++){
                if(matrix[i][j]>=2)
                    sum++;
            }
        }

//        for(int i=0; i<1000; i++){
//            for(int j=0; j<1000; j++){
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.print(sum);

    }
}
