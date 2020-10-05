import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] items;
  private int n = 0;

  // construct an empty randomized queue
  public RandomizedQueue() {
    items = (Item[]) new Object[1];
  }

  // size the array
  private void resize(int newSize) {
    Item[] copy = (Item[]) new Object[newSize];
    for (int i = 0; i < n; i++) {
      copy[i] = items[i];
    }
    items = copy;
  }

  // exchange elements of an array
  private void exchange(Item[] arr, int i, int j) {
    if (i == j)
      return;
    Item temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return n;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException();
    if (n == items.length) resize(2 * items.length);
    items[n++] = item;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int randomIndex = StdRandom.uniform(n);
    Item item = items[randomIndex];
    items[randomIndex] = null;
    exchange(items, randomIndex, n - 1);
    n--;
    if (n > 0 && n == items.length / 4) resize(items.length / 2);
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    int randomIndex = StdRandom.uniform(n);
    return items[randomIndex];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private final Item[] randomItems;
    private int current;

    public RandomizedQueueIterator() {
      randomItems = (Item[]) new Object[n];
      for (int i = 0; i < n; i++) {
        randomItems[i] = items[i];
      }
      shuffle(randomItems);
      current = 0;
    }

    private void shuffle(Item[] arr) {
      for (int i = 0; i < n; i++) {
        int randomIndex = StdRandom.uniform(n);
        Item temp = arr[i];
        arr[i] = arr[randomIndex];
        arr[randomIndex] = temp;
      }
    }

    public boolean hasNext() {
      return current < randomItems.length;
    }

    public void remove() {
      throw new UnsupportedOperationException(); 
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = randomItems[current];
      current++;
      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<String> rq = new RandomizedQueue<String>();
    rq.enqueue("Google");
    rq.enqueue("Apple");
    rq.enqueue("Microsoft");
    System.out.println(rq.sample());
    System.out.println(rq.dequeue());
    Iterator<String> rqIterator = rq.iterator();
    while (rqIterator.hasNext()) {
      System.out.println(rqIterator.next());
    }
  }
}
