/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonnaci.sequence;

/**
 *
 * @author Xyz
 */
class Question_5_Quiz {

    public static void main(String[] args) {
        int[][] x;
        x = new int[5][5];
        x[0][0]=1;
        x[1][1]=1;
        x[2][2]=1;
        x[3][3]=1;
        x[4][4]=1;
        for (int i = 0; i < 4; i++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(x[i][c]);
            }
            System.out.println();
        }
        update(x);
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(x[i][c]);
            }
            System.out.println();
        }
    }
    
    static void  update(int [][]x)
    {
        int neigh=0;
        for(int i=0;i<4;i++)
        {
            for(int c=0;c<4;c++)
            {
                if(x[i][c]==1)
                {
                    neigh+=1;
                }
            }
        }
        if(neigh==4)
        {
            for(int i=0;i<4;i++)
                for(int c=0;c<4;c++)
                    x[i][c]=0;
        }
    }
}
