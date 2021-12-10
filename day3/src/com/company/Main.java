package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int[][] newMatrix(int[][] matrix, int k, int bit, int pos){

        int[][] ans = new int[k][];
        int kaux = 0;

        for(int i=0; i<k; i++){
            if(matrix[i][pos] == bit){
                ans[kaux] = new int[matrix[i].length];
                for(int j=0; j<matrix[i].length; j++){
                    ans[kaux][j] = matrix[i][j];
                }
                kaux++;
            }
        }

        int[][] finalAns = new int[kaux][];
        for(int i=0; i<kaux; i++){
            finalAns[i] = new int[ans[i].length];
            for(int j=0; j<ans[i].length; j++){
                finalAns[i][j] = ans[i][j];
            }
        }

        return finalAns;

    }

    public static int[][] getDominantBit(int[][] matrix, int k, int nthbit){

        int[][] ans = new int[k][];
        int kaux = 0;

        int nr1 = 0 ;
        int nr0 = 0;

        for(int i=0; i<k; i++){
            if(matrix[i][nthbit] == 1)
                nr1++;
            else
                nr0++;
        }

        if(nr1>=nr0){
            ans = newMatrix(matrix, k, 1, nthbit);
            kaux = ans.length;
        }
        else {
            ans = newMatrix(matrix, k, 0, nthbit);
            kaux = ans.length;
        }

        return ans;
    }

    public static int[] oxygen(int[][] matrix, int k){

        int nthBit = 0;

        while(matrix.length != 1 && nthBit<matrix[0].length){
            matrix = getDominantBit(matrix, k, nthBit);
            k = matrix.length;
            nthBit++;
        }

        int[] answer = new int[matrix[0].length];

        for(int i=0; i<matrix[0].length; i++){
            answer[i] = matrix[0][i];
        }

        return answer;
    }

    public static int[] cotwo(int[][] matrix, int k){
        int nthBit = 0;

        while(matrix.length != 1 && nthBit<matrix[0].length){
            matrix = getLessDominantBit(matrix, k, nthBit);
            k = matrix.length;
            nthBit++;
        }

        int[] answer = new int[matrix[0].length];

        for(int i=0; i<matrix[0].length; i++){
            answer[i] = matrix[0][i];
        }

        return answer;
    }

    private static int[][] getLessDominantBit(int[][] matrix, int k, int nthbit) {

        int[][] ans = new int[k][];
        int kaux = 0;

        int nr1 = 0 ;
        int nr0 = 0;

        for(int i=0; i<k; i++){
            if(matrix[i][nthbit] == 1)
                nr1++;
            else
                nr0++;
        }

        if(nr0<=nr1){
            ans = newMatrix(matrix, k, 0, nthbit);
            kaux = ans.length;
        }
        else {
            ans = newMatrix(matrix, k, 1, nthbit);
            kaux = ans.length;
        }

        return ans;

    }

    public static int[][] deepCopy(int[][] matrix, int k){

        int[][] ans = new int[k][];
        for(int i=0; i<k; i++){
            ans[i] = new int[matrix[i].length];
            for(int j=0; j<matrix[i].length; j++){
                ans[i][j] = matrix[i][j];
            }
        }

        return ans;

    }

    public static long toDecimal(int[] input){

        long number = 0;
        int n = 0;

        for(int i=input.length-1; i>=0; i--){
            number+=input[i]*Math.pow(2, n);
            n++;
        }

        return number;

    }

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File(args[0]));

        int[][] matrix = new int[1001][];

        String currentNumber = null;

        int rowLength = 0;

        int k=0;

        while(scanner.hasNextLine()){

            currentNumber = scanner.nextLine();

            rowLength = currentNumber.length();
            matrix[k] = new int[currentNumber.length()];

            while(!currentNumber.isEmpty()){

                char last = currentNumber.charAt(currentNumber.length()-1);
                int lastNumber = last-'0';
                matrix[k][currentNumber.length()-1] = lastNumber;
                currentNumber = currentNumber.substring(0, currentNumber.length()-1);

            }

            k++;

        }

        int[][] aux = deepCopy(matrix, k);
        int[] oxygenLevel = oxygen(aux, k);
        int[] co2Level = cotwo(matrix, k);

        long oxygenLevelNumber = toDecimal(oxygenLevel);
        long cotwoLevelNumber = toDecimal(co2Level);

        System.out.println(oxygenLevelNumber*cotwoLevelNumber);

    }
}
