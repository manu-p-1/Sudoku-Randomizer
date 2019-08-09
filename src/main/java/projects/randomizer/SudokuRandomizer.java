package projects.randomizer;
import java.util.Random;

/**
 * A class designed to facilitate the algorithm being used in the program. There
 * are four types manipulation used in this program to randomize the board while
 * still maintaining the integrity of the board. The first method used is
 * randomly swapping rows within a certain three by three sub region. The second
 * method involves randomly swapping columns within a certain three by three sub
 * region. The third and fourth method allows for randomly swapping three
 * adjacent sub regions at a time (by row or by column).
 *
 *
 * @author Manu Puduvalli
 *
 */
public class SudokuRandomizer {
	/**
	 * Swaps two random rows within every three by three sub region. Rather than
	 * swapping every row within every single sub region, The rows are swapped as a
	 * group. This is done by randomly choosing two rows from index 0 to 2, from
	 * index 3 to 5, and from index 6 to 8 and swapping them within the confines of
	 * its sub region.
	 *
	 * @param <T> the type of element used
	 * @param  premade the 2D array to be used for swapping
	 * @return premade the 2D array type
	 */
	public static <T extends Comparable<T>> T[][] swapRowInGroup(final T[][] premade) {
		Random rng = new Random();
		Container<T>[] rowTemp = Container.<T>array(9);
		int randRow1 = 0; // The first row to be swapped
		int randRow2 = 0; // The second row to be swapped
		// Moves every third row to make sure we stick to the same group
		for (int row = 0; row < premade.length; row += 3) {
			if (row <= 2) {
				randRow1 = rng.nextInt(3);
				randRow2 = rng.nextInt(3);
			} else if (row >= 3 && row <= 5) {
				randRow1 = rng.nextInt(3) + 3;
				randRow2 = rng.nextInt(3) + 3;
			} else {
				randRow1 = rng.nextInt(3) + 6;
				randRow2 = rng.nextInt(3) + 6;
			}
			// Swap loop
			for (int col = 0; col < premade[randRow1].length; col++) {
				rowTemp[col] = new Container<T>(premade[randRow1][col]);
				premade[randRow1][col] = premade[randRow2][col];
				premade[randRow2][col] = rowTemp[col].get();
			}
		}
		return premade;
	}

	/**
	 * Swaps two random columns within every three by three sub region. Rather than
	 * swapping every columns within every single sub region, The columns are
	 * swapped as a group. This is done by randomly choosing two columns from index
	 * 0 to 2, from index 3 to 5, and from index 6 to 8 and swapping them within the
	 * confines of its sub region.
	 *
	 * @param <T> the type of element used
	 * @param premade the 2D array to be used for swapping
	 * @return premade the 2D array type
	 */
	public static <T extends Comparable<T>> T[][] swapColumnInGroup(final T[][] premade) {
		Random rng = new Random();
		Container<T>[] colTemp = Container.<T>array(9);
		int randCol1 = 0; // The first row to be swapped
		int randCol2 = 0; // The second row to be swapped
		int columnRep = 0;
		// Moves every third column to make sure we stick to the same group
		for (columnRep = 0; columnRep < premade.length; columnRep += 3) {
			if (columnRep <= 2) {
				randCol1 = rng.nextInt(3);
				randCol2 = rng.nextInt(3);
			} else if (columnRep >= 3 && columnRep <= 5) {
				randCol1 = rng.nextInt(3) + 3;
				randCol2 = rng.nextInt(3) + 3;
			} else {
				randCol1 = rng.nextInt(3) + 6;
				randCol2 = rng.nextInt(3) + 6;
			}
			// Swap loop
			for (int row = 0; row < premade.length; row++) {
				colTemp[row] = new Container<T>(premade[row][randCol1]);
				premade[row][randCol1] = premade[row][randCol2];
				premade[row][randCol2] = colTemp[row].get();
			}
		}
		return premade;
	}

	/**
	 * Swaps two random groups of sub regions horizontally. A sub region group
	 * consists of all horizontally adjacent sub regions. Two random group values
	 * are generated. If the value falls between index 0 and index 2, this indicates
	 * group 1. If the value falls between index 3 and index 5, this indicates group
	 * 2. If the value falls between index 6 and index 8, this indicates group 3.
	 * Afterwards, each corresponding group is swapped.
	 *
	 * @param <T> the type of element used
	 * @param premade the 2D array to be used for swapping
	 * @return premade the 2D array type
	 */
	public static <T extends Comparable<T>> T[][] swapGroupAsRow(final T[][] premade) {
		Random rng = new Random();
		// A temporary array for the values in each group
		Container<T>[][] swapTemp = Container.<T>array(3,9);
		int randGroup1 = rng.nextInt(9); // The first group to be swapped
		int randGroup2 = rng.nextInt(9); // The second group to be swapped
		// index 0 marks the beginning of the first group
		if (randGroup1 <= 2) {randGroup1 = 0;}
		if (randGroup2 <= 2) {randGroup2 = 0;}
		// index 3 marks the beginning of the second group
		if (randGroup1 >= 3 && randGroup1 <= 5) {randGroup1 = 3;}
		if (randGroup2 >= 3 && randGroup2 <= 5) {randGroup2 = 3;}
		// index 6 marks the beginning of the third group
		if (randGroup1 >= 6) {randGroup1 = 6;}
		if (randGroup2 >= 6) {randGroup2 = 6;}
		// Puts the values of group 1 in swapTemp
		for (int row = randGroup1; row < randGroup1 + 3; row++) {
			for (int col = 0; col < premade[row].length; col++) {
				swapTemp[row - randGroup1][col] = new Container<T>(premade[row][col]);
			}
		}
		// Puts the values of group 2 where group 1 was
		int counter = randGroup2;
		for (int row = randGroup1; row < randGroup1 + 3; row++) {
			for (int col = 0; col < premade[row].length; col++) {
				premade[row][col] = premade[counter][col];
			}
			counter++;
		}
		// Puts the values of swapTemp where group 2 was
		for (int row = randGroup2; row < randGroup2 + 3; row++) {
			for (int col = 0; col < premade[row].length; col++) {
				premade[row][col] = swapTemp[row - randGroup2][col].get();
			}
		}
		return premade;
	}

	/**
	 * Swaps two random groups of sub regions vertically. A sub region group
	 * consists of all vertically adjacent sub regions. Two random group values are
	 * generated. If the value falls between index 0 and index 2, this indicates
	 * group 1. If the value falls between index 3 and index 5, this indicates group
	 * 2. If the value falls between index 6 and index 8, this indicates group 3.
	 * Afterwards, each corresponding group is swapped.
	 *
	 * @param <T> the type of element used
	 * @param premade the 2D array to be used for swapping
	 * @return premade the 2D array type
	 */
	public static <T extends Comparable<T>> T[][] swapGroupAsColumn(final T[][] premade) {
		Random rng = new Random();
		// A temporary array for the values in each group
		Container<T>[][] swapTemp = Container.<T>array(9,3);
		int randGroup1 = rng.nextInt(9); // The first group to be swapped
		int randGroup2 = rng.nextInt(9); // The second group to be swapped
		// index 0 marks the beginning of the first group
		if (randGroup1 <= 2) {randGroup1 = 0;}
		if (randGroup2 <= 2) {randGroup2 = 0;}
		// index 3 marks the beginning of the second group
		if (randGroup1 >= 3 && randGroup1 <= 5) {randGroup1 = 3;}
		if (randGroup2 >= 3 && randGroup2 <= 5) {randGroup2 = 3;}
		// index 6 marks the beginning of the third group
		if (randGroup1 >= 6) {randGroup1 = 6;}
		if (randGroup2 >= 6) {randGroup2 = 6;}
		// Puts the values of group 1 in swapTemp
		for (int row = 0; row < 9; row++) {
			for (int col = randGroup1; col < randGroup1 + 3; col++) {
				swapTemp[row][col - randGroup1] = new Container<T>(premade[row][col]);
			}
		}
		// Puts the values of group 2 where group 1 was
		int counter = randGroup2;
		for (int row = 0; row < 9; row++) {
			for (int col = randGroup1; col < randGroup1 + 3; col++) {
				premade[row][col] = premade[row][counter];
				counter++;
			}
			counter = randGroup2;
		}
		// Puts the values of swapTemp where group 2 was
		for (int row = 0; row < 9; row++) {
			for (int col = randGroup2; col < randGroup2 + 3; col++) {
				premade[row][col] = swapTemp[row][col - randGroup2].get();
			}
		}
		return premade;
	}
	
	/**
	 * Transposes the 2D array. Turns the rows in into columns and vice versa.
	 * In essence, the 2D array is being rotated to the right by 90 degrees.
	 * 
	 * @param <T> the type of element used
	 * @param premade the 2D array to be used for swapping
	 * @return premade the 2D array type
	 */
	public static <T extends Comparable<T>> T[][] transpose(final T[][] premade){
		Container<T>[][] copy = Container.array(9,9);
	    for (int row = 0; row < 9; ++row) {
	        for (int col = 0; col < 9; ++col) {
	        	//Transposition
	            copy[row][col] = new Container<T>(premade[9 - col - 1][row]); 
	        }
	    }
	    for (int row = 0; row < 9; ++row) {
	        for (int col = 0; col < 9; ++col) {
	            premade[row][col] = copy[row][col].get();
	        }
	    }
	    return premade;
	}

	/**
	* Randomizes the 2D array buy running the <code>swapRowInGroup</code>,
	* <code>swapColumnInGroup</code>, <code>swapGroupAsRow</code>, and
	* <code>swapGroupAsColumn</code> methods.
	*
	* @param <T> the type of element used
	* @param premade the 2D array to be used for swapping
	* @return premade the 2D array type
	*/
	public static <T extends Comparable<T>> T[][] randomizeAllGroupsOnce(final T[][] premade) {
		swapRowInGroup(premade);
		swapColumnInGroup(premade);
		swapGroupAsRow(premade);
		swapGroupAsColumn(premade);
		transpose(premade);
		return premade;
	}
}
