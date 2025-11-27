/***
 * A generic interface that defines basic operations of a list data structure
 * @param <T> the type of elements stored in the list
 *          add: inserts the specified element at specific position in the list -> shift elemetns to
 *           right
 *           boolean add: appends the specific element to the end of the list
 *           get: Gets element at a specific index
 *           remove: removes an element at a specific position of the list
 *           size: returns the number of elements within the list
 */
public interface List<T> {

    public void add (int index, T element) throws IndexOutOfBoundsException;
    public boolean add (T element);
    public T get (int index) throws IndexOutOfBoundsException;
    public T remove (int index)throws IndexOutOfBoundsException;
    public int size ();
}
