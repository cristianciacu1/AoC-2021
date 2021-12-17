package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static boolean trajectoryPlanner(int xVelocity, int yVelocity, int x1, int x2, int y1, int y2){

        int currentX = 0, currentY = 0;

        while(!(currentX >= x1 && currentX <= x2 && currentY >= y1 && currentY <= y2)){

            currentX+=xVelocity;
            currentY+=yVelocity;

            if(currentX > x2 || currentY < y1)
                return false;

            if(xVelocity == 0){
                if(currentX >= x1 && currentX <= x2){
                    yVelocity--;
                    continue;
                }
                else
                    return false;
            }

            if(xVelocity > 0){
                xVelocity--;
                yVelocity--;
            }
            else{
                xVelocity++;
                yVelocity--;
            }

        }

        return true;

    }

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File("in.in"));

        String currentLine = scanner.nextLine();
        String[] parts = currentLine.split("target area: ");
        String[] miniParts = parts[1].split(", ");

        String[] xParts = miniParts[0].split("x=");
        String[] xMiniParts = xParts[1].split("\\.\\.");

        int x1 = Integer.parseInt(xMiniParts[0]);
        int x2 = Integer.parseInt(xMiniParts[1]);

        String[] yParts = miniParts[1].split("y=");
        String[] yMiniParts = yParts[1].split("\\.\\.");

        int y1 = Integer.parseInt(yMiniParts[0]);
        int y2 = Integer.parseInt(yMiniParts[1]);

//        System.out.println(x1 + " " + x2 + " " + y1 + " " + y2);

        int yMax = Integer.MIN_VALUE;
        int counter = 0;

        for(int i=1; i<=1000; i++){
            for(int j=-1000; j<=1000; j++){
                if(trajectoryPlanner(i, j, x1, x2, y1, y2))
                    counter++;
            }
        }

//        System.out.println(yMax);
        System.out.println(counter);


    }
}
