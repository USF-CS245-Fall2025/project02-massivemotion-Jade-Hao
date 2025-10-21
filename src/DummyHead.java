/***
 * @author jadehao
 * A single linked list that uses a dummy head
 * The dummy head simplifies inseration and removal since it
 * provides a non-null placeholder node before first element
 * This code supports addition, retrieval, and removal of elements
 * @param <T> the type of elements stored in the list
 */
public class DummyHead<T> implements List<T> {
    private class Node{ //represents a node in the linked list
        T val; //value stored in this node
        Node next; //refers to next node in the list
        Node(T v){ //constructs a new node
            this.val = v;
            this.next = null;
        }
    }
    private Node dummy; //dummy node
    int size; // number of elements currently stored in the list

    public DummyHead(){ //constructs an empty linked list
        dummy = new Node(null);
        size= 0;
    }

    /***
     * Inserts an element at the specified index
     * Shifts elements to the right
     * @param index the position to insert the element
     * @param element the element to be inserted
     */
    public void add(int index, T element) {
        if (index < 0 || index > size){ //base case
            throw new IndexOutOfBoundsException();
        }
        Node prev = dummy;
        for(int i= 0; i< index; i++){ //traverse the lise until node before index
            prev = prev.next; //move to next node
        }
        Node node = new Node(element);
        node.next = prev.next; // point to the node that was at prev index
        prev.next = node;
        size++; //increment size
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

    /**
     * Returns the element at the specified index in the lsit
     * @param index the index of the element to return
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size){ // base case
            throw new IndexOutOfBoundsException();
        }
        Node curr = dummy.next; //start from the first node
        for (int i=0; i< index; i++){ //traverse the list
            curr=curr.next; //move to the next node
        }
        return curr.val; //return the value stored in the node at specific index
    }

    /***
     * Remove and return the element at the specified index
     * Shift any elements after to the left
     * @param index the index of the element to remove
     * @return the element that was removed from the list
     */
    public T remove(int index) {
        if (index < 0 || index >= size){ //base case
            throw new IndexOutOfBoundsException();
        }
        Node prev = dummy; // start from dummy node
        for(int i=0; i< index; i++){ //traverse the list
            prev= prev.next;
        }
        Node toRemove = prev.next; //node to remove is one right after prev
        prev.next = toRemove.next; //adjust pointer to skip over node
        size--; //decrease size of list
        return toRemove.val; //return value of removed node
    }

    /***
     * Return the number of element currently in the list
     * @return the current size of the list
     */
    public int size() {
        return size;
    }
}
