package ru.nsu.fit.g18214.shatalov;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class QueueTest {
  @Test
  public void testSingle() {
    Queue<Integer, Integer> q = new Queue<>();
    q.insert(1,1);
    Assert.assertEquals(1, q.size());
    Assert.assertEquals((Object) 1, q.extractMax());
    Assert.assertEquals(0, q.size());
  }

  @Test
  public void testString(){
    Queue<Integer, String> q = new Queue<>();
    q.insert(12, "12");
    q.insert(11, "11");
    q.insert(1, "1");
    q.insert(321, "321");
    q.insert(333, "331");
    Assert.assertEquals(5, q.size());
    Assert.assertEquals("331", q.extractMax());
    Assert.assertEquals(4, q.size());
    Assert.assertEquals("1", q.extractMin());
  }
  @Test
  public void testStringAndChar(){
    Queue<Character, String> q = new Queue<>();
    q.insert('c', "12");
    q.insert('b', "11");
    q.insert('a', "1");
    q.insert('r', "321");
    q.insert('z', "331");
    Assert.assertEquals(5, q.size());
    Assert.assertEquals("331", q.extractMax());
    Assert.assertEquals(4, q.size());
    Assert.assertEquals("1", q.extractMin());
  }
  @Test
  public void testInt(){
    Queue<Integer, Integer> q = new Queue<>();
    q.insert(12, 12);
    q.insert(11, 11);
    q.insert(1, 1);
    q.insert(321, 321);
    q.insert(333, 331);
    Assert.assertEquals(5, q.size());
    Assert.assertEquals((Object) 331, q.extractMax());
    Assert.assertEquals(4, q.size());
    Assert.assertEquals((Object) 1, q.extractMin());
  }
  @Test
  public void nullptrTest(){
    Queue<Integer, Integer> q = new Queue<>();
    q.insert(20, null);
    q.insert(null, 20);
    q.insert(null,null);
    Assert.assertTrue(q.size() == 0);
  }
  @Test
  public void iteratorTest() {
    Queue<Integer, Integer> q = new Queue<>();
    for (int i = 0; i < 10; i++) {
      q.insert(i, i);
    }
    Iterator<Integer> iterator = q.iterator();
    for (int i = 0; i < 10; i++) {
      int next = iterator.next();
      Assert.assertEquals(i, next);
    }
    Assert.assertEquals(false, iterator.hasNext());
  }
}
