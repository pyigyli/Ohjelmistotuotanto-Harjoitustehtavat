package ohtu.io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

  private List<String> lines;
  private int i;
  private ArrayList<String> prints;

  public StubIO(List<String> values) {
    this.lines = values;
    this.prints = new ArrayList<>();
  }

  public void print(String toPrint) {
    this.prints.add(toPrint);
  }

  public int readInt(String prompt) {
    this.print(prompt);
    return Integer.parseInt(this.lines.get(this.i++));
  }

  public ArrayList<String> getPrints() {
    return this.prints;
  }

  public String readLine(String prompt) {
    this.print(prompt);
    if (this.i < this.lines.size()) {
      return this.lines.get(this.i++);
    }
    return "";
  }
}
