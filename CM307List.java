 
public abstract interface CM307List<E> {
	public void add(E e);

	// adds element e to the end of the list
	// list may contain duplicate elements
	// if array-based, should increase array size as needed

	public boolean insert(int n, E e);

	// insert element e at index n of the list
	// moves data at position n and beyond to make room for the new element
	// should allow insertion at end of data (at n=size)
	// returns true if successful, false if n is out of bounds
	// if array-based, should increase array size as needed

	public E get(int n);

	// returns the element at position n or null if n is out of bounds

	public E remove(int n);

	// removes element at position n
	// returns the element that was removed or null if n is out of bounds

	public boolean isEmpty();

	// returns true if there are no items in the list, false otherwise

	public int size();
	// returns the number of elements in the list
}
