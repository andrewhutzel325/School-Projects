/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFourAndBmiCalculator;

import java.util.Scanner;

/**
 *
 * @author yordanosmogos
 */
public class ConnectFour {

    public static void main(String[] args) {
        // DON'T MODIFY THE MAIN METHOD UNLESS FOR DEBUGGING
        //MAKE SURE YOU GET RID OF YOUR MODIFICATIONS HERE BEFORE SUBMISSION

        String[][] board = createEmptyBoard();
        String win = " ";

        Scanner input = new Scanner(System.in);

        boolean bl = true;

        printPattern(board);

        while (bl) {

            int player1 = 1, player2 = 2, userInput;

            System.out.println("Please drop a RED disk at the column between 0 and 6:");
            userInput = input.nextInt();
            dropDisk(board, userInput, player1);
            printPattern(board);

            win = checkWinner(board);
            if (" ".equals(win)) {
                System.out.println("Str is empty:" + win);

            } else {
                System.out.println("Str is NOT empty:" + win);
                break;
            }

            System.out.println("Please drop a YELLOW disk at the column between 0 and 6:");
            userInput = input.nextInt();
            dropDisk(board, userInput, player2);
            printPattern(board);

            win = checkWinner(board);
            if (" ".equals(win)) {
                System.out.println("Str is empty:" + win);

            } else {
                System.out.println("Str is NOT empty:" + win);
                break;
            }


        } // end of while loop

    } // end of main

    public static String[][] createEmptyBoard() {
        /* This method prints the first empty pattern for the game
         DON'T MODIFY THIS METHOD
         */

        String[][] f = new String[7][15];
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {

                if (j % 2 == 0) {
                    f[i][j] = "|";
                } else {
                    f[i][j] = " ";
                }
                if (i == 6) {
                    f[i][j] = "-";
                }
            }
        }

        return f;

    } // end of createEmptyBoard

    public static void printPattern(String[][] brd) {
        //Write your code here to print an updated pattern
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j < 15; j++) {

                System.out.print(brd[i][j]);

            }
            System.out.println();
        }

    } // end of printPattern

    public static void dropDisk(String[][] brd, int position, int player) {

        /*Write your code to drop the disk at the position the user entered
         depending on which player*/
        int i, column = position * 2 + 1;

        if (player == 1) {
            for (i = 5; i >= 0; i--) {
                if (brd[i][column] == " ") {
                    brd[i][column] = "R";
                    break;
                }

            }
        } else {
            for (i = 5; i >= 0; i--) {
                if (brd[i][column] == " ") {
                    brd[i][column] = "Y";
                    break;
                }

            }
        }

    } // end of dropDisk

    public static String checkWinner(String[][] brd) {

        /*Write your code to check if there is a winner. If there is, then
         return the charaster of the winners color( withe R or Y)
         */
        String str = " ", str1 = "Congratulations! Red player wins!", str2 = "Congratulations! Yellow player wins!";

        //Note that the numbers are only stored in ODD values, any EVEN values are given as Random lines and stuff
        //Any check on a normal space needs to be added by 2, so if we're looking to see if there is 4 in a row
        //there will need to be 8 AVAILABLE SPACES for a connect four, why? ==> EX: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
        //                                                                        0 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        1 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        2 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        3 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        4 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        5 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                        6 | Y | R | Y | Y | R |  Y   |  R |
        //                                                                                       I=7 J=15
        
//Correct
        //Check for horizontal
        for (int i = 0; i <= 6; i++) {
            for (int j = 1; j < 14; j += 2) {
                if (j - 6 >= 1) {
                    //Its possible that Horizontal to the LEFT could be a match!
                    //Check
                    //System.out.println("\nJ+6\n\n");
                    for (int x = j, y = 0, r = 0; x >= 1; x -= 2) {
                        
                        if (brd[i][x] == "Y") {
                            r=0;
                            y++;
                            //System.out.println("Yellow's value:"+y);
                        } else if (brd[i][x] == "R") {
                            y=0;
                            r++;
                            //System.out.println("Red's value:"+r);
                        }
                        if (r == 4) {
                            
                            str = "Red wins";
                        } else if (y == 4) {
                            
                            str = "Yellow wins";
                        }

                    }
                }

            }
        }
//Correct
        //Check for vertical
        for (int i = 0; i <= 6; i++) {
            for (int j = 1; j < 14; j += 2) {
                //Horizontal Down, potentiall redudant
                if ((i + 3) <= 6) {
                    for (int x = i, r = 0, y = 0; x <= 6; x++) {
                        if (brd[x][j] == "Y") {

                            y++;
                            //System.out.println("Yellow's value:"+y);
                        } else if (brd[x][j] == "R") {

                            r++;
                            //System.out.println("Red's value:"+r);
                        }
                        if (r == 4) {
                            
                            str = "Red wins";
                        } else if (y == 4) {
                            
                            str = "Yellow wins";
                        }
                    }
                }
                


            }

        }
//
//        //Left diagonal
        for (int i = 0; i <= 6; i++) {
            for (int j = 1; j < 14; j += 2) {
                if((i-3)>=0 && (j-6)>=1){
                    for(int x=i,z=j,r=0,y=0;x>=0 && z>=1;x--,z-=2){
                        if (brd[x][z] == "Y") {

                            y++;
                            //System.out.println("Yellow's value:"+y);
                        } else if (brd[x][z] == "R") {

                            r++;
                            //System.out.println("Red's value:"+r);
                        }
                        if (r == 4) {
                            
                            str = "Red wins";
                        } else if (y == 4) {
                            
                            str = "Yellow wins";
                        }
                    }
                }
            }
        }
        
        //Right Diagonal
                for (int i = 0; i <= 6; i++) {
            for (int j = 1; j < 14; j += 2) {
                if((i-3)>=0 && (j+6)<=13){
                    for(int x=i,z=j,r=0,y=0;x>=0 && z<=13;x--,z+=2){
                        if (brd[x][z] == "Y") {

                            y++;
                            //System.out.println("Yellow's value:"+y);
                        } else if (brd[x][z] == "R") {

                            r++;
                            //System.out.println("Red's value:"+r);
                        }
                        if (r == 4) {
                            
                            str = "Red wins";
                        } else if (y == 4) {
                            
                            str = "Yellow wins";
                        }
                    }
                }
            }
        }

        System.out.println(str);
        return str;
    } // end of checkWinner

} // end of class
