import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node first, last;
  private int size = 0;

  // node
  private class Node {
    Item item;
    Node next;
    Node prev;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }                 

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException();
    if (isEmpty()) {
      first = new Node();
      first.item = item;
      last = first;
    }
    else {
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      first.prev = null;
      oldFirst.prev = first;
    }
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException();
    if (isEmpty()) {
      last = new Node();
      last.item = item;
      first = last;
    }
    else {
      Node oldLast = last;
      last = new Node();
      last.item = item;
      last.prev = oldLast;
      last.next = null;
      oldLast.next = last;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = first.item;
    first = first.next;
    if (first == null) last = null;
    else first.prev = null;
    size--;
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = last.item;
    last = last.prev;
    if (last == null) first = null;
    else last.next = null;
    size--;
    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  // deque implementation of iterator
  private class DequeIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException(); 
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    // Deque<String> deque = new Deque<String>();
    // deque.addFirst("Facebook");
    // deque.addFirst("Google");
    // deque.addLast("Apple");
    // deque.addLast("Microsoft");
    // deque.addLast("Paypal");
    // System.out.println(deque.size());
    // deque.removeFirst();
    // deque.removeLast();
    // for (String string : deque) {
    //   System.out.println(string);
    // }
    // Iterator<String> dequeIterator = deque.iterator();
    // while (dequeIterator.hasNext()) {
    //   System.out.println(dequeIterator.next());
    // }
    // System.out.println(deque.size());

    Deque<Integer> deque = new Deque<Integer>();
    deque.addFirst(1);
    deque.addFirst(2);
    deque.isEmpty();
    deque.addFirst(4);
    deque.addFirst(5);
    deque.addFirst(6);
    deque.addFirst(7);
    deque.addFirst(8);
    deque.removeLast();
    deque.addFirst(10);
    deque.removeLast();
    Iterator<Integer> dequeIterator = deque.iterator();
    while (dequeIterator.hasNext()) {
      System.out.println(dequeIterator.next());
    }
  }
}