/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ConnectFourAndBmiCalculator;
import java.util.Scanner;

/**
 *
 * @author tinder
 */
public class BMI_Calculator {
           // TODO code application logic here
    public static void main(String[] args) {
        int inches, feet, highIN = 0, LOWweight, HIGHweight,temp=0; //Declare ints, highIN has to be given value 0
        float BMI; // Declare float
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the low weight in pounds");
        LOWweight = input.nextInt(); // take low weight value
        System.out.print("Enter the high weight in pounds");
        HIGHweight = input.nextInt(); // take high weight value
        System.out.print("Enter your height in feet and then inches:");
        feet = input.nextInt();
        inches = input.nextInt();
        temp=(feet*12)+inches;
        System.out.print("Weight\t" + "BMI\t" + "Category\n");
        for (int i = 0; LOWweight<=HIGHweight;LOWweight+=5) // Enter nested loop, int i declared
        {

                
                System.out.print(LOWweight); // Outputs LOWweight + 9 spaces...
                BMI = LOWweight; // Int -> float
                //BMI needs inches so convert
                BMI = 703.0f * ((BMI) / ((temp) * (temp)));
                System.out.printf("\t%.1f", BMI); // Outputs up to 1 decimal places
                if (BMI > 25) // Tests for numbers larger than 25 if smaller # output execute other statement
                {
                    System.out.println("\tOver weight");
                } else {
                    System.out.println("\tNormal weight");
                }
                
            }
    }
}
