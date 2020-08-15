import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;

    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // return the number of items on the deque
    public int size() {
//        Node n = head;
//        int ct = 0;
//        while (n != null) {
//            ct++;
//            n = n.next;
//        }
//        return ct;
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        } else {
            Node n = head;
            head = new Node();
            head.item = item;
            head.next = n;
            if (n != null) {
                n.previous = head;
            }
            if (tail == null) {
                tail = head;
            }
            size++;
        }

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        } else {
            Node n = tail;
            tail = new Node();
            tail.item = item;
            tail.previous = n;
            if (n != null) {
                n.next = tail;
            }

            if (head == null) {
                head = tail;
            }
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("No first element");
        } else {
            Item i = head.item;
            head = head.next;
            if (head != null)
                head.previous = null;
            else
                tail = null;
            size--;
            return i;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("No last element");
        } else {
            Item i = tail.item;
            tail = tail.previous;
            if (tail != null)
                tail.next = null;
            else
                head = null;
            size--;
            return i;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot use this operation");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            } else {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> test = new Deque<>();

        // adding 10 elements
        for (int i = 0; i < 5; i++) {
            test.addFirst(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in deque: " + test.size() + "\n");

        }
        for (int i = 5; i < 10; i++) {
            test.addLast(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in deque: " + test.size() + "\n");

        }


        System.out.print("\nIterator test:\n[");
        for (Integer elem : test)
            System.out.print(elem + " ");
        System.out.println("]\n");

        // removing 10 elements
        for (int i = 0; i < 5; i++) {
            System.out.println("Removed element: " + test.removeFirst());
            System.out.println("Current number of elements in deque: " + test.size() + "\n");
        }
        for (int i = 5; i < 10; i++) {
            System.out.println("Removed element: " + test.removeLast());
            System.out.println("Current number of elements in deque: " + test.size() + "\n");
        }
    }

}
