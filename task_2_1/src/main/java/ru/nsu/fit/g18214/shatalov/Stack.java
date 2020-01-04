package ru.nsu.fit.g18214.shatalov;

import java.util.NoSuchElementException;

public class Stack<T> {
  private StackElement<T> elemOfStack = null;
  private StackElement<T> head = null;
  Integer size = 0; //number of elements in stack

  public void push(T item) {
    size++;
    if (elemOfStack == null) {
      elemOfStack = new StackElement<>(item, null);
      head = elemOfStack;
    } else {
      elemOfStack = new StackElement<>(item, elemOfStack);
    }
  }

  /**
   * Take element from stack.
   * @return taken element
   */
  public T pop() {
    if (size == 0) {
      throw new NoSuchElementException("Stack is empty");
    }
    T elem = elemOfStack.getElement();
    elemOfStack = elemOfStack.getPredE();
    if (elemOfStack != null) {
      elemOfStack.setNextE(null);
    }
    size--;
    if (size == 0) {
      head = null;
    }
    return elem;
  }

  /**
   * .
   * @return number of elements in stack.
   */
  public Integer count() {
    return size;
  }
}