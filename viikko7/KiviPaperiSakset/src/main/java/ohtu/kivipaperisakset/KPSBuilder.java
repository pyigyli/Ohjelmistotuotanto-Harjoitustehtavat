package ohtu.kivipaperisakset;

public class KPSBuilder {
    
  private KPS kps;

  public KPSBuilder() {
    this.kps = new KPSPelaajaVsPelaaja();
  }

  public KPS build() {
    return this.kps;
  }

  public KPSBuilder ihmispelaaja() {
    this.kps = new KPSPelaajaVsPelaaja();
    return this;
  }

  public KPSBuilder tekoaly() {
    this.kps = new KPSTekoaly();
    return this;
  }

  public KPSBuilder parempiTekoaly() {
    this.kps = new KPSParempiTekoaly();
    return this;
  }
}
