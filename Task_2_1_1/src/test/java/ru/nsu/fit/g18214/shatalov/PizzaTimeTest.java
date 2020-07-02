package ru.nsu.fit.g18214.shatalov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import org.junit.Assert;
import org.junit.Test;

public class PizzaTimeTest {
  @Test(expected = NullPointerException.class)
  public void TestEmptyFile() throws IOException {
    PizzaTime pz = new PizzaTime();
    pz.read(new File("testEmpty.json"));
  }

  @Test
  public void TestReadBakers() throws IOException {
    PizzaTime pz = new PizzaTime();
    pz.read(new File("stuff.json"));
    Assert.assertEquals(4, pz.bakers.size());
  }

  @Test
  public void TestReadDelivery() throws IOException {
    PizzaTime pz = new PizzaTime();
    pz.read(new File("stuff.json"));
    Assert.assertEquals(1, pz.deliverys.size());
  }
}
