package ru.nsu.fit.g18214.shatalov;

import java.util.Iterator;

public class Queue<K extends Comparable<K>, O> {
  private Elem<O, K> tail = null;
  private Elem<O, K> head = null;
  private int queueSize = 0;

  /**
   * .
   * @return size (number) of elements in queue
   */
  public int size() {
    return queueSize;
  }

  /**
   * Insert new element in queue according to given key.
   * key.head < key.tail.
   * Every object must have key != null.
   * @param key - comparable object that define position in queue.
   * @param obj - object to be stored in queue.
   */
  public void insert(K key, O obj) {
    if (obj == null || key == null) {
      return;
    }
    if (head == null) {
      head = new Elem<>(key, obj, null);
      tail = head;
      queueSize++;
      return;
    }
    if (key.compareTo(head.key) <= 0) { //key <= head.key
      Elem<O, K> newHead = new Elem<>(key, obj, null);
      newHead.addNextElem(head);
      head = newHead;
      queueSize++;
      return;
    }
    if (key.compareTo(tail.key) > 0) {  //key > tail.key
      Elem<O, K> newTail = new Elem<>(key, obj, tail);
      tail.addNextElem(newTail);
      tail = newTail;
      queueSize++;
      return;
    }
    // if new element somewhere between
    Elem<O, K> newElemPlace = head;
    for (int i = 0; i < queueSize; ++i) {
      K headKey = newElemPlace.key;
      K tailKey = newElemPlace.getNextElem().key;
      if (headKey.compareTo(key) <= 0 && tailKey.compareTo(key) >= 0) {
        Elem<O, K> next = newElemPlace.getNextElem();
        Elem<O, K> newTail = new Elem<>(key, obj, newElemPlace);
        newElemPlace.addNextElem(newTail);
        newTail.addNextElem(next);
        queueSize++;
        break;
      }
      newElemPlace = newElemPlace.getNextElem();
    }
  }

  /**
   * Extracts element with maximal key.
   * @return element with maximal key.
   * @throws IndexOutOfBoundsException if queue is empty.
   */
  public O extractMax() throws IndexOutOfBoundsException {
    queueSize--;
    if (tail != null && tail == head) {
      Elem<O, K> maxElem = tail;
      tail = null;
      head = null;
      return maxElem.getElem();
    }
    if (tail != null) {
      Elem<O, K> maxElem = tail;
      tail = tail.getPreElem();
      tail.addNextElem(null);
      return maxElem.getElem();
    } else {
      throw new IndexOutOfBoundsException("Queue is empty.");
    }
  }

  /**
   * Extracts from queue element with minimal key.
   * @return element with minimal key.
   * @throws IndexOutOfBoundsException if queue is empty.
   */
  public O extractMin() throws IndexOutOfBoundsException {
    queueSize--;
    if (tail != null && tail == head) {
      Elem<O, K> minElem = tail;
      tail = null;
      head = null;
      return minElem.getElem();
    }
    if (head != null) {
      Elem<O, K> minElem = head;
      head = head.getNextElem();
      return minElem.getElem();
    } else {
      throw new IndexOutOfBoundsException("Queue is empty.");
    }
  }

  /**
   * Implementation of iterator for this queue.
   * @return Iterator.
   */
  public Iterator<O> iterator() {
    return new Iterator<O>() {
      private Elem<O, K> current = head;

      public boolean hasNext() {
        return current != null;
      }

      public O next() throws IndexOutOfBoundsException {
        if (current == null) {
          throw new IndexOutOfBoundsException("End of queue.");
        }
        O result = current.getElem();
        current = current.getNextElem();
        return result;
      }
    };
  }
}
