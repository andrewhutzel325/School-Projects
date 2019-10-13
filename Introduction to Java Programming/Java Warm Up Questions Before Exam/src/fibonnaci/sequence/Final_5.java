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
public class Final_5 {

    public static void main(String[] args) {
       System.out.println("Don't be over whelmed. We did this in project 8!");
       // Lets begin by assigning the static values to the 3x3 grid!
       int x[][];
       x= new int[3][3];
       x[0][0]=1;
       x[0][1]=1;
       x[1][1]=1;
       x[2][2]=1;

        update(x);
        for(int i=0;i<3;i++)
        {
            for(int c=0;c<3;c++)
            {
                System.out.print(x[i][c]);
                       
            }
            out.println();
        }

    }
    public static void update(int [][]grid)
    {
        int neigh=0;
       for(int i=0;i<3;i++)
       {
           for(int c=0;c<3;c++)
           {
               if(grid[i][c]==1)
               {
                   neigh+=1;
               }
           }
       }
       if(neigh==4)
       {
           grid[1][1]=0;
       }
    }
}
