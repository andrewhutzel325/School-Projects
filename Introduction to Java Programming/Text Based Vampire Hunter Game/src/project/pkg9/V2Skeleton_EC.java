package project.pkg9.pkg5;

import java.util.Scanner;

/*
 * File: Project 9 EC
 * By: Andrew Hutzel and Banks Piccinini
 * Date: November 2nd, 2015
 * Compile: Compiled in Netbeans 8.0.2
 * Usage: Netbeans 8.0.2
 * Description: Place a vampire and a human on a 10x10 grid, the vampire will now
 * hunt the human until he is dead or he suicides. Emphasizes usuage of objects
 * in these projects. In the EC, which this is it, add a hunter into the mix.
 */
public class V2Skeleton_EC {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] grid = new char[10][10];

        // initialize vampire, take input
        System.out.print("Enter (i, j) for vampire: ");
        int newI = input.nextInt();
        int newJ = input.nextInt();
        Creature vampire = new Creature('V', newI, newJ);

        // initialize hunter, take input
        System.out.print("Enter (i, j) for hunter: ");
        int I = input.nextInt();
        int J = input.nextInt();
        Creature hunter = new Creature('X', newI, newJ);

        // initialize human, take input
        System.out.print("Enter (i, j) for human: ");
        int I2 = input.nextInt();
        int J2 = input.nextInt();
        Creature human = new Creature('H', newI, newJ);

        // check whether hunter and human can move
        System.out.print("Would you like hunter/human to move? (0: no, 1: yes): ");
        int isMove = input.nextInt();
        hunter.setMoving(isMove);
        human.setMoving(isMove);
        // update and display grid
        clearGrid(grid);
        // set vampire, human, and hunter coords
        vampire.setIJ(newI, newJ);
        hunter.setIJ(I, J);
        human.setIJ(I2, J2);
        //Display hunter, human, and vampire on grid
        vampire.display(grid);
        hunter.display(grid);
        human.display(grid);
        //Draw the grid now
        drawGrid(grid);
        //Display the x,y coord associated w/ vampire, human, and hunter
        System.out.println("Vampire at: " + vampire.getI()
                + " " + vampire.getJ());
        System.out.println("Hunter at: " + hunter.getI()
                + " " + hunter.getJ());
        System.out.println("Human at: " + human.getI()
                + " " + human.getJ());

        // get next user command
        System.out.print("Enter command (0 to quit): ");
        int command = input.nextInt();

        while (command != 0) { // while not 0 do not quit
            //Initialize grid again
            clearGrid(grid);
            //Take user command
            vampire.update(command);
            //If the vampire is within the hunter's reach already, end game
            if (sameSquare(vampire, hunter) == true) {
                System.out.println("The hunter has killed the vampire!");
                break;
            }
            //If the vampire was placed on the same square as the human end it
            if (sameSquare2(vampire, human) == true) {
                System.out.println("Vampire has bitten the human!");
                break;
            }
            //Now that neither of those are true, update/move the players
            hunter.update();
            human.update();
            //If after moving the human runs into the vampire, end game
            if (sameSquare2(vampire, human) == true) {
                System.out.println("The human has sacrificed himself!");
                break;
            }
            //If the vampire throws himself into the grid of the hunter, end game
            if (sameSquare(vampire, hunter) == true) {
                System.out.println("The vampire has sacrifed himself!");
                break;
            }
            //Display all location of players
            hunter.display(grid);
            vampire.display(grid);
            human.display(grid);

            // if vampire and hunter are on same square,
            // vampire bites hunter, game ends
            // if game does not end
            // hunter makes random move
            // display hunter on grid
            // if vampire and hunter are on same square,
            // hunter sacrificed himself, game ends
            //Draw grid
            drawGrid(grid);
            //Output x and y coords associated to all players
            System.out.println("Vampire at: " + vampire.getI()
                    + " " + vampire.getJ());
            System.out.println("Hunter at: " + hunter.getI()
                    + " " + hunter.getJ());
            System.out.println("Human at: " + human.getI()
                    + " " + human.getJ());
            //Take user input
            System.out.print("Enter command (0 to quit): ");
            command = input.nextInt();

        } // while (command != 0)

    }

    public static void clearGrid(char[][] g) {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                g[i][j] = '.';
            }
        }
    }

    public static void drawGrid(char[][] g) {
        System.out.println("0123456789");
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                System.out.print(g[i][j]);
            }
            System.out.println(i);
        }
    }

    public static boolean sameSquare2(Creature a1, Creature a2) {
        //If on same coord, return true
        if (a1.getI() == a2.getI() && a1.getJ() == a2.getJ()) {
            return true;
        } // else return false
        else {
            return false;
        }
    }

    public static boolean sameSquare(Creature c1, Creature c2) {
        // if c1 and c2 have identical (i, j) coordinates, return true
        //Analyze the eight indexes around the hunter!
        //1
        if (c1.getI() == c2.getI() && c1.getJ() - 1 == c2.getJ()) {
            return true;
        } // else return false
        //2
        else if (c1.getI() == c2.getI() && c1.getJ() + 1 == c2.getJ()) {
            return true;
        } //3
        else if (c1.getI() - 1 == c2.getI() && c1.getJ() == c2.getJ()) {
            return true;
        } //4
        else if (c1.getI() + 1 == c2.getI() && c1.getJ() == c2.getJ()) {
            return true;
        } //5
        else if (c1.getI() - 1 == c2.getI() && c1.getJ() - 1 == c2.getJ()) {
            return true;
        } //6
        else if (c1.getI() - 1 == c2.getI() && c1.getJ() + 1 == c2.getJ()) {
            return true;
        } //7
        else if (c1.getI() + 1 == c2.getI() && c1.getJ() - 1 == c2.getJ()) {
            return true;
        } //8
        else if (c1.getI() + 1 == c2.getI() && c1.getJ() + 1 == c2.getJ()) {
            return true;
        } else {
            return false;
        }

    }

}

class Creature {

    // display character for creature
    private char pic;
    // (i, j) coordinates for creature
    private int i = 0;
    private int j = 0;
    private boolean canMove = true; // can creature move?

    Creature(char c, int nI, int nJ) {
        // set display character to c
        pic = c;
        // set position to (nI, nJ)
        setIJ(i, j);
    }

    public void setIJ(int nI, int nJ) {
        // set (i, j) coordinates for creature
        // if new coordinates are invalid, leave current position unchanged
        if (nI < 10 && nI >= 0) {
            i = nI;
        }
        if (nJ < 10 && nJ >= 0) {
            j = nJ;
        }
    }

    public void setMoving(int n) {
        if (n == 0) {
            canMove = false; // not moving
        } else {
            canMove = true; // moving
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void update() {
        // random position update; call update with random argument 1-4
        int c = 0;
        c = ((int) ((Math.random() * ((4 - 1) + 1)) + 1)); // Value 1 to 4
        if (canMove != false) {
            if (c == 1) {
                if (j > 0) {
                    j--;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }

            // 2: i++ (down)
            if (c == 2) {
                if (i + 1 < 10) {
                    i++;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
            // 3: i-- (up)
            if (c == 3) {
                if (i > 0) {
                    i--;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
            // 4: j++ (right)
            if (c == 4) {
                if (j + 1 < 10) {
                    j++;
                } else {
                    System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                    return;
                }
            }
        }
    }

    public void update(int c) {
        // if canMove, update position according to user command c
        // 1: j-- (left)
        //Always moves unless value isn't 1 2 3 or 4
        if (c == 1) {
            if (j > 0) {
                j--;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }

        // 2: i++ (down)
        if (c == 2) {
            if (i + 1 < 10) {
                i++;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }
        // 3: i-- (up)
        if (c == 3) {
            if (i > 0) {
                i--;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
                return;
            }
        }
        // 4: j++ (right)
        if (c == 4) {
            if (j + 1 < 10) {
                j++;
            } else {
                System.out.println("Invalid position change; position set to(" + i + ", " + j + ")");
            }
        }

    }

    public void display(char[][] g) {
        g[i][j] = pic;
    }

}
