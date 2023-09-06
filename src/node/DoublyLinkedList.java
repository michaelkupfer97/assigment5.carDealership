package node;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
/**
 * Doubly linked list implementation of the List interface.
 * @param <T> the type of elements stored in the list
 */
public class DoublyLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    /**
     * Checks if the doubly linked list is empty.
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns the number of elements in the doubly linked list.
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Adds an element to the beginning of the doubly linked list.
     * @param data the element to be added
     */
    @Override
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }
    /**
     * Adds an element to the end of the doubly linked list.
     * @param data the element to be added
     */
    @Override
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }
    /**
     * Removes the first occurrence of the specified element from the doubly linked list.
     * @param data the element to be removed
     * @return the removed element, or null if the element was not found
     */
    @Override
    public T remove(T data) {
        if (head == null)
            return null;

        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                if (current == head) {
                    head = current.getNext();
                    if (head != null)
                        head.setPrevious(null);
                } else {
                    current.getPrevious().setNext(current.getNext());
                    if (current.getNext() != null)
                        current.getNext().setPrevious(current.getPrevious());
                }
                size--;
                return current.getData();
            }
            current = current.getNext();
        }

        System.out.println("Item was not found");
        return null;
    }
    /**
     * Removes all elements from the doubly linked list.
     */
    @Override
    public void clear() {
        Node<T> current = head;
        while (current != null) {
            current.setData(null);
            current = current.getNext();
            size--;
        }
    }
    /**
     * Checks if the doubly linked list contains the specified element.
     * @param data the element to be checked
     * @return true if the list contains the element, false otherwise
     */
    @Override
    public boolean contains(T data) {
        Node<T> current = head;

        while (current != null) {
            if (current.getData().equals(data))
                return true;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Prints the elements of the doubly linked list in forward order.
     */
    @Override
    public void printForward() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }
    /**
     * Prints the elements of the doubly linked list in backward order.
     */
    @Override
    public void printBackward() {
        Node<T> current = tail;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getPrevious();
        }
    }
}
