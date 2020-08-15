import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> words = new RandomizedQueue<String>();
        int ct = Integer.valueOf(args[0]);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            words.enqueue(s);
        }
        for (int i = 0; i < ct; i++) {
            StdOut.println(words.dequeue());
        }
    }
}
