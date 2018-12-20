package projects.randomizer;

/**
 * A class that represents a single container which holds an element
 *
 * @param <E> the element in this container
 * @author Manu Puduvalli
 */
public class Container<E> {

	private E elem; //The element in this container
	
	/**
	 * Class constructor to create a new container with the given element.
	 * 
	 * @param elem element in the container
	 */
	Container(E elem) {this.elem = elem;}
	/**
	 * Returns the element in the container.
	 * 
	 * @return the element in the container
	 */
	E get() {return elem;}
	
	/**
	 * Creates an 1d array of container objects with a specified type
	 * 
	 * @param <T> the type of elements in each container
	 * @param length the length of the array to create
	 * @return an array of container objects
	 */
	@SuppressWarnings("unchecked")
	static <T> Container<T>[] array(int length) {
		return (Container<T>[]) new Container[length];
	} 
	
	/**
	 * Creates an 2d array of container objects with a specified type
	 * 
	 * @param <T> the type of elements in each container
	 * @param row the row length of the 2d array to create
	 * @param col the col length of the 2d array to create
	 * @return an array of container objects
	 */
	@SuppressWarnings("unchecked")
	static <T> Container<T>[][] array(int row, int col) {
		return (Container<T>[][]) new Container[row][col];
	} 
} 
