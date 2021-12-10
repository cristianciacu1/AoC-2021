package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void getNextValues(String nextDrawnValues, List<Integer> nextValues){
        String[] parts = nextDrawnValues.split(",");

        for(int i=0; i<parts.length; i++){
            int a = Integer.parseInt(parts[i]);
            nextValues.add(a);
        }
    }

    public static List<List<List<Integer>>> readMatrices (Scanner scanner){

        List<List<List<Integer>>> ans = new ArrayList<List<List<Integer>>>();

        while(scanner.hasNextInt()){

            List<List<Integer>> matrix = new ArrayList<List<Integer>>();
            for(int i=0; i<5; i++){
                List<Integer> currentLine = new ArrayList<>();
                for(int j=0; j<5; j++){
                    int x = scanner.nextInt();
                    currentLine.add(x);
                }
                matrix.add(currentLine);
            }

            ans.add(matrix);

        }

        return ans;

    }

    public static boolean checkWinner(List<List<Integer>> matrix){

//        check horizontal

        boolean okHorizontal = true;
        for(int i=0; i<5; i++){
            okHorizontal = true;
            for(int j=0; j<5; j++){
                if(matrix.get(i).get(j) != -1){
                    okHorizontal = false;
                }
            }
            if(okHorizontal)
                return true;
        }

//        check vertical
        boolean okVertical = true;
        for(int i=0; i<5; i++){
            okVertical = true;
            for(int j=0; j<5; j++){
                if(matrix.get(j).get(i) != -1){
                    okVertical = false;
                }
            }
            if(okVertical)
                return true;
        }

        return false;

    }

    public static void deepCopy(List<List<Integer>> before, List<List<Integer>> after, int value, int[] newValue){

        newValue[0] = value;

//        List<List<Integer>> after = new ArrayList<>();

        for(int i=0; i<5; i++){
            List<Integer> currentLine = new ArrayList<>();
            for(int j=0; j<5; j++){
                int x = before.get(i).get(j);
                currentLine.add(x);
            }
            after.add(currentLine);
        }

    }

    public static void replaceValue(List<List<List<Integer>>> matrix, List<List<Integer>> finalMatrix2, int value, int[] finalValue){

        for(int k = 0; k<matrix.size(); k++){

            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(matrix.get(k).get(i).get(j) == value){
                        matrix.get(k).get(i).set(j, -1);
                        if(checkWinner(matrix.get(k)) == true) {
                            finalMatrix2.clear();
                            deepCopy(matrix.get(k), finalMatrix2, value, finalValue);
                            matrix.remove(matrix.get(k));
                            if(matrix.size() == 0)
                                return;
                            k--;
                            i=5;
                            j=5;
                        }
                    }
                }
            }
        }

    }

    private static long finalAns(List<List<Integer>> lists, int value) {

        long sum = 0;

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){

                int x = lists.get(i).get(j);
                if(x != -1){
                    sum+=x;
                }

            }
        }

        return sum*value;

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.in"));

        String nextDrawnValues = scanner.nextLine();
        List<Integer> nextValues = new ArrayList<>();

        getNextValues(nextDrawnValues, nextValues);

        List<List<List<Integer>>> matrix = readMatrices(scanner);

        List<List<Integer>> finalMatrix = new ArrayList<>();
        int[] finalValue = new int[2];

        for(int i=0; i<nextValues.size(); i++){
            int x = nextValues.get(i);
            replaceValue(matrix, finalMatrix, x, finalValue);
            if(matrix.size() == 0){
                System.out.print(finalAns(finalMatrix, finalValue[0]));
                return;
            }
        }

    }
}
