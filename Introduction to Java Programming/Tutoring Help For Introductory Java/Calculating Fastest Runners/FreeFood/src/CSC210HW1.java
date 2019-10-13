
//We are using Scanner class to scan values, package is imported below this line
//The * portion of this import means I'm importing EVERYTHING from the package.
import java.util.*;

public class CSC210HW1 {

	public static int val_IDX = -1;

	public static void main(String[] args) {

		String[] studentNames = { "Emily", "Simon", "Paulo", "Aaron", "Kate", "Cindy", "Ryan", "James", "Michael",
				"Sara", "Joseph", "Juan", "Brad" };
		int[] times2017 = { 357, 299, 432, 326, 275, 450, 265, 343, 264, 308, 242, 377, 273 };
		int[] times2018 = { 341, 307, 328, 283, 274, 359, 256, 343, 269, 308, 249, 340, 238 };
		int[] time_improvements = new int[times2017.length];
		int time_temp = time_improvements[0], time_tempIDX = 0;

		// Write your code to call the method here

		System.out.println("The fastest runners in 2017 and their time in minutes are ");
		// System.out.println("\n Names BEFORE 2017: " + Arrays.toString(times2017) +
		// "\n");
		// System.out.println("\n Names BEFORE 2017: " + Arrays.toString(studentNames) +
		// "\n");
		fastestRunner(times2017, studentNames);
		// System.out.println("\n Names AFTER 2017: " + Arrays.toString(times2017) +
		// "\n");
		// System.out.println("\n Names BEFORE 2017: " + Arrays.toString(studentNames) +
		// "\n");

		System.out.println("\nThe fastest runners in 2018 and their time in minutes are ");
		// System.out.println("\n Names 2018: " + Arrays.toString(times2018) + "\n");
		fastestRunner(times2018, studentNames);
		// System.out.println("\n");

		System.out.println("\nThe list of students who improved their time from 2017 to 2018 are:");

		time_improvements = compareTimes(times2017, times2018, studentNames);

		for (int i = 0; i < time_improvements.length; i++) {
			if (time_improvements[i] > 0) {
				System.out.println(studentNames[i] + ":" + time_improvements[i] + " minutes");
			}
		}
		System.out.println("\nThe list of students who hindered their time from 2017 to 2018 are:");
		for (int i = 0; i < time_improvements.length; i++) {
			if (time_improvements[i] <= 0) {
				System.out.println(studentNames[i] + ":" + time_improvements[i] + " minutes");
			}
		}

		// Obligatory paulo is a tenacious runner????
		for (int i = 0; i < time_improvements.length; i++) {
			if (time_temp < time_improvements[i]) {
				time_temp = time_improvements[i];
				time_tempIDX = i;
			}
		}

		System.out.println("\n" + studentNames[time_tempIDX] + " is a tenacious runner");

	}
	// Write your methods here

	static int[] compareTimes(int times2017[], int times2018[], String names[]) {

		int time_storage[] = new int[times2017.length];
		// System.out.println("\n2017: " +Arrays.toString(times2017)+"\n");
		// System.out.println("\n2018: "+Arrays.toString(times2018)+"\n");
		// System.out.println("\n Names: "+Arrays.toString(names)+"\n");
		for (int i = 0; i < times2017.length; i++)
			time_storage[i] = times2017[i] - times2018[i];
		// System.out.println("\n"+Arrays.toString(time_storage)+"\n");
		return time_storage;
	}

	static void swapFirst(int[] times, String names[]) {
		int temp = times[0], temp_idx = -1;
		String temp_str = names[0];
		for (int i = 0; i < times.length; i++) {
			if (temp > times[i]) {
				temp = times[i];
				temp_idx = i;
			}
		}

		// We have found the fastest running for the year, now we swap positions with
		// the LOWEST person! times[0]
		if (val_IDX == -1) {
			// Value first
			temp = times[0];
			times[0] = times[temp_idx];
			times[temp_idx] = temp;

			// String next
			names[0] = names[temp_idx];
			names[temp_idx] = temp_str;
			val_IDX = temp_idx;
		} else {

			temp = times[0];
			times[0] = times[val_IDX];
			times[val_IDX] = temp;

			// Value first
			temp = times[0];
			times[0] = times[temp_idx];
			times[temp_idx] = temp;

			// String next
			names[0] = names[temp_idx];
			names[temp_idx] = temp_str;
		}
	}

	static void fastestRunner(int times[], String names[]) {
		// EXPLANATION (Loop 1):
		// This code is pretty publicly available, I pulled up the first one and just
		// went from there.
		// So we loop through the first time and find the SMALLEST value in the array.
		// We store the index in first_index, because we will need that later.
		// https://www.geeksforgeeks.org/arrays-in-java/

		// Declare indexes and values
		int first = times[0], second = times[0], third = times[0];
		int first_index = 0, second_index = 0, third_index = 0, temp = 0;

		// Sorting method call here
		// System.out.println("BEFORE NAME SORT");
		// System.out.println(Arrays.toString(names));
		// System.out.println(Arrays.toString(times));
		swapFirst(times, names);
		// System.out.println("\nAFTER NAME SORT");
		// System.out.println(Arrays.toString(names));
		// System.out.println(Arrays.toString(times));
		// END Sorting method call here

		// Clone times, we will manipulate
		int temp_times[];
		temp_times = times.clone();

		// FastestRunner
		for (int i = 1; i < temp_times.length; i++) {
			for (int j = i; j > 0; j--) {
				if (temp_times[j] < temp_times[j - 1]) {
					temp = temp_times[j];
					temp_times[j] = temp_times[j - 1];
					temp_times[j - 1] = temp;
				}
			}
		}

		first = temp_times[0];
		second = temp_times[1];
		third = temp_times[2];

		// Finds index
		for (int i = 0; i < times.length; i++) {
			if (first == times[i]) {
				first_index = i;
			}
			if (second == times[i]) {
				second_index = i;
			}
			if (third == times[i]) {
				third_index = i;
			}
		}
		// System.out.println("Index #2 "+ second_index);
		System.out.println(names[first_index] + ":" + times[first_index]);
		System.out.println(names[second_index] + ":" + times[second_index]);
		System.out.println(names[third_index] + ":" + times[third_index]);
		// End Fastest Runner
	}

}
