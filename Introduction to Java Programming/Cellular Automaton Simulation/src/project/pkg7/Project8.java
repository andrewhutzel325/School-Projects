
/*
 * File: Project8
 * By: Andrew Hutzel and Banks Piccinini
 * Date: November 16th, 2040
 * Compile: Compiled in Netbeans 8.0.2
 * Usage: Netbeans 8.0.2
 * Description: Takes user input, places user's index values into 10x10 array,
 * displays location of elements in the datasets on a 2d plane, and sleeps 
 * inbetween steps for four seconds because E.C.
 */
package project.pkg8;

import java.util.Scanner;

/**
 *
 * @author Xyz
 */
public class Project8 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        int[][] x;
        int step, temp1=0,temp2=0;
        System.out.print("Enter what demensions you want the 2d array to be:");
        temp1=input.nextInt();
        temp2=input.nextInt();
        System.out.println("Please enter list of (i,j) pairs for populated cells\n(negative i or j to quit):");
        x = new int[temp1][temp2]; //declare 10x10 array
        for (int i = 0, j = 0;;) { //take input values
            i = input.nextInt();
            j = input.nextInt();
            if (i < 0 || j < 0) { // if neg value, break.
                break;
            } else {
                if (i == 0 || i == temp1-1 || j == 0 || j == temp2-1) { // if values are 0 or 9 for either index, make zero
                    x[i][j] = 0;
                } else {
                    x[i][j] = 1; // if values check out, place in index
                }
            }
        }
        System.out.print("Enter number of steps:");
        step = input.nextInt(); // Enter number of times program runs
        System.out.println("Inital grid:");
        displayGrid(x,temp1,temp2); //Display user element values selected

        for (int i = 0, j = 1; i < step; i++, j++) { // Enter for loop for x steps
            updateGrid(x,temp1,temp2);
            System.out.println("Time step:" + j);
            displayGrid(x,temp1,temp2);
            Thread.sleep(4000L); // Sleep four seconds because... Ec.

        }
    }

    public static void displayGrid(int mat[][], int temp1, int temp2) {
        for (int i = 0; i < temp1; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int i = 0; i < temp1; i++) {
            for (int c = 0; c < temp2; c++) { // Check all datasets of multidemensional array for 1 or 0
                if (mat[i][c] != 0) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(i);
        }
    }

    public static void updateGrid(int mat[][],int temp1,int temp2) {
        int matCOPY[][];
        matCOPY = new int[temp1][temp2]; //declare copy for 10x10 array in main

        for (int i = 1; i < temp1; i++) {
            for (int c = 1; c < temp2; c++) {
                matCOPY[i][c] = mat[i][c]; // Manually place all elements into copy
            }
        }

        for (int i = 1, neigh = 0; i < temp1-1; i++) {
            for (int c = 1; c < temp2-1; c++) { // Evaluate all eight spaces around selected space
                neigh = 0;

                if (matCOPY[i - 1][c] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i + 1][c] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i][c - 1] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i][c + 1] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i + 1][c + 1] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i - 1][c - 1] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i + 1][c - 1] == 1) {

                    neigh += 1;
                }
                if (matCOPY[i - 1][c + 1] == 1) {

                    neigh += 1;
                }
                if (neigh == 3) //if neigh==3, make a new cell
                {
                    mat[i][c] = 1;
                }
                if (neigh <= 1 || neigh >= 4) { //exceed neighbors or =1, make zero
                    mat[i][c] = 0;

                }
            }
        }

    }

}
