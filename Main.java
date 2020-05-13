package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import poker.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("''''''''''''''''''''''''''''''");
        System.out.println("'                            '");
        System.out.println("'                            '");
        System.out.println("'                            '");
        System.out.println("'           Welcome          '");
        System.out.println("'                            '");
        System.out.println("'                            '");
        System.out.println("'         Texas Poker        '");
        System.out.println("'                            '");
        System.out.println("'                            '");
        System.out.println("'                            '");
        System.out.println("''''''''''''''''''''''''''''''");

//        System.out.println("\nWhat poker game do you want to play?\n1. Texas Poker 2. ");
//        while (true) {
//            //Enter data using BufferReader
//            //Enter data using BufferReader
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(System.in));
//
//            // Reading data using readLine
//            String name = reader.readLine();
//
//            if (name == "1") {
//
//            }
//        }

        // System.out.println("Start Playing? (Y, N)");

        System.out.println("Let's start!");
        int i = 0;
        TexasPoker Po = new TexasPoker();
        TexasPoker Po2 = new TexasPoker();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        Po.initCard();
        Po2.initCard();
        boolean finalState = false;
        while(i < 5){
            if (i != 0){
                System.out.println("Continue playing? <Y, N>");
                // Reading data using readLine
                String answ = reader.readLine();

                if (answ.equals("N") || answ.equals("n")){
                    finalState = true;
                    TexasPoker.printCurrentStage(Po.getxCardsList(),
                            Po2.getxCardsList(), finalState);
                    break;
                }
            }

            Po.play();
            Po2.play();
            if (i == 4){
                finalState = true;
            }
            TexasPoker.printCurrentStage(Po.getxCardsList(),
                    Po2.getxCardsList(), finalState);
            i ++;
        }

        int result = TexasPoker.evaluate(Po.getxCards(), Po2.getxCards());

        if (result == 0){
            System.out.println("You Win!!!");
        }
        else if (result == 1){
            System.out.println("You Lost....");
        }
        else{
            System.out.println("It's a tie.");
        }
        

    }
}

























