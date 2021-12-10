package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    long depth = 0;
        long horizontal = 0;
        int aim = 0;

        Scanner scanner = new Scanner(new File(args[0]));

        String currentLine = null;

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();

            String[] parts = currentLine.split(" ");

            String command = parts[0];
            int number = Integer.parseInt(parts[1]);

            if(command.equals("forward")){
                horizontal+=number;
                depth+=(aim*number);
            }
            else{
                if(command.equals("down"))
                    aim+=number;
                else{
                    aim-=number;
                }
            }

        }

        System.out.print(depth*horizontal);
    }
}
