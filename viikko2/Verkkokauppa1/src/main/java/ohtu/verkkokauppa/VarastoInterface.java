package ohtu.verkkokauppa;

import java.util.*;

public interface VarastoInterface {
  Tuote haeTuote(int id);
  int saldo(int id);
  void otaVarastosta(Tuote t);
  void palautaVarastoon(Tuote t);
}
