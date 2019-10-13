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
*/ // Selection Sort

public class SelSort {
    public static void main(String[] args) {
	final int SIZE = 5;
	int [] x = {89, 2, -13, 67, 0};

	for (int i=0; i<x.length; i++) {
	    // find smallest element in elements i to x.length
	    int currentMin = x[i];
            out.println("x["+i+"]="+x[i]);
	    int minIndex = i;

	    for (int j=i+1; j < x.length; j++) {
		if (x[j] < currentMin) {
                    out.println("x["+j+"]="+x[j]);
		    currentMin = x[j];
		    minIndex = j;
		}
	    }
	    // swap smallest element with element i
	    if (minIndex != i) {
                out.println("x["+i+"]="+x[i]);
                out.println("minIndex="+minIndex);
                out.println("currentMin="+currentMin);
		x[minIndex] = x[i];
                out.println("x["+i+"] now equals x["+minIndex+"]="+x[minIndex]);
		x[i] = currentMin;
                out.println("x["+i+"] now equals currentMin="+x[i]);
	    }
	}

	System.out.println("Sorted array: ");
	for (int i=0; i<x.length; i++) {
	    System.out.println(x[i]);
	}
	
    }
}