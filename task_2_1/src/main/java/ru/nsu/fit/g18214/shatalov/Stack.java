package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Stack {
  List<Object> stack = new ArrayList<>();
  Integer size = 0; //number of elements in stack

  public void push(Object item) {
    stack.add(item);
    size++;
  }

  /**
   * Take element from stack.
   * @return taken element
   */
  public Object pop() {
    if (size == 0) {
      throw new NoSuchElementException("Stack is empty");
    }
    Object elem = stack.get(size - 1);
    stack.remove(size - 1);
    size--;
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