/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonnaci.sequence;

import static java.lang.System.out;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 *
 * @author Xyz
 */
public class Language2Learn {
    public static void main(String [] args)
    {
        int num=0,numpicked=0;
        String lang[];
        Scanner input = new Scanner(System.in);
        SecureRandom rand=new SecureRandom();
       out.print("Enter how many languages you want to enter:");
       num=input.nextInt();
       lang = new String[num];
       out.print("Enter the langauges:");
       lang[0]=input.nextLine();
       for(int x=0;x<num;++x)
       {
           lang[x]=input.nextLine();
       }  
       System.out.println("Language to learn over winter break:"+lang[numpicked]);
    }
    
}
