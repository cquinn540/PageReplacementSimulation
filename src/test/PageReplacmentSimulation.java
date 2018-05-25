package test;

import java.io.*;
import java.util.Random;

import page.replacement.*;

/**
 * 
 * @author Colin Quinn
 * 
 * Assignment 4 - Page Replacement
 * Date: 04/19/2018
 * 
 * This class compares the First In First Out (FIFO), and Least Recently
 * Used (LRU) page replacement algorithms against an optimal one (OPT) as
 * a upper limit on performance.
 * 
 * It runs each algorithm on three page request example strings, using page
 * table sizes of 1 through 7, and then records the number of page faults.
 * The first string is randomly generated
 *
 */
public class PageReplacmentSimulation {
	/**
	 * Generates page strings, runs the algorithms, and outputs the results
	 * to "Asg4Data_out.txt"
	 */
	public static void main(String[] args) {
		
		int[] array1 = new int[20];
		Random rand = new Random();
		
		// Initialize random array
		for (int i = 0; i < array1.length; i++) {
			// Generates random number 0 through 9
			array1[i] = rand.nextInt(10);
		}
		
		int[] array2 = {0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2};
		int[] array3 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
		
		// Writes output
		try {
			FileWriter writer = new FileWriter("Asg4Data_out.txt");
			writer.write("Colin Quinn\n" + "ICS 462 Assignment #4\n\n");
			
			runTests(7, array1, writer);
			runTests(7, array2, writer);
			runTests(7, array3, writer);
			
			writer.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * Runs each test for a number of page table sizes and writes output
	 * to file
	 * 
	 * @param pages: tests page table sizes 1 through pages
	 * @param input: integer array containing page reference string
	 * @param writer: File Writer object used to write output
	 */
	public static void runTests(int pages, int[] input, FileWriter writer) {
		
		int fifo;
		int lru;
		int opt;
		int i;
		String pageReferenceString = "page reference string: ";
		
		// Make page reference string
		for (i = 0; i < input.length - 1; i++) {
			pageReferenceString += input[i] + ",";
		}
		// Get rid of last comma
		pageReferenceString += input[i];
		
		try {
			writer.write("\n------------- " + pageReferenceString + " -------------\n\n");
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		// Runs each algorithm on reference string for each number of pages
		for (i = 1; i <= pages; i++) {
			
			fifo = FIFO.run(i, input);
			lru = LRU.run(i, input);
			opt = OPT.run(i, input);
			
			try {
				writer.write(
					"For " + i + " page frames, and using " + pageReferenceString + "\n\n"
					+ "FIFO had " + fifo + " page faults.\n"
					+ "LRU had " + lru + " page faults.\n"
					+ "OPT had " + opt + " page faults.\n\n\n"
				);
			} catch (IOException io) {
				io.printStackTrace();
			}
		} // End for
	} // End runTests
}
