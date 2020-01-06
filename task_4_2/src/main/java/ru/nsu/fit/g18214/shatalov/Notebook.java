package ru.nsu.fit.g18214.shatalov;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Notebook {
  File file = new File("Notebook.json");

  public static void main(String args[]) {
    Notebook gen = new Notebook();
    //map json to note
    try {
      List<Note> listOfNotes = new ArrayList<>();
      Note note = new Note();
      Note note2 = new Note();
      Note note3 = new Note();
      note2.setString("dream Do");
      //note.setString("Scuby Do");
      //note3.setString("Do hast dream");
      //listOfNotes.add(note);
      listOfNotes.add(note2);
      //listOfNotes.add(note3);
      for (int i = 0; i < listOfNotes.size(); i++) {
        gen.writeNote(listOfNotes.get(i));
      }
      //gen.deleteNode("Minecraft");
      gen.showInterval("06/01/2020 18:46:00", "06/01/2020 19:46:00", "dream", "Do");
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
  }

  public void writeNote(Note note) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    ArrayNode arrayNode;
    arrayNode = (ArrayNode) mapper.readTree(file);
    arrayNode.add(mapper.valueToTree(note));
    mapper.writeValue(file, arrayNode);
  }

  public void deleteNode(String string) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    ArrayNode arrayNode;
    arrayNode = (ArrayNode) mapper.readTree(file);
    for (int i = 0; i < arrayNode.size(); i++) {
      if (arrayNode.get(i).get("string").asText().equals(string)) {
        arrayNode.remove(i);
      }
    }
    mapper.writeValue(file, arrayNode);
  }

  public void show() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    ArrayNode arrayNode;
    arrayNode = (ArrayNode) mapper.readTree(file);
    for (int i = arrayNode.size() - 1; i >= 0; i--) {
      System.out.println(arrayNode.get(i).get("string").asText());
    }
  }

  public void showInterval(String dateIn1, String dateIn2, String... keyWords) throws IOException, ParseException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    ArrayNode arrayNode;
    arrayNode = (ArrayNode) mapper.readTree(file);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date1 = formatter.parse(dateIn1);
    Date date2 = formatter.parse(dateIn2);

    for (int i = arrayNode.size() - 1; i >= 0; i--) {

      if (formatter.parse(arrayNode.get(i).get("time").asText()).compareTo(date2) <= 0 &&
          formatter.parse(arrayNode.get(i).get("time").asText()).compareTo(date1) >= 0) {
        int c = 0;

        for (String s : keyWords) {
          if (arrayNode.get(i).get("string").asText().contains(s)) {
            c++;
          }
          if (c == keyWords.length) {
            System.out.println(arrayNode.get(i).get("string").asText());
          }
        }
      }
    }
  }
}
