package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

  private int undoLuku;

  public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
    super(tuloskentta, syotekentta, nollaa, undo);
    this.undoLuku = 0;
  }

  @Override
  public void suorita() {
    try {
      this.undoLuku = Integer.parseInt(this.tuloskentta.getText());
    } catch (Exception e) {}
    this.nollaa.disableProperty().set(true);
    this.undo.disableProperty().set(false);
    this.syotekentta.setText("");
    this.tuloskentta.setText("0");
  }

  @Override
  public void peru() {
    this.nollaa.disableProperty().set(false);
    this.undo.disableProperty().set(true);
    this.tuloskentta.setText("" + this.undoLuku);
  }
}
