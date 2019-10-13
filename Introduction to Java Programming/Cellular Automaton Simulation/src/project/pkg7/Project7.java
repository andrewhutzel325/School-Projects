/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg7;

import java.util.Scanner;

/*
 * File: Project3
 * By: Andrew Hutzel
 * Date: November 2nd, 2015
 * Compile: Compiled in Netbeans 8.0.2
 * Usage: Netbeans 8.0.2
 * Description: Takes cell input <=80, takes steps, takes random indexed values
 * and places ones at said index value. According to given rules
 * the numbers will be manipulated into random shapes.
 */
public class Project7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int num1, step, index;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Welcome to the cellular automaton simulation!\nEnter number of cells (<= 80):");
            index = input.nextInt();
        } while (index > 80 || index<=3); // Input cell value, I made sure that the array will never go out of bounds.
        int[] cells;
        //Given index value, pass into cell.
        cells = new int [index];
        for (int i = 0; i < cells.length; i++) { //all cells indexes given zero
            cells[i] = 0;
        }
        System.out.print("Enter number of time steps:");
        step = input.nextInt(); // Take user's requested steps.
        System.out.print("Enter the index of occupied cells (negative index to end):");
        do{
        num1 = input.nextInt();
        if (num1>=0)
            cells[num1]=1;
        }while(num1>=0); // Take num1, place into index, make sure >=0
        for (int i = 0, x = 0; i < index; i++, x++) { //Ouput top line for cells
            if (x > 9) {
                x = 0;
                System.out.print(x);
            } else {
                System.out.print(x);
            }
            if (i+1 == index) {
                System.out.println(); // new line
            }

        }
        for (int i = 0; i <= step; i++) { // Depending on steps, continue to run until hit steps<=
            displayCells(cells);
            updateCells(cells);
        }
    }

    public static void displayCells(int data[]) { // Take index @ cells, check num, output character
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                System.out.print("#");
            } else if (data[i] == 0) {
                System.out.print(" ");
            }
            if (i + 1 == data.length) {
                System.out.println();
            }
        }
    }

    public static void updateCells(int data[]) { // Takes cells, applying rules to cell
        int[] dataCOPY;
        dataCOPY = new int[data.length]; // allocate data.length to dataCOPY
        for (int i = 0; i < data.length; i++) { // Pass all of data into dataCOPY
            dataCOPY[i] = data[i];
        }
        for (int i = 1; i < data.length; i++) { // eval given rules
            if ((i + 1) < data.length) { // Once i>0 we now can eval the 8 given rules.
                if (dataCOPY[i - 1] == 1 && dataCOPY[i] == 1 && dataCOPY[i + 1] == 1) {
                    data[i] = 0;
                }
                if (dataCOPY[i - 1] == 1 && dataCOPY[i] == 1 && dataCOPY[i + 1] == 0) {
                    data[i] = 1;
                }
                if (dataCOPY[i - 1] == 1 && dataCOPY[i] == 0 && dataCOPY[i + 1] == 1) {
                    data[i] = 1;
                }
                if (dataCOPY[i - 1] == 1 && dataCOPY[i] == 0 && dataCOPY[i + 1] == 0) {
                    data[i] = 0;
                }
                if (dataCOPY[i - 1] == 0 && dataCOPY[i] == 1 && dataCOPY[i + 1] == 1) {
                    data[i] = 1;
                }
                if (dataCOPY[i - 1] == 0 && dataCOPY[i] == 1 && dataCOPY[i + 1] == 0) {
                    data[i] = 1;
                }
                if (dataCOPY[i - 1] == 0 && dataCOPY[i] == 0 && dataCOPY[i + 1] == 1) {
                    data[i] = 1;
                }
                if (dataCOPY[i - 1] == 0 && dataCOPY[i] == 0 && dataCOPY[i + 1] == 0) {
                    data[i] = 0;
                }
            }
        }
    }
}
