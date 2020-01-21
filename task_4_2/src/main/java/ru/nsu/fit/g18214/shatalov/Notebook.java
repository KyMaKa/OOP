package ru.nsu.fit.g18214.shatalov;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.cli.*;

import java.io.*;
import java.sql.ClientInfoStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Notebook {

  public List<Note> nbook = new ArrayList<>();
  public int last;
  public List<String> list = new ArrayList<>()  ;

  ObjectMapper mapper = new ObjectMapper();
  File file = new File("Notebook.json");
  ArrayNode arrayNode;
  ObjectNode obj = mapper.createObjectNode();
  public Notebook() throws IOException {
    if (file.length() != 0) {
      arrayNode = (ArrayNode) mapper.readTree(file);
      for (int i = 0; i < arrayNode.size(); i++) {
        this.nbook.add(mapper.readValue(arrayNode.get(i).toString(), Note.class));
        last++;
      }
    } else {
      arrayNode = obj.putArray(mapper.writeValueAsString(" "));
      this.last = 0;
    }
  }

  public static void main(String[] args) throws IOException {
    Notebook nb = new Notebook();
    CommandLineParser parser = new DefaultParser();
    Options options = initOpts();
    System.out.println("Notebook opened.");
    try {
      CommandLine line = parser.parse(options, args);
      if (line.hasOption("a")) {
        String nArgs = line.getOptionValue("a");
        nb.addNote(nArgs);
        return;
      }
      if (line.hasOption("rm")) {
        String rArg = line.getOptionValue("rm");
        nb.deleteNode(rArg);
        return;
      }
      if (line.hasOption("s")) {
        nb.show();
        return;
      }
      if (line.hasOption("si")) {
        String[] sArgs = line.getOptionValues("si");
        nb.showInterval(sArgs);
        return;
      }
      if (line.hasOption("h")) {
        System.out.println("Available options:");
        System.out.println("\"-a\" - Add new note. Has one argument. Example: -a \"Your note\"");
        System.out.println("\"-s\" - Show all notes (newest first). Has no arguments.");
        System.out.println("\"-rm\" - Remove note. Has one argument. Example: -rm \"Your note\"");
        System.out.println("\"-si\" - Show all notes in given date interval with given substring(s)." +
            " Has minimum 3 arguments: date after, date before and substring. " +
            "Example: -si \"06/01/2020 18:08:25\" \"07/01/2020 19:16:00\" \"Your\"");
        return;
      }
      System.out.println("Please enter option \"-h\" for help.");
    } catch (Exception exp) {
      System.out.println("Error occurred: " + exp.getMessage());
    }
    System.out.println("Notebook closed.");
  }

  public static Options initOpts() {
    Options options = new Options();
    options.addOption(
        "s", "show", false, "Show all notes.");
    Option newOpt =
        new Option("a", "add", true, "Add new note.");
    newOpt.setArgs(1);
    options.addOption(newOpt);
    options.addOption("rm", "remove", true, "Remove note.");
    Option incOpt =
        new Option(
            "si",
            "includes",
            true,
            "Filter notes by date and included substrings.");
    incOpt.setArgs(Option.UNLIMITED_VALUES);
    options.addOption(incOpt);
    options.addOption("h", "help", false, "Show available options.");
    return options;
  }

  public void addNote(String notes) throws IOException {
    Note note = new Note();
    note.setString(notes);
    nbook.add(note);
    arrayNode.add(mapper.valueToTree(note));
    last++;
    updateJsonFile();
    System.out.println("Added new note!");
  }

  public void deleteNode(String string) throws IOException {
    boolean foundNote = false;
    for (int i = 0; i < arrayNode.size(); i++) {
      if (arrayNode.get(i).get("string").asText().equals(string)) {
        arrayNode.remove(i);
        last--;
        foundNote = true;
      }
    }
    if (!foundNote) {
      System.out.println("No such note");
      return;
    }
    System.out.println("Note removed!");
    updateJsonFile();
  }

  public void show() {
    for (int i = arrayNode.size() - 1; i >= 0; i--) {
      list.add(arrayNode.get(i).get("string").asText());
    }
    writeOnConsole(list);
  }

  public void showInterval(String[] args) throws ParseException {
    String dateIn1 = args[0];
    String dateIn2 = args[1];

    String[] keyWords = new String[args.length - 2];
    for (int i = 2; i < args.length; i++) {
      int j = 0;
      keyWords[j++] = args[i];
    }

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date1 = formatter.parse(dateIn1);
    Date date2 = formatter.parse(dateIn2);
    boolean hasNote = false;
    for (int i = arrayNode.size() - 1; i >= 0; i--) {

      if (formatter.parse(arrayNode.get(i).get("time").asText()).compareTo(date2) <= 0 &&
          formatter.parse(arrayNode.get(i).get("time").asText()).compareTo(date1) >= 0) {
        int c = 0;

        for (String s : keyWords) {
          if (arrayNode.get(i).get("string").asText().contains(s)) {
            c++;
          }
          if (c == keyWords.length) {
            list.add(arrayNode.get(i).get("string").asText());
            hasNote = true;
          }
        }
      }
    }
    if (!hasNote) {
      System.out.println("No notes with such criteria");
    }
    writeOnConsole(list);
  }

  private void updateJsonFile() throws IOException {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //arrayNode.add(mapper.valueToTree(nbook.get(last - 1)));
    mapper.writeValue(file, arrayNode);
  }

  private void writeOnConsole(List string) {
    if (string.size() == 0) {
      System.out.println("Notebook is empty.");
    }
    for (int i = 0; i < string.size(); i++) {
      System.out.println(string.get(i));
    }
  }
}
