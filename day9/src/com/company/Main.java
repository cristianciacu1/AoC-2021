package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("in.in"));

        int[][] matrix = new int[103][];
        int[][] matrix2 = new int[103][];
        int k = 0;

        int[] line2 = new int[103];
        int[] line3 = new int[103];
        for(int i=0; i<102; i++){
            line2[i] = 10;
            line3[i] = 10;
        }
        matrix[k] = line2;
        matrix2[k++] = line3;

        while(scanner.hasNextLine()) {
            String a = scanner.nextLine();
            String[] parts = a.split("");

            int[] line = new int[103];
            int[] line1 = new int[103];
            line[0] = 10;
            line1[0] = 10;
            line[101] = 10;
            line1[101] = 10;

            for(int i=0; i<parts.length; i++){
                line[i+1] = Integer.parseInt(parts[i]);
                line1[i+1] = Integer.parseInt(parts[i]);
            }

            matrix[k] = line;
            matrix2[k++] = line1;

        }

        matrix[k] = line2;
        matrix2[k++] = line3;

        List<Integer> list = new ArrayList<>();

        for(int i=1; i<=100; i++){
            for(int j=1; j<=100; j++){
                if(matrix[i][j]!=9)
                    if(matrix[i][j] < matrix[i-1][j] &&
                    matrix[i][j] < matrix[i][j+1] &&
                    matrix[i][j] < matrix[i+1][j] &&
                    matrix[i][j] < matrix[i][j-1]){
                        int basinsSize = calculateBasinSize(matrix2, i, j, matrix[i][j]);
                        list.add(basinsSize);
                    }
            }
        }

        list = list.stream().sorted().collect(Collectors.toList());
        long ans = 1;
        ans*=list.get(list.size()-1);
        ans*=list.get(list.size()-2);
        ans*=list.get(list.size()-3);
        System.out.println(ans);

    }

    private static int calculateBasinSize(int[][] matrix, int i, int j, int value) {
        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};
        int ans = 1;
        for(int k=0; k<4; k++){
            int i1 = i+di[k];
            int j1 = j+dj[k];

            matrix[i][j] = -1;
            if(matrix[i1][j1] != -1 && matrix[i1][j1] != 9 && matrix[i1][j1] != 10 && matrix[i1][j1] > value){
                ans+=calculateBasinSize(matrix, i1, j1, matrix[i1][j1]);
            }

        }
        return ans;
    }
}
