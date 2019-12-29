package ru.nsu.fit.g18214.shatalov;

import org.junit.Assert;
import org.junit.Test;

public class TestStack {
  @Test
  public void test_int(){
    Stack stack = new Stack();
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
    Stack stack = new Stack();
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
    Stack stack = new Stack();
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

  }
}
