package page.replacement;

import java.util.LinkedList;

/**
 * 
 * @author Colin Quinn
 * 
 * This class implements the Least Recently Used (LRU) page replacement
 * algorithm.  This means that the page in the page table that has gone
 * the longest without being requested is replaced on a page fault.
 *
 */
public class LRU {
	/**
	 * RunsLRU and returns the number of page faults
	 * 
	 * @param: tableCapacity: size of page table
	 * @param: requests: page reference string
	 * @return number of page faults
	 */
	public static int run(int tableCapacity, int[] requests) {
		
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int pageFaults = 0;
		boolean found = false;
		int i;
		
		// initialize with empty values
		for (i = 0; i < tableCapacity; i++) {
			stack.add(-1);
		}
		
		// run LRU
		for (i = 0; i < requests.length; i++) {
			
			found = stack.contains( Integer.valueOf( requests[i] ) );
			// hit (Integer object calls right version of remove function)
			if (found) {
				// Put most recently used page on top of stack
				stack.remove( Integer.valueOf( requests[i] ) );
				stack.add( requests[i] );
			}
			// miss
			else {
				pageFaults++;
				// Removes bottom of stack
				stack.remove();
				stack.add( requests[i] );
			}
		}
		return pageFaults;
	}

}
