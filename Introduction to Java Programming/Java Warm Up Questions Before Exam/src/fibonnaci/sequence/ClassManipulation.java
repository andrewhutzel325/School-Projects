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
public class ClassManipulation {

    public static void main(String[] args) {
        int[][] x = {{3, 1, 4, 2}, {1, 5, 2, -1}, {2, 4, 1, 8}, {1, 2, 3, 4}};

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                //We've entered a nest for loop
                if (x[i - 1][j] > x[i][j + 1]) { 
                    //First Loop:
                    // First eval (x[0][0] > x[1][1] => 3>5) false
                    //Second eval (x[0][1]>x[1][2] => 1>2, false)
                    //Third eval (x[0][2]>x[0][3] => 4>2, true!)
                    //Second Loop
                    //First eval (x[1][0]>x[2][1] => 1>4, fasle)
                    //Second eval (x[1][1]>x[2][2] => 5>1, true!)
                    //Third eval (x[1][2]>x[1][3] => 2>3, false)
                    //Third Loop (last loop):
                    //First eval (x[2][0]>x[3][1] => 2>2, fasle)
                    //Second eval (x[2][1]>x[3][2] => 4>3, true!)
                    //Third eval (x[2][2]>x[3][3] => 1>4, false)
                    System.out.println("x[i]:" + i + " x[j]:" + j);
                    //First Loop: i=1 j =2
                    //Second Loop:i=2 j=1
                    //Thrid Loop: i=3 j=1
                    x[i - 1][j + 1] = 0; //First loop: x[0][3]=0
                    //Second Loop:x[1][2]=0
                    //Third Loop:x[2][2]=0
                }
            }
        }
        out.println("Printing out multidimensional array:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("x[" + i + "][" + j + "]=" + x[i][j]+"\n");
            }
            System.out.println();
        }
    }
}
