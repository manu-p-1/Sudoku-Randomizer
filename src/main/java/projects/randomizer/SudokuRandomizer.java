package projects.randomizer;
import java.util.Arrays;
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
 * @author Manu Puduvalli
 *
 */
public class SudokuRandomizer<T extends Comparable<T>> {

	private final T[][] board;

	/**
	 * This Constructor Sets the board.
	 *
	 * @param board the Sudoku board
	 */
	public SudokuRandomizer(T[][] board) {
		this.board = board;
	}

	/**
	 * Swaps two random rows within every three by three sub region. Rather than
	 * swapping every row within every single sub region, The rows are swapped as a
	 * group. This is done by randomly choosing two rows from index 0 to 2, from
	 * index 3 to 5, and from index 6 to 8 and swapping them within the confines of
	 * its sub region.
	 *
	 * @return SudokuRandomizer
	 */
	public SudokuRandomizer<T> swapRowInGroup() {
		Random rng = new Random();
		Container<T>[] rowTemp = Container.array(9);
		int randRow1; // The first row to be swapped
		int randRow2; // The second row to be swapped
		// Moves every third row to make sure we stick to the same group
		for (int row = 0; row < board.length; row += 3) {
			if (row <= 2) {
				randRow1 = rng.nextInt(3);
				randRow2 = rng.nextInt(3);
			} else if (row <= 5) {
				randRow1 = rng.nextInt(3) + 3;
				randRow2 = rng.nextInt(3) + 3;
			} else {
				randRow1 = rng.nextInt(3) + 6;
				randRow2 = rng.nextInt(3) + 6;
			}
			// Swap loop
			for (int col = 0; col < board[randRow1].length; col++) {
				rowTemp[col] = new Container<>(board[randRow1][col]);
				board[randRow1][col] = board[randRow2][col];
				board[randRow2][col] = rowTemp[col].get();
			}
		}
		return this;
	}

	/**
	 * Swaps two random columns within every three by three sub region. Rather than
	 * swapping every columns within every single sub region, The columns are
	 * swapped as a group. This is done by randomly choosing two columns from index
	 * 0 to 2, from index 3 to 5, and from index 6 to 8 and swapping them within the
	 * confines of its sub region.
	 *
	 * @return SudokuRandomizer
	 */
	public SudokuRandomizer<T> swapColumnInGroup() {
		Random rng = new Random();
		Container<T>[] colTemp = Container.array(9);
		int randCol1; // The first row to be swapped
		int randCol2; // The second row to be swapped
		int columnRep;
		// Moves every third column to make sure we stick to the same group
		for (columnRep = 0; columnRep < board.length; columnRep += 3) {
			if (columnRep <= 2) {
				randCol1 = rng.nextInt(3);
				randCol2 = rng.nextInt(3);
			} else if (columnRep <= 5) {
				randCol1 = rng.nextInt(3) + 3;
				randCol2 = rng.nextInt(3) + 3;
			} else {
				randCol1 = rng.nextInt(3) + 6;
				randCol2 = rng.nextInt(3) + 6;
			}
			// Swap loop
			for (int row = 0; row < board.length; row++) {
				colTemp[row] = new Container<>(board[row][randCol1]);
				board[row][randCol1] = board[row][randCol2];
				board[row][randCol2] = colTemp[row].get();
			}
		}
		return this;
	}

	/**
	 * Swaps two random groups of sub regions horizontally. A sub region group
	 * consists of all horizontally adjacent sub regions. Two random group values
	 * are generated. If the value falls between index 0 and index 2, this indicates
	 * group 1. If the value falls between index 3 and index 5, this indicates group
	 * 2. If the value falls between index 6 and index 8, this indicates group 3.
	 * Afterwards, each corresponding group is swapped.
	 *
	 * @return SudokuRandomizer
	 */
	public SudokuRandomizer<T> swapGroupAsRow() {
		Random rng = new Random();
		// A temporary array for the values in each group
		Container<T>[][] swapTemp = Container.array(3,9);
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
			for (int col = 0; col < board[row].length; col++) {
				swapTemp[row - randGroup1][col] = new Container<>(board[row][col]);
			}
		}
		// Puts the values of group 2 where group 1 was
		int counter = randGroup2;
		for (int row = randGroup1; row < randGroup1 + 3; row++) {
			System.arraycopy(board[counter], 0, board[row], 0, board[row].length);
			counter++;
		}
		// Puts the values of swapTemp where group 2 was
		for (int row = randGroup2; row < randGroup2 + 3; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = swapTemp[row - randGroup2][col].get();
			}
		}
		return this;
	}

	/**
	 * Swaps two random groups of sub regions vertically. A sub region group
	 * consists of all vertically adjacent sub regions. Two random group values are
	 * generated. If the value falls between index 0 and index 2, this indicates
	 * group 1. If the value falls between index 3 and index 5, this indicates group
	 * 2. If the value falls between index 6 and index 8, this indicates group 3.
	 * Afterwards, each corresponding group is swapped.
	 *
	 * @return SudokuRandomizer
	 */
	public SudokuRandomizer<T> swapGroupAsColumn() {
		Random rng = new Random();
		// A temporary array for the values in each group
		Container<T>[][] swapTemp = Container.array(9,3);
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
				swapTemp[row][col - randGroup1] = new Container<>(board[row][col]);
			}
		}
		// Puts the values of group 2 where group 1 was
		int counter = randGroup2;
		for (int row = 0; row < 9; row++) {
			for (int col = randGroup1; col < randGroup1 + 3; col++) {
				board[row][col] = board[row][counter];
				counter++;
			}
			counter = randGroup2;
		}
		// Puts the values of swapTemp where group 2 was
		for (int row = 0; row < 9; row++) {
			for (int col = randGroup2; col < randGroup2 + 3; col++) {
				board[row][col] = swapTemp[row][col - randGroup2].get();
			}
		}
		return this;
	}

	/**
	 * Transposes the 2D array. Turns the rows in into columns and vice versa.
	 *
	 * @return SudokuRandomizer
	 */
	public SudokuRandomizer<T> transpose(){
		Container<T>[][] copy = Container.array(9,9);
	    for (int row = 0; row < 9; ++row) {
	        for (int col = 0; col < 9; ++col) {
	        	//Transposition
	            copy[row][col] = new Container<>(board[9 - col - 1][row]);
	        }
	    }
	    for (int row = 0; row < 9; ++row) {
	        for (int col = 0; col < 9; ++col) {
	            board[row][col] = copy[row][col].get();
	        }
	    }
	    return this;
	}

	/**
	* Randomizes the 2D array buy running {@link SudokuRandomizer#swapRowInGroup()},
	* {@link SudokuRandomizer#swapColumnInGroup()}, {@link SudokuRandomizer#swapGroupAsRow()},
	* and {@link SudokuRandomizer#swapGroupAsColumn()}.
	*
	* @return SudokuRandomizer
	*/
	public T[][] randomizeAll() {
		swapRowInGroup();
		swapColumnInGroup();
		swapGroupAsRow();
		swapGroupAsColumn();
		transpose();
		return this.board;
	}

	/**
	 * Returns the modified board.
	 *
	 * @return this board
	 */
	public T[][] get(){
		return this.board;
	}

	/**
	 * Prints a 2d array with some visual enhancements.
	 *
	 * @param array the array to be printed
	 */
	public static <T> void print(T[][] array) {
		System.out.println(Arrays.deepToString(array)
				.replace("], ", "]\n")
				.replace("[[", "[")
				.replace("]]", "]"));
	}
}
