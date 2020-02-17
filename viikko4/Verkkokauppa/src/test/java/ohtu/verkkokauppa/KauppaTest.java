package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

  Pankki pankki;
  Viitegeneraattori viite;
  Varasto varasto;
  Kauppa k;

  @Before
  public void setUp() {
    pankki = mock(Pankki.class);

    viite = mock(Viitegeneraattori.class);
    when(viite.uusi()).thenReturn(42);

    varasto = mock(Varasto.class);
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.saldo(2)).thenReturn(10);
    when(varasto.saldo(3)).thenReturn(0);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kirja", 25));
    when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "paita", 10));

    k = new Kauppa(varasto, pankki, viite);
  }

  @Test
  public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
  }

  @Test
  public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }

  @Test
  public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreillaKunOstoksiaOnMonta() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 30);
  }

  @Test
  public void useanSamanOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
  }

  @Test
  public void yhdenTuotteenPuuttuessaVarastostaOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(3);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }

  @Test
  public void metodiAloitaAsiointiNollaaEdellisenOstoksenTiedot() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));
  }

  @Test
  public void pyydetaanUusiViiteJokaiseenMaksuun() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    verify(viite, times(1)).uusi();

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    verify(viite, times(2)).uusi();

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    verify(viite, times(3)).uusi();
  }

  @Test
  public void poistaKoristaPoistaaKorista() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.poistaKorista(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }
}
