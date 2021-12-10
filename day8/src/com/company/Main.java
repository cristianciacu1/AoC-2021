package com.company;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File("input.in"));

        String currentLine;

//        0 - 6
//        1 - 2
//        2 - 5
//        3 - 5
//        4 - 4
//        5 - 5
//        6 - 6
//        7 - 3
//        8 - 7
//        9 - 6

//        1, 4, 7, 8 matter

        while(scanner.hasNextLine()){

            currentLine = scanner.nextLine();

            String[] parts = currentLine.split(" \\| ");

            String[] parts2 = parts[1].split(" ");
            String[] parts3 = parts[0].split(" ");

            String[] numbers = new String[10];

            String ans = "";

            for(int i=0; i<parts2.length; i++){

                char tempArray[] = parts2[i].toCharArray();

                Arrays.sort(tempArray);

                String current = new String(tempArray);

                if(current.equals(zero)){
                    ans+="0";
                }
                if(current.equals(one)){
                    ans+="1";
                }
                if(current.equals(two)){
                    ans+="2";
                }
                if(current.equals(three)){
                    ans+="3";
                }
                if(current.equals(four)){
                    ans+="4";
                }
                if(current.equals(five)){
                    ans+="5";
                }
                if(current.equals(six)){
                    ans+="6";
                }
                if(current.equals(seven)){
                    ans+="7";
                }
                if(current.equals(eight)){
                    ans+="8";
                }
                if(current.equals(nine)){
                    ans+="9";
                }

            }

            System.out.println(ans);

        }

    }

    private static void determineNumbers(String[] parts3, String[] numbers) {

        String top = null;
        String topLeft = null;
        String topRight = null;
        String middle = null;
        String bottomLeft = null;
        String bottomRight = null;
        String bottom = null;

//        Taking the string of length 2
//        1

        for(int i=0; i< parts3.length; i++){
            if(parts3[i].length() ==  2){
                topRight = Character.toString(parts3[i].charAt(0));
                bottomRight = Character.toString(parts3[i].charAt(1));
            }
        }

//        Taking the string of length 3
//        7

        for(int i=0; i< parts3.length; i++){
            if(parts3[i].length() ==  3){
                String current = parts3[i];
                current.replaceAll(topRight, "");
                current.replaceAll(bottomRight, "");
                top = Character.toString(current.charAt(0));
            }
        }

//        taking 4 and 0 and compare topLeft edge

        String topLeftAux = null;
        String middleAux = null;
        String zeroAux = null;

        for(int i=0; i<parts3.length; i++){
            if(parts3[i].length() == 3){
                String current = parts3[i];
                current.replaceAll(topRight, "");
                current.replaceAll(bottomRight, "");
                topLeftAux = Character.toString(current.charAt(0));
                middleAux = Character.toString(current.charAt(1));
            }
            else{
                if(parts3[i].length() == 6 && !parts3[i].contains(topRight)) { // it means that this is 0
                    zeroAux = parts3[i];
                }
            }
        }

        if(zeroAux.contains(topLeftAux)){
            topLeft = topLeftAux;
            middle = middleAux;
        }
        else{
            topLeft = middleAux;
            middle = topLeftAux;
        }

//        now we check for nine

        for(int i=0; i<parts3.length; i++){

            if(parts3[i].length() == 6 && parts3[i].contains(topRight)){
                String current = parts3[i];
                current.replaceAll(top, "");
                current.replaceAll(topLeft, "");
                current.replaceAll(middle, "");
                current.replaceAll(topRight, "");
                current.replaceAll(bottomRight, "");
                middle = Character.toString(current.charAt(0));
                break;
            }

        }

//        Now we check for 8

        for(int i=0; i<parts3.length; i++){

            if(parts3[i].length() == 7){
                String current = parts3[i];
                current.replaceAll(top, "");
                current.replaceAll(topRight, "");
                current.replaceAll(topLeft, "");
                current.replaceAll(middle, "");
                current.replaceAll(bottomRight, "");
                current.replaceAll(bottom, "");
                bottomLeft = Character.toString(current.charAt(0));
                break;
            }

        }

        String zero = top + topLeft + topRight + bottomLeft + bottomRight + bottomLeft;
        char tempArray[] = zero.toCharArray();

        Arrays.sort(tempArray);

        String current = new String(tempArray);
        numbers[0] = current;

        String one = topRight + bottomRight;
        tempArray[] = one.toCharArray();

        Arrays.sort(tempArray);

        current = new String(tempArray);
        numbers[0] = current;

        String two = top + topRight + middle + bottomLeft + bottom;
        String three = top + topRight + bottomRight + middle + bottom;
        String four = topRight + middle + bottomRight;
        String five = top + topLeft + middle + bottomRight + bottom;
        String six = top+topLeft + middle + bottomLeft + bottomRight + bottom;
        String seven = top + topRight + bottomRight;
        String eight = top + topRight + topLeft + middle + bottomLeft + bottomRight + bottom;
        String nine = top + topRight + topLeft + middle + bottomRight + bottom;

    }
}
