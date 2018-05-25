package page.replacement;

import java.util.LinkedList;

/**
 * 
 * @author Colin Quinn
 * 
 * This class implements the optimal (OPT) page replacement algorithm.
 * This algorithm requires future knowledge of the page reference string,
 * which is generally not available in an actual operating system.  It is
 * used as an upper limit on performance that other algorithms may be
 * compared against.
 * 
 * This algorithm searches the page reference string for a page in the page
 * in the page table that goes the longest without being requested again,
 * or a page that is never requested again.
 *
 */
public class OPT {
	/**
	 * Runs OPT and returns the number of page faults
	 * 
	 * @param: tableCapacity: size of page table
	 * @param: requests: page reference string
	 * @return number of page faults
	 */
	public static int run(int pages, int requests[]) {
		
		LinkedList<Integer> pageTable = new LinkedList<Integer>();
		
		int pageFaults = 0;
		int i;
		
		// initialize with empty values
		for (i = 0; i < pages; i++) {
			pageTable.add(-1);
		}
		// Run optimal page replacement
		for (i = 0; i < requests.length; i++) {
			// page table miss
			if ( !pageTable.contains( requests[i] ) ) {
				// Check for empty spot
				if ( pageTable.contains(-1) ) {
					pageTable.remove( Integer.valueOf(-1) );
				}
				// If not empty spot, look for optimal replacement
				else {
					int optimalPageIndex = -1;
					int optimalPage = 0;
					
					// Check each page number to see which is used last (greatest index)
					for (int target : pageTable) {
						// Find earliest index of each page
						int firstIndex = findFirstIndex(target, i, requests);
						// If not in list it is the optimal page
						if (firstIndex == -1) {
							optimalPage = target;
							break;
						}
						// Max of earliest pages is optimal page
						if (firstIndex > optimalPageIndex) {
							optimalPageIndex = firstIndex;
							optimalPage = target;
						}
					} // End for
					pageTable.remove( Integer.valueOf(optimalPage) );
				} // End optimal replacement
				pageTable.add( requests[i] );
				pageFaults++;
			} // End page table miss 
		} // End for
		
		return pageFaults;
	}
	/**
	 * Finds the first index in the page reference string at which reference the target page.
	 * Search is started from the index passed as a parameter.
	 * 
	 * @param target: value to be located
	 * @param index: index of start of search
	 * @param array: array to be searched
	 * @return index of first index of target, -1 if target not found
	 */
	private static int findFirstIndex(int target, int index, int[] array) {
		for (int i = index; i < array.length; i++) {
			if (array[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
