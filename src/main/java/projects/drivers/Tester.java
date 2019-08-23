package projects.drivers;

import projects.randomizer.SudokuRandomizer;

/**
* A simple class to demonstrate the SudokuRandomizer class.
*/
public class Tester {

	/**
	 * Main method contains items to test.
	 *
	 * @param args arguments to be used
	 */
	public static void main(String[] args) {

		final Integer[][] premade = {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 },
				{ 7, 8, 9, 1, 2, 3, 4, 5, 6 },
				{ 2, 3, 4, 5, 6, 7, 8, 9, 1 },
				{ 5, 6, 7, 8, 9, 1, 2, 3, 4 },
				{ 8, 9, 1, 2, 3, 4, 5, 6, 7 },
				{ 3, 4, 5, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 8, 9, 1, 2, 3, 4, 5 },
				{ 9, 1, 2, 3, 4, 5, 6, 7, 8 } };
		
		/*
		 * Returning "this" allows room for additional functionality
		 */
		SudokuRandomizer.print(new SudokuRandomizer<Integer>(premade)
				.swapRowInGroup()
				.swapColumnInGroup()
				.swapGroupAsRow()
				.swapGroupAsColumn()
				.transpose()
				.get());
	}
}
