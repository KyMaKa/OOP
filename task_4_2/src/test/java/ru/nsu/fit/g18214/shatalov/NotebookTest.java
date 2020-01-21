package ru.nsu.fit.g18214.shatalov;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotebookTest {
  Notebook gen = new Notebook();

  public NotebookTest() throws IOException {
  }

  @Test
  public void testAdd() throws IOException {
    gen.addNote("First note");
    Assert.assertTrue(gen.arrayNode.get(gen.last - 1).get("string").asText().equals("First note"));
  }

  @Test
  public void testDeleteNote() throws IOException {
    gen.deleteNode("First note");
    boolean foundNote = false;
    for (int i = 0; i < gen.arrayNode.size(); i++) {
      if (gen.arrayNode.get(i).get("string").asText().equals("First note")) {
        gen.arrayNode.remove(i);
        foundNote = true;
      }
    }
    Assert.assertFalse(foundNote);
  }

  @Test
  public void testShow() throws IOException {
    gen.addNote("First");
    gen.addNote("Second");
    gen.addNote("Third");
    gen.show();
    List<String> expected = new ArrayList<>();
    expected.add("Third");
    expected.add("Second");
    expected.add("First");
    boolean ok = true;
    for (int i = 0; i < expected.size(); i++) {
      if (!expected.get(i).equals(gen.list.get(i))) {
        ok = false;
        break;
      }
    }
    Assert.assertTrue(ok);
  }
}

