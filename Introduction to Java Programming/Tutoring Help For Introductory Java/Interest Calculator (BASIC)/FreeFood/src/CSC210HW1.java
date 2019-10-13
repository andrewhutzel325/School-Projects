//We are using Scanner class to scan values, package is imported below this line
//The * portion of this import means I'm importing EVERYTHING from the package.
import java.util.*;




public class CSC210HW1 {
    public static void main(String[] args) {
        //So we want to take user input. To do this we need to create a SCANNER object from the imported package above!
    	//https://stackoverflow.com/questions/30612811/what-does-scanner-input-new-scannersystem-in-actually-mean/30613358
    	//Read that stack overflow link if you want to understand what Scanner is in better detail
    	Scanner in = new Scanner(System.in);
    	//We've now created a scanner object, but we are not going to use that just yet!
    	//We are going to declare our variables! Below I've declared four float values and one integer. Why floats? Because they allow for decimals values.
    	//Why integers? Because integers only allow for WHOLE numbers and not decimals. Its all dependent upon precision and the professor's instructions.
    	float total=0,year_int=0,tmp=0,income_int=0;
    	int deposited=0;
    	//Now that we have our scanner object AND variables lets start to match your professor's output and make the output statements!
    	//Your professor actually made me laugh out loud, he's using println instead of print and the output is all ghetto.
    	//Remember to ALWAYS match your professor's output regardless of how silly it looks.
    	
    	//Here is our first output to the console. We're looking for the amount of deposited this year.
    	System.out.println("Enter the amount deposited this year:");
    	//Now that your console has a prompt for what you need to enter, here comes your scanner object!
    	//We want to use scanner to take user's input and place it into the variables we declared above, like below.
    	//NOTE, we're using the object in to take a nextINT <--- You will need to take in's object and search for the function that matches
    	//the data type you're using. In this case this variable is an integer so you take a user's INTEGER value. If the user enters a decimal
    	//you will get an integer. Try it out if you'd like! Add print statements and see how the output is changed.
    	deposited=in.nextInt();
    	//Same thing for the next part, match the data type with what you enter
    	System.out.println("Enter the yearly interest rate:");
    	year_int=in.nextFloat();
    	System.out.println("Enter the income interest rte:");
    	income_int=in.nextFloat();
    	//Meat of the program is located here, this is where we COMPUTE the total.
    	//Remember all of the variables stored the values from above so lets begin our manipulation
    	tmp=(deposited*(income_int/100));
    	total=(deposited-tmp)*(year_int/100);
    	System.out.print("The amount of interest earned in the year is "+total+"\n");
    }
}
