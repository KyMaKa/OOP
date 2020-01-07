package ru.nsu.fit.g18214.shatalov;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class TestStack {
  @Test
  public void test_int(){
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(13);
    stack.push(0);
    Object elem = stack.pop();
    Assert.assertEquals(3, (Object) stack.count());
    Assert.assertEquals(0, elem);
  }

  @Test
  public void test_string(){
    Stack<String> stack = new Stack<String>();
    stack.push("Abc");
    stack.push("Cba");
    stack.push("qwerty");
    Assert.assertEquals(3, (Object) stack.count());
    Object elem = stack.pop();
    Assert.assertEquals("qwerty", elem);
    Assert.assertEquals(2, (Object) stack.count());
  }

  @Test
  public void test_diff(){
    Stack<java.io.Serializable> stack = new Stack<>();
    stack.push(3);
    stack.push("asd");
    stack.push('a');
    Assert.assertEquals(3, (Object) stack.count());
    Object elem = stack.pop();
    Assert.assertEquals('a', elem);
    elem = stack.pop();
    Assert.assertEquals("asd", elem);
    elem = stack.pop();
    Assert.assertEquals(3, elem);
    Assert.assertEquals(0, (Object) stack.count());
  }

  @Test
  public void test_null(){
    Stack stack = new Stack();
    stack.push(null);
    stack.push(null);
    stack.push(null);
    Assert.assertEquals((Object) 0, (Object) stack.count());
  }

  @Test
  public void iterator_test() {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }
    Iterator<Integer> iterator = stack.iterator();
    for (int i = 1; i <= 10; i++) {
      int item = iterator.next();
      Assert.assertEquals((Object) (10 - i), (Object) item);
    }
    Assert.assertFalse(iterator.hasNext());
  }
}
