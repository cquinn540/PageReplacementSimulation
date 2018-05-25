package page.replacement;

import java.util.LinkedList;

/**
 * 
 * @author Colin Quinn
 * 
 * This class implements the First In First Out (FIFO) page replacement
 * algorithm.  This means that the page that has been in the page table
 * the longest is replaced on a page fault.
 *
 */
public class FIFO {
	/**
	 * Runs FIFO and returns the number of page faults
	 * 
	 * @param: tableCapacity: size of page table
	 * @param: requests: page reference string
	 * @return number of page faults
	 */
	public static int run(int tableCapacity, int[]requests) {
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int pageFaults = 0;
		int i;
		
		// initialize with empty values
		for (i = 0; i < tableCapacity; i++) {
			queue.add(-1);
		}
		
		// run FIFO
		for (i = 0; i < requests.length; i++) {
			// page table miss
			if ( !queue.contains( requests[i] ) ) {
				pageFaults++;
				// First in, first out
				queue.remove();
				queue.add( requests[i] );
			}
		}
		return pageFaults;
	}
}
