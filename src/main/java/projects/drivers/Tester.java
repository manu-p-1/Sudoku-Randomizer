package projects.drivers;

import java.util.Arrays;

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

		Integer[][] premade = {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 4, 5, 6, 7, 8, 9, 1, 2, 3 },
				{ 7, 8, 9, 1, 2, 3, 4, 5, 6 },
				{ 2, 3, 4, 5, 6, 7, 8, 9, 1 },
				{ 5, 6, 7, 8, 9, 1, 2, 3, 4 },
				{ 8, 9, 1, 2, 3, 4, 5, 6, 7 },
				{ 3, 4, 5, 6, 7, 8, 9, 1, 2 },
				{ 6, 7, 8, 9, 1, 2, 3, 4, 5 },
				{ 9, 1, 2, 3, 4, 5, 6, 7, 8 } };

		print(premade);
		SudokuRandomizer.swapRowInGroup(premade);
		System.out.println();
		System.out.println();
		print(premade);
		SudokuRandomizer.swapGroupAsColumn(premade);
		System.out.println();
		System.out.println();
		print(premade);
	}

	/**
	 * Prints a 2d array with some visual enhancements.
	 * 
	 * @param array the array to be printed
	 */
	static void print(Integer[][] array) {
		System.out.println(Arrays.deepToString(array)
				.replace("], ", "]\n")
				.replace("[[", "[")
				.replace("]]", "]"));
	}
}
