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
public class FinalQ2 {
    public static void main(String [] args) {
        Pet dog = new Pet("Fido", 2); // Creating an Object of Pet class!
        Pet cat = new Pet("Fritz", 3); // Creating an object of Pet class!
        System.out.println(Pet.count); 
        //Every time you create an object of Pet it will increment the STATIC
        //value of count up by one! Thats why it prints out the value two, you 
        //created two objects of Pet!
        // Calling class Pet's count value and asking it to be 
        //printed in main! Note the difference between public vs private, if its
        //private you would not be able to reference the variables outside of the class
        //but because the variables are not declared as private you can freely 
        //reference them from anywhere in your program!

        System.out.println(dog.name); // Again, calling Pet OBJECT dog's variable name and printing
        //it out in main! Private vs public is really important!
        dog = cat; // Assigning all given values of cat to dog. These objects are 
        //now linked meaning they share common values. If one of them is changed,
        //the other one will change as well!
        cat.age = 5; // Assign the value five to cat object's age.
        System.out.println(dog.name + " " + dog.age); // Output: Fritz and 5
        //You will print out Fritz and 5, why? Because the objects are linked,
        //when cat was passed to dog it meant that both object's values are linked.
    }
}

class Pet {
    String name;
    int age;
    static int count = 0;

    Pet(String newName, int newAge) {
        name = new String(newName);
        age = newAge;
        count++;
    }
}


