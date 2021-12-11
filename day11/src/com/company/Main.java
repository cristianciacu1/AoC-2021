package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File("in.in"));

        int[][] matrix = new int[12][];
        int k=0;

        int[] line1 = new int[12];

        for(int i=0; i<=11; i++){
            line1[i] = -1;
        }

        matrix[k++] = line1;

        String currentLine;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            String[] parts = currentLine.split("");
            int[] line = new int[12];
            line[0] = -1;
            for(int i=0; i<parts.length; i++){
                line[i+1] = Integer.parseInt(parts[i]);
            }
            line[11] = -1;
            matrix[k++] = line;
        }

        matrix[k++] = line1;

        for(int i=1; i<=300; i++) {
            solve(matrix);
            if(checkIfBigFlash(matrix)){
                System.out.println(i);
                return;
            }
        }

    }

    public static boolean checkIfBigFlash(int[][] matrix){
        for(int i=1; i<=10; i++){
            for(int j=1; j<=10; j++){
                if(matrix[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    private static void solve(int[][] matrix) {

        Queue<Integer> queue = new LinkedList<>();

        addOne(matrix, queue);

        while(!queue.isEmpty()){
            int i = queue.poll();
            int j = queue.poll();
            solvesSpecialCases(matrix, i, j, queue);
        }

    }

    private static void solvesSpecialCases(int[][] matrix, int i, int j, Queue<Integer> queue) {

        int di[] = {-1, -1, -1, 0, 1, 1, 1, 0};
        int dj[] = {-1, 0, 1, 1, 1, 0, -1, -1};
        for(int k=0; k<8; k++){
            int i1 = i+di[k];
            int j1 = j+dj[k];
            if(matrix[i1][j1]!=-1 && matrix[i1][j1]!=9 && matrix[i1][j1]!=0){
                matrix[i1][j1]++;
            }
            else{
                if(matrix[i1][j1] == 9){
                    queue.add(i1);
                    queue.add(j1);
                    matrix[i1][j1] = 0;
                }
            }
        }

    }

    private static void addOne(int[][] matrix, Queue<Integer> queue) {

        for(int i=1; i<=10; i++){
            for(int j=1; j<=10; j++){
                if(matrix[i][j]!=9){
                    matrix[i][j]++;
                }
                else{
                    queue.add(i);
                    queue.add(j);
                    matrix[i][j] = 0;
                }
            }
        }

    }
}
