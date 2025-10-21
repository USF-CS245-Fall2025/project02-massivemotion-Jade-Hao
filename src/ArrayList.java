/***
 * @author jadehao
 * A simple implementation of resizing an array list
 * Provides basic functionality similar to java.util.ArrayList class
 * In this code we do indexing, insertion, removal and resizing at the array reaches cap
 * NOTE:Array-based list with initial capacity of 10.
 * @param <T> the type of element stored in list
 */
public class ArrayList<T> implements List<T>{
    private Object[] data; // the internal array
    private int size; //number of currently stored elements in the array
    private static final int CAPACITY = 10; // cap on size (given to us in pdf)

    /***
     * Constructs an empty Array List with cap of 10 elements
     */
    public ArrayList(){
        data = new Object[CAPACITY];
        size = 0;
    }

    /***
     *  Ensures that array has at least the specific min capacity
     *  If the array is too small then it shall be replaced with a new array
     * (either double its size or big enough to fit all the elements
     * @param min the minimum required capacity
     */
    public void grow_Array(int min){
        if(min <= data.length){ //check array size is big enough
            return;
        }
        int newSize = Math.max(data.length * 2, min); //create new size : double or min required
        Object[] expand = new Object[newSize]; //create new array with calculated size
        System.arraycopy(data, 0, expand, 0, size); //copy elements from old array to new one
        data= expand; //update reference to point to new created array
    }

    /***
     * Insert the specified element at index in list
     * Elements at or after the specified index are shifted one position
     * @param index the index to insert the element
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if code is out of range
     */
    public void add(int index, T element) {
        if (index < 0 || index > size){ //base case
           throw new IndexOutOfBoundsException();
        }
        if(size == data.length){ //check if the array is full
            grow_Array(size +1); //call grow_array to expand the array if it is full
        }
        if(index < size){ //if less than curent size -> shift right
            System.arraycopy(data, index, data, index + 1, size-index); //shift one to the right
        }
        data[index] = element; //insert new element
        size++; // increment the size
    }

    /***
     * Appends the specified element to the end of the list
     * @param element element to insert
     * @return the element at the specific index
     */
    public boolean add(T element) {
       if (size == data.length){
           grow_Array(size + 1);
       }
       data[size++] = element;
        return true;
    }

    /***
     * Gets element at a specific index
     * @param index position of the element to return
     * @return element at specific index
     */
    public T get(int index) {
       if (index < 0 || index >= size){
           throw new IndexOutOfBoundsException();
       }
        return (T) data[index]; // need to convert T back to whatever the object is
    }

    /***
     * Remove the element at the specific index, shifting after the element
     * @param index the index of element to remove
     * @return the element that was removed
     */
    public T remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        T oldVersion = (T) data[index];
        int numsMoved = size - index -1;
        if(numsMoved > 0){
            System.arraycopy(data, index + 1, data, index, numsMoved);
        }
        data[--size] = null;
        return oldVersion;
    }

    /***
     * Return the number of element currently in the list
     * @return the current size of the list
     */
    public int size() {
        return size;
    }
}
