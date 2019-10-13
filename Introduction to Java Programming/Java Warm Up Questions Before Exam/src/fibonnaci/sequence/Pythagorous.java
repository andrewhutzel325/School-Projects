package fibonnaci.sequence;

import java.util.Scanner;

class Pythagorous {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("A: ");
        double a = input.nextDouble();
        System.out.print("B: ");
        double b = input.nextDouble();
        System.out.print("C: ");
        double c = input.nextDouble();

        System.out.println();
        Pyth(a, b, c);
    }

    static void Pyth(double a, double b, double c) {
        double tempA, tempB, tempC;
        tempA = a;
        tempB = b;
        tempC = c;
        tempA = (b * b) + (c * c);
        System.out.println("A= sqrt(" + tempA + ")");
        tempB = (a * a) + (c * c);
        System.out.println("B= sqrt(" + tempB + ")");
        tempC = (b * b) + (a * a);
        System.out.println("C= sqrt(" + tempC + ")");
    }

}
