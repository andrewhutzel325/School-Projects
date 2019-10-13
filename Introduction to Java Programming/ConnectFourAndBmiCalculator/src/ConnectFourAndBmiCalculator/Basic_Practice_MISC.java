/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFourAndBmiCalculator;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Xyz
 */
public class Basic_Practice_MISC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("NumSort Begins\n");
        NumSort();
        System.out.println("\nModulusOfTwo Begins\n");
        ModulusOfTwo();
        System.out.println("\nModulusOfTen Begins\n");
        ModulusOfTen();
        System.out.println("\nEvenOrOdd Begins\n");
        EvenOrOdd();

    }
    
    static int NumSort(){
                Scanner test = new Scanner(System.in);
        int a, b, c;
        System.out.print("A:");
        a = test.nextInt();
        System.out.print("B:");
        b = test.nextInt();
        System.out.print("C:");
        c = test.nextInt();
        System.out.println("A:" + a + "\nB:" + b + "\nC:" + c);
        if (a >= b && a >= c) {
            System.out.println("Biggest number:" + a);
            if (b >= c) {
                System.out.println("Middle number:" + b);
                System.out.println("Smallest number:" + c);
            } else {
                System.out.println("Middle number:" + c);
                System.out.println("Smallest number:" + b);
            }
        } else if (b >= a && b >= c) {
            System.out.println("Biggest number:" + b);
            if (a >= c) {
                System.out.println("Middle number:" + a);
                System.out.println("Smallest number:" + c);
            } else {
                System.out.println("Middle number:" + c);
                System.out.println("Smallest number:" + a);
            }
        } else if (c >= b && c >= a) {
            System.out.println("Biggest number:" + c);
            if (b >= a) {
                System.out.println("Middle number:" + b);
                System.out.println("Smallest number:" + a);
            } else {
                System.out.println("Middle number:" + a);
                System.out.println("Smallest number:" + b);
            }
        
    }
        return 0;
    }
    
    static int ModulusOfTwo(){
        
    Scanner test = new Scanner(System.in);
                    int i = 0, sum = 0;
            for (i = 0; i < 4; i++) {
                System.out.println("Stage(" + i + ")\nSum=" + sum);
                sum += i;
                do {
                    if (sum % 2 == 0) {
                        sum += 4;
                        System.out.println("Sum%2 is TRUE on stage (" + i + ")\nSum=" + sum);
                    } else {
                        sum += 6;
                        System.out.println("Sum%2 is FALSE on stage (" + i + ")\nSum=" + sum);
                    }
                } while (sum < 20);
                sum -= 3;
                System.out.println("We've EXITED the do while loop on Stage (" + i + ")\nSum=" + sum);
            }
            System.out.println("Total of sum is:" + sum);
        return 0;
    }
    
    static int ModulusOfTen(){
                int sum = 0, x = 4;
        for (; x > 0; x--) {
            sum += sum;
            for (;;) {
                sum += 4;
                if (sum % 10 == 0) {
                    System.out.println("Sum is:" + sum);
                    break;
                }
            }
            System.out.println("Sum OUTSIDE of loop:" + sum);
        }
        return 0;
    }
    
    static int EvenOrOdd(){
                int sum_2 = 0;
        Scanner test_2 = new Scanner(System.in);
        for (;;) {
            System.out.print("Enter number:");
            sum_2 = test_2.nextInt();
            if (sum_2 % 2 == 0) {
                System.out.println("Number is even!");
            } else {
                System.out.println("Number is odd!");
            }
            if (sum_2 == 0) {
                System.out.println("Exiting Program!");
                break;
            }
        }

        for (int i = 0; i < 5; i++) {
            int j = 0;
            while (j < i) {
                System.out.println(j);
                j++;
            }
        }
        return 0;
    }
}
