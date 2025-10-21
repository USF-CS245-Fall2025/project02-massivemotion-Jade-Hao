/***
 * @author jadehao
 * A linked list implementation that stores elements of type T
 * This class support basic list operations like adding, removing, retrieving elements
 * by index. This code also grows as elements are added.
 * @param <T> the type of elements stored within the list
 */

public class LinkedList<T> implements List<T> {
   private class Node<T>{
       T val;// the value stored in the node
       Node next; //reference to the next node in the list
       Node(T v){ //constructs a new node containing the specified value
           this.val = v;
           this.next = null;
       }
   }
    private Node head; // the head of the linked list (might be null if list=empty)
    int size; //the number of elements currently in the list

    /**
     * Constructs an empty Linked List
     */
    public LinkedList(){
        head = null;
        size = 0;
    }

    /***
     * Inserts the specified element at a specific postion in list
     * Shift element currently to that position and all elements after to the right
     * @param index where specified element is to be inserted
     * @param element the element to insert
     */
    public void add(int index, T element) {
        if (index < 0 || index > size){ //base case
            throw new IndexOutOfBoundsException();
        }
        if (index== 0){ //if index is 0; insert the element at the start
            Node node= new Node<>(element); //create new node
            node.next = head; //set the new node next ref to curr head
            head = node; //update the head to the new node (making it first node)
        }else{
            Node prev = head; //if head is not 0, traverse the list
            for(int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Node node = new Node(element); //create new node with provided element
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    /**
     * Appends the specified element to the end of this list
     * @param element the element to add
     * @return true
     */
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    /***
     * Reterievers the element at the specified postion in the list
     * @param index the index of the element to return
     * @return the element at the specified index
     */
    public T get(int index) {
        if(index < 0 || index >= size){ //base case
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = head;
        for(int i= 0; i <index; i++){ //traverse list to find node
            curr = curr.next; // move to the next node in list
        }
        return curr.val; //return value stored in node
    }

    /***
     * Remove the element at specific position in the list
     * @param index the index of the element to remove
     * @return the element that was removed from the list
     */
    public T remove(int index) {
        if (index < 0 || index >= size){ //base case
            throw new IndexOutOfBoundsException();
        }
        T val;
        if (index == 0){ //check if the index is 0, removing head node
            val = (T) head.val; //store value of current head
            head = head.next; //update the head to the next node
        }else{
            Node<T> prev= head;
            for (int i = 0; i<index -1 ; i++){ // traverse the list until we reach the one to remove
                prev = prev.next;
            }
            Node<T> toRemove = prev.next;
            val = toRemove.val; //store value of node we want to remove
            prev.next= toRemove.next; //remove node by update prev to the next node
        }
        size--; //decrease the size
        return val;
    }
    /***
     * Return the number of element currently in the list
     * @return the current size of the list
     */
    public int size() {
        return size;
    }
}
