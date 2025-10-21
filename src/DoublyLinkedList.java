/***
 * @author jadehao
 * Implementation of a doubly linked list that stores elements in nodes linked in the
 * forward and backward directions.
 * You can add, retrieve, and remove elements at specific indices. Maintains references
 * in both head and tail nodes
 * @param <T> the tupe of elements sotred within a list
 */

public class DoublyLinkedList<T> implements List<T> {
    private class Node{ // stores value and refrences to prev and next node
        T val;
        Node next;
        Node prev;

        Node(T v){ // constructs new node from a specified value
            this.val = v;
            this.next = null;
        }
    }
    private Node head; //first node in the list
    private Node tail; // last node in the list
    int size;

    /**
     * constructs an empty doubly linked list
     */
    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /***
     * Inserts an element at the specified index in the list
     * Shifts elements after to the right
     * @param index position to insert the element
     * @param element the element that is inserted
     */
    public void add(int index, T element) {
        if (index < 0 || index > size){ //base case
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node(element);

        if (size == 0){ //empty list
            head = tail = node;
        }else if(index == 0){ //insert at the beginning
            node.next= head;
            head.prev = node;
            head = node;
        }else if(index == size){ //insert at the end
            tail.next = node;
            node.prev = tail;
            tail = node;
        }else{ //insert int eh middle
            Node curr= head;
            for(int i = 0; i < index; i++){
                curr= curr.next;
            }
            Node inFront = curr.prev;
            inFront.next = node; //update to point to node
            node.prev = inFront; //set node to inFront
            node.next = curr; //link node to curr
            curr.prev = node; //like curr to node
        }
        size++; //increment list size
    }

    /***
     * Appends the specified element to the end of the list
     * @param element  the element to be added
     * @return
     */
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    /***
     * Return the element at the specified position in the list
     * Can go forward or backwards
     * @param index the index of the element to return
     * @return the element at the specified index
     */
    public T get(int index) {
        if (index < 0 || index >= size) { //base case
            throw new IndexOutOfBoundsException();
        }
        Node curr;
        if (index < size / 2) { //if in the first half  of list -start from head
            curr = head;
            for(int i= 0; i< index; i++){ //traverse the list from the head, moving towards index
                curr= curr.next;
            }
        }else{
            curr = tail; //if index is the second half start from tail
            for (int i = size - 1; i > index; i--) { //traverse from the tail
                curr = curr.prev;
            }
        }
        return curr.val; //return the value of the node at index
    }

    /**
     * Removes the element at the specified index in the list
     * @param index the position of the element to remove
     * @return
     */
    public T remove(int index) {
        if (index < 0 || index >= size) { //base case
            throw new IndexOutOfBoundsException();
        }
        Node toRemove;
        if (size == 1){ //if the list has only one element, remove only node
            toRemove= head;
            head = tail = null;
        }else if(index == 0){ //if element is the first node -> remove it
            toRemove = head; //set to head node
            head = head.next; //move head to next node
            head.prev = null;
        }else if( index == size -1){ // if the last node
            toRemove = tail; //set toRemove to tail
            tail = tail.prev; // move the tail to previous node
            tail.next = null; // set new tail to null
        }else{ // if the element is somewhere in the middle
            toRemove = head;
            for(int i=0 ; i< index; i++){ //traverse the list until the node at the specified index is reached
                toRemove = toRemove.next; // move to the next node in the list
            }
            toRemove.prev.next = toRemove.next; //set the next of the previous node to the next
            toRemove.next.prev = toRemove.prev;
        }
        size--; //decrease the size
        return toRemove.val; // return value of removed node
    }

    /***
     * Return the number of element currently in the list
     * @return the current size of the list
     */
    public int size() {
        return size;
    }
}
