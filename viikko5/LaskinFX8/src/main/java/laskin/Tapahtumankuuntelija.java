package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.*;

public class Tapahtumankuuntelija implements EventHandler {
  private Button undo;

  private Map<Button, Komento> komennot;
  private Komento edellinen = null;
 

  public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
    this.undo = undo;
    this.komennot = new HashMap<>();
    this.komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo));
    this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo));
    this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo));
  }

  @Override
  public void handle(Event event) {
    if (event.getTarget() != this.undo) {
      Komento komento = this.komennot.get((Button) event.getTarget());
      komento.suorita();
      this.edellinen = komento;
    } else {
      this.edellinen.peru();
      this.edellinen = null;
    }
  }
}
