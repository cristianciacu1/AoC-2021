package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {



    public static void main(String[] args) throws FileNotFoundException {
	     Scanner scanner = new Scanner(new File("in.in"));

         int ans = 0;

         List<Long> list = new ArrayList<>();

         while(scanner.hasNextLine()){

             String currentLine = scanner.nextLine();
             char[] chars = currentLine.toCharArray();

             checkLine(chars, list);

         }

         list = list.stream().sorted().collect(Collectors.toList());

         int n = list.size();

         System.out.println(list.get(n/2));

    }

    private static void checkLine(char[] chars, List<Long> list) {

        LinkedList<Character> stack = new LinkedList<>();
        for(int i=0; i<chars.length; i++){
            if(chars[i] == '(' || chars[i] == '[' || chars[i] == '{' || chars[i] == '<'){
                stack.addFirst(chars[i]);
                continue;
            }
            else {
                if (chars[i] == ')') {
                    if(!stack.getFirst().equals('('))
                        return;
                    stack.removeFirst();
                }
                if (chars[i] == ']') {
                    if(!stack.getFirst().equals('['))
                        return;
                    stack.removeFirst();
                }
                if (chars[i] == '}') {
                    if(!stack.getFirst().equals('{'))
                        return;
                    stack.removeFirst();
                }
                if (chars[i] == '>') {
                    if(!stack.getFirst().equals('<'))
                        return;
                    stack.removeFirst();
                }
            }
        }

        long ans = 0;

        while(!stack.isEmpty()){

            Character current = stack.removeFirst();

            if(current.equals('(')){
                ans = ans*5 + 1;
            }
            if(current.equals('[')){
                ans = ans*5 + 2;
            }
            if(current.equals('{')){
                ans = ans*5 + 3;
            }
            if(current.equals('<')){
                ans = ans*5 + 4;
            }

        }

        list.add(ans);

    }
}
