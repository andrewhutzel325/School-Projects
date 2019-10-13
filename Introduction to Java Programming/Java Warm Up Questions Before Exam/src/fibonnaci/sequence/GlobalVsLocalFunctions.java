/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonnaci.sequence;

import static java.lang.System.out;

/**
 *
 * @author Xyz
 */
public class GlobalVsLocalFunctions {
    public static void main(String[]args)
    {
        int x=5;
        int y=2;
        out.println(x+y);
        out.println(temp(x+2,y));
        for(int i=0;i<20;i++)
        {
            System.out.println("x="+x);
        }
    }
    static int temp(int c, int n)
    {
        c+=n;
        return c;
    }
}
