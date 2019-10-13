package project10;
import static java.lang.System.out;
import java.util.Scanner;

/*
 * File: Project10
 * By: Andrew Hutzel and Banks Piccinini
 * Date: December 7th, 2015
 * Compile: Compiled in Netbeans 8.0.2
 * Usage: Netbeans 8.0.2
 * Description: Output a menu, have the user select the items they want, and then
 * when they leave output the total cost. That is all this program does.
 */
public class Project10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Create input object for scanner
        Scanner input = new Scanner(System.in);
        //Create integers choices for switch
        int choice = 0, choice2=0;
        //Create object of cart and 5 objects of Item associates w/ products
        Cart newCart = new Cart();
        //System.out.println(newCart.getTotal());
        Item[] availableItems = new Item[5];
        Item eggs = new Item("eggs", 2.99);
        availableItems[0] = eggs;
        Item milk = new Item("milk", 3.99);
        availableItems[1] = milk;
        Item broccoli = new Item("broccoli", 2.75);
        availableItems[2] = broccoli;
        Item bread = new Item("bread", 3.25);
        availableItems[3] = bread;
        Item apple = new Item("apple", 1.25);
        availableItems[4] = apple;
        //Welcome User at beginning of program
        System.out.println("Welcome to the virtual grocery store!");
        //Create an infinite loop, break when user input's 4.
        for (;;) {
            //Prompt user with options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1: View available items");
            System.out.println("2: View your cart");
            System.out.println("3: Add an item to your cart");
            System.out.println("4: Check out");
            System.out.print("Enter your choice:");
            //Take choice
            choice = input.nextInt();
            for(int i=0;i<30;i++)
                out.println();
            //Use choice for cases in the switch.
            switch (choice) {
                case 1: {
                    //Output Line for proper spacing
                    out.println();
                    System.out.println("Available Items:");
                    //Outputs available Items created in main
                    for (int i = 0; i < 5; i++) {
                        System.out.print(i + ": ");
                        availableItems[i].print();
                    }
                    break;
                }
                case 2: {
                    //Output Line for proper spacing
                    out.println();
                    //Prints out cart's items
                    newCart.printItems();
                    //Prints out cart's item's total
                    System.out.print("Total cost: $" + newCart.getTotal());
                    out.println();
                    break;
                }
                case 3: {
                    //Output Line for proper spacing
                    out.println();
                    System.out.print("What item would you like to add?:");
                    //Take User input, again.
                    choice2=input.nextInt();
                    //Apply user's choice to cart
                    newCart.addItem(availableItems[choice2]);
                    break;
                }
                case 4:
                {
                    //Acquire cart's total and output it before leaving
                    out.println("That will be $"+newCart.getTotal()+"\nThank you.");
                    break;
                }

            }
            if (choice == 4) {
                //Simple break from never ending for loop.
                break;
            }
            
        }
    }

    static class Item {
        //Item's private variables
        private String name;
        private double price;
        //Declare a Item constuctor
         Item(String newName, double newPrice) {
            name = newName;
            price = newPrice;
        }
         Item()
         {
             
         }
        //Print Item's Variables
        public void print() {
            System.out.println(name + ": $" + price);
        }
        //Return Item's varuable price
        public double getPrice() {
            return price;
        }
    }
    //Declare a Cart class
    static class Cart {
        //Declare Cart's variables
        private Item[] items;
        private int numItems;
        //Declare a Cart constructor
        Cart() {
            items = new Item[100];
            numItems = 0;
        }
        //Declare an addItem feature to cart
        public void addItem(Item item) {
            //Because items [100] is an object of Item, you apply numItems to
            //increase the array value associated with each item in items and
            //then you increase numItems to reference the next array slot.
            items[numItems] = item;
            numItems += 1;
        }

        public void printItems() {
            if (numItems == 0) { // Put simply, nothing in cart, output empty.
                System.out.println("The cart is currently empty.");
            } else { // There is something in the cart
                System.out.print("Your cart:\n");
                //Output all the items in the cart
                for (int i = 0; i < items.length; i++) {
                    //Simple if statement, since items[100] will likely never
                    //really be filled and we scan EVERY slot in items, its smart
                    //to skip all null values and only return the slots that do
                    //have something in them
                    if(items[i]==null)
                        break;
                    else
                        items[i].print();
                }
            }
        }
        //Create getTotal so it can return cart's value
        public double getTotal() {
            //Declare a double to return proper values.
            double total = 0;
            //Scan items in the cart
            for (int i = 0; i < items.length; i++) {
                //If null, don't check it
                if (items[i] == null) { 
                    break;
                } else {
                    //Since object does exist, check it and get the price
                    total += items[i].getPrice();
                }
            }
            //Retrun double total.
            return total;
        }

    }

}
