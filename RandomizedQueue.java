import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rq;
    private int tailIndex = 0;
    private Random rGen = new Random();

    // construct an empty randomized queue
    public RandomizedQueue() {
        rq = (Item[]) (new Object[1]);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) (new Object[capacity]);
        for (int i = 0; i < tailIndex; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return tailIndex == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return tailIndex;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null argument");
        } else {
            if (tailIndex == rq.length) {
                resize(rq.length * 2);
            }
            rq[tailIndex] = item;
            tailIndex++;

        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (tailIndex == 0) {
            throw new NoSuchElementException();
        } else {
            if (tailIndex <= rq.length / 4) {
                resize(rq.length / 2);
            }

            int index = rGen.nextInt(tailIndex);
            Item retVal = rq[index];
            rq[index] = rq[--tailIndex];
            rq[tailIndex] = null;
            return retVal;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = rGen.nextInt(tailIndex);     // selects a random index
        return rq[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int current = 0;
        private Item[] shuffledArray = (Item[]) (new Object[tailIndex]);

        public QueueIterator() {
            for (int i = 0; i < tailIndex; i++) {
                shuffledArray[i] = rq[i];
            }
            shuffle(shuffledArray);
        }

        public boolean hasNext() {
            return current < tailIndex;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot use this operation");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return shuffledArray[current++];
        }

        public void shuffle(Item[] array) {
            int n = tailIndex;
            for (int i = 0; i < n; i++) {
                int r = i + (int) (Math.random() * (n - i));
                Item swap = array[r];
                array[r] = array[i];
                array[i] = swap;
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();

        // adding 10 elements
        for (int i = 0; i < 10; i++) {
            test.enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + test.size() + "\n");

        }


        System.out.print("\nIterator test:\n[");
        for (Integer elem : test)
            System.out.print(elem + " ");
        System.out.println("]\n");

        // removing 10 elements
        for (int i = 0; i < 10; i++) {
            System.out.println("Removed element: " + test.dequeue());
            System.out.println("Current number of elements in queue: " + test.size() + "\n");
        }

    }


}
