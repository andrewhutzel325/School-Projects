/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonnaci.sequence;

import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Xyz
 */
public class JavaExample {
    public static void main(String [] args)
    {
        String a;
        String b;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string:");
        a=input.nextLine();
        b=input.nextLine();
        a=a+b;
        out.println(a);
    }
}
