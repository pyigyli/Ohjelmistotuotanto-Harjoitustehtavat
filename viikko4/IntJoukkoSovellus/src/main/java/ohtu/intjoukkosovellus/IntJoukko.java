
package ohtu.intjoukkosovellus;

public class IntJoukko {

  public final static int KAPASITEETTI = 5;
  public final static int OLETUSKASVATUS = 5;
  private int kasvatuskoko;
  private int[] lukujoukko;
  private int joukonKoko;

  public IntJoukko() {
    this.initialize(this.KAPASITEETTI, this.OLETUSKASVATUS);
  }

  public IntJoukko(int kapasiteetti) {
    this.initialize(kapasiteetti, this.OLETUSKASVATUS);
  }

  public IntJoukko(int kapasiteetti, int kasvatuskoko) {
    this.initialize(kapasiteetti, kasvatuskoko);
  }

  private void initialize(int kapasiteetti, int kasvatuskoko) {
    if (kapasiteetti < 0) {
      throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen luku");
    }
    if (kasvatuskoko < 0) {
      throw new IndexOutOfBoundsException("kasvatuskoko ei voi olla negatiivinen luku");
    }
    this.lukujoukko = new int[kapasiteetti];
    for (int i = 0; i < this.lukujoukko.length; i++) {
      this.lukujoukko[i] = 0;
    }
    this.joukonKoko = 0;
    this.kasvatuskoko = kasvatuskoko;
  }

  public boolean lisaa(int luku) {
    if (kuuluu(luku)) {
      return false;
    }
    this.lukujoukko[this.joukonKoko] = luku;
    this.joukonKoko++;
    if (this.joukonKoko % this.lukujoukko.length == 0) {
      int[] uusiTaulukko = new int[this.joukonKoko + this.kasvatuskoko];
      kopioiTaulukko(this.lukujoukko, uusiTaulukko);
      this.lukujoukko = uusiTaulukko;
    }
    return true;
  }

  public boolean kuuluu(int luku) {
    for (int i = 0; i < this.joukonKoko; i++) {
      if (luku == this.lukujoukko[i]) {
        return true;
      }
    }
    return false;
  }

  public boolean poista(int luku) {
    for (int i = 0; i < this.joukonKoko; i++) {
      if (luku == this.lukujoukko[i]) {
        shiftElementsToPrev(i);
        this.joukonKoko--;
        return true;
      }
    }
    return false;
  }

  private void shiftElementsToPrev(int startPoint) {
    for (int j = startPoint; j < this.joukonKoko - 1; j++) {
      this.lukujoukko[j] = this.lukujoukko[j + 1];
    }
    this.lukujoukko[this.joukonKoko - 1] = 0;
  }

  private void kopioiTaulukko(int[] vanha, int[] uusi) {
    for (int i = 0; i < vanha.length; i++) {
      uusi[i] = vanha[i];
    }
  }

  public int mahtavuus() {
    return this.joukonKoko;
  }

  @Override
  public String toString() {
    if (this.joukonKoko == 0) {
      return "{}";
    } else if (this.joukonKoko == 1) {
      return "{" + this.lukujoukko[0] + "}";
    } else {
      String tuotos = "{";
      for (int i = 0; i < this.joukonKoko - 1; i++) {
        tuotos += this.lukujoukko[i];
        tuotos += ", ";
      }
      tuotos += this.lukujoukko[this.joukonKoko - 1];
      tuotos += "}";
      return tuotos;
    }
  }

  public int[] toIntArray() {
    int[] taulu = new int[this.joukonKoko];
    for (int i = 0; i < taulu.length; i++) {
      taulu[i] = this.lukujoukko[i];
    }
    return taulu;
  }

  public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
    IntJoukko palauteTaulu = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      palauteTaulu.lisaa(aTaulu[i]);
    }
    for (int i = 0; i < bTaulu.length; i++) {
      palauteTaulu.lisaa(bTaulu[i]);
    }
    return palauteTaulu;
  }

  public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
    IntJoukko palauteTaulu = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      for (int j = 0; j < bTaulu.length; j++) {
        if (aTaulu[i] == bTaulu[j]) {
          palauteTaulu.lisaa(bTaulu[j]);
        }
      }
    }
    return palauteTaulu;
  }
      
  public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
    IntJoukko palauteTaulu = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      palauteTaulu.lisaa(aTaulu[i]);
    }
    for (int i = 0; i < bTaulu.length; i++) {
      palauteTaulu.poista(bTaulu[i]);
    }
    return palauteTaulu;
  }
}
