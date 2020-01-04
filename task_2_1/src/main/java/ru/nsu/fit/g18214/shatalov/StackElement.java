package ru.nsu.fit.g18214.shatalov;

public class StackElement<T> {
  private T hlp;
  private StackElement<T> nextE;
  private StackElement<T> predE;

  StackElement(T newElem, StackElement<T> pred) {
    predE = pred;
    hlp = newElem;
    if (pred != null) {
      pred.nextE = this;
    }
  }

  void setNextE(StackElement<T> next) {
    nextE = next;
    if (next != null) {
      next.predE = this;
    }
  }

  void setPredE(StackElement<T> pred) {
    predE = pred;
    pred.nextE = this;
  }

  T getElement() {
    return hlp;
  }

  StackElement<T> getPredE() {
    return predE;
  }

  StackElement<T> getNextE() {
    return nextE;
  }
}
