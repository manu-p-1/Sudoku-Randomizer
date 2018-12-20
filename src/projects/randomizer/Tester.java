package projects.randomizer;

import java.util.Arrays;

/**
* A simple class to demonstrate the SudokuRandomizer class
*/
public class Tester {

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


	static void print(Integer[][] array) {
		System.out.println(Arrays.deepToString(array)
				.replace("], ", "]\n")
				.replace("[[", "[")
				.replace("]]", "]"));
	}
}
