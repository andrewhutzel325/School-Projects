/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonnaci.sequence;
import java.util.Scanner;
/**
 *
 * @author Xyz
 */
public class FibonnaciSequence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int []y;
        int length;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the length of the fibonacci sequence you'd like to calculate:");
        length=input.nextInt();
        y = new int[length];
        //Always give y[1]=1;
        y[1]=1;
        //Call fibonnaci method
        fib(y,length);
        for(int i=0;i<length;i++)
        {
            System.out.println("y["+i+"]="+y[i]);
        }
    }
    public static void fib(int []y, int length)
    {
      
        for(int a=2;a<length;a++)
        {
            y[a]=y[a-2]+y[a-1];
        }
        
    }
}
