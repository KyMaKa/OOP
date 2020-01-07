package ru.nsu.fit.g18214.shatalov;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
  private String string;
  private String time;
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  public Note() {}
  public String getString() {
    return string;
  }

  private void setTime(){
    this.time = formatter.format(new Date());
  }

  public void setString(String string) {
    setTime();
    this.string = string;
  }

  public String getTime(){
    return time;
  }

  public String toString() {
    return "[string:" + string + ", time:" + time + "]";
  }
}
