package ru.nsu.fit.g18214.shatalov;

import java.util.Iterator;

public class Queue<K extends Comparable<K>, O> {
  private Elem<O, K> tail = null;
  private Elem<O, K> head = null;
  private int queueSize = 0;

  public int size() {
    return queueSize;
  }

  public boolean empty(){
    return queueSize == 0;
  }

  public void insert(K key, O obj) {
    if (obj == null || key == null) return;
    if (head == null) {
      head = new Elem<O, K>(key, obj, null);
      tail = head;
      queueSize++;
      return;
    }
    if (key.compareTo(head.key) <= 0) { //key <= head.key
      Elem<O, K> newHead = new Elem<O, K>(key, obj, null);
      newHead.addNextElem(head);
      head = newHead;
      queueSize++;
      return;
    }
    if (key.compareTo(tail.key) > 0) {  //key > tail.key
      Elem<O, K> newTail = new Elem<O, K>(key, obj, tail);
      tail.addNextElem(newTail);
      tail = newTail;
      queueSize++;
      return;
    }
    // if new element somewhere between
    Elem<O, K> newElemPlace = head;
    for (int i = 0; i < queueSize - 1; ++i) {
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


  public O extractMax() throws IndexOutOfBoundsException {
    queueSize--;
    if (tail!= null && tail == head){
      Elem<O, K> maxElem = tail;
      tail = null;
      head = null;
      return maxElem.getElem();
    }
    if (tail != null) {
      Elem<O, K> maxElem = tail;
      if (tail.getPreElem() == null)
        throw new IndexOutOfBoundsException("Out of Bound.");
      tail = tail.getPreElem();
      tail.addNextElem(null);
      return maxElem.getElem();
    } else {
      throw new IndexOutOfBoundsException("Queue is empty.");
    }
  }


  public O extractMin() {
    queueSize--;
    if (tail!= null && tail == head){
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
