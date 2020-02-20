package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

  private int undoLuku;

  public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
    super(tuloskentta, syotekentta, nollaa, undo);
    this.undoLuku = 0;
  }

  @Override
  public void suorita() {
    int laskunTulos = 0;
    try {
      laskunTulos = Integer.parseInt(this.tuloskentta.getText()) + Integer.parseInt(this.syotekentta.getText());
    } catch (Exception e) {}
    this.undoLuku = Integer.parseInt(this.syotekentta.getText());
    this.nollaa.disableProperty().set(false);
    this.undo.disableProperty().set(false);
    this.syotekentta.setText("");
    this.tuloskentta.setText("" + laskunTulos);
  }

  @Override
  public void peru() {int laskunTulos = 0;
    try {
      laskunTulos = Integer.parseInt(this.tuloskentta.getText()) - this.undoLuku;
    } catch (Exception e) {}
    this.undo.disableProperty().set(true);
    this.tuloskentta.setText("" + laskunTulos);
  }
}
