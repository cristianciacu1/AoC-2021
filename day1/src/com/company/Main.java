package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File(args[0]));

        int number = 0;

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        int sum1 = a+b+c;

        while(scanner.hasNextLine()){

            int sumAux = sum1;
            sumAux-=a;
            int auxC = c;

            c = scanner.nextInt();
            int sum2 = sumAux + c;

            if(sum2>sum1){
                number++;
            }

            a=b;
            b=auxC;
            sum1 = sum2;

        }

        System.out.print(number);
    }
}
