package node;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
/**
 * A node class for a doubly linked list.
 * @param <T> the type of data stored in the node
 */
public class Node<T> {
    protected T data;
    protected Node<T> previous;
    protected Node<T> next;
    /**
     * Constructs a new node with the specified data.
     * @param data the data to be stored in the node
     */
    public Node(T data) {
        this.data = data;
        this.previous = null;
        this.next = null;
    }
    /**
     * Constructs a new node with the specified data, previous node, and next node.
     * @param data     the data to be stored in the node
     * @param previous the previous node
     * @param next     the next node
     */
    public Node(T data, Node<T> previous, Node<T> next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
    /**
     * Returns the data stored in the node.
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }
    /**
     * Sets the data stored in the node.
     * @param data the data to be set
     */
    public void setData(T data) {
        this.data = data;
    }
    /**
     * Returns the previous node.
     * @return the previous node
     */
    public Node<T> getPrevious() {
        return previous;
    }
    /**
     * Sets the previous node.
     * @param previous the previous node to be set
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
    /**
     * Returns the next node.
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     * @param next the next node to be set
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
