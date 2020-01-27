package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {

  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();
 
      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri",   "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));
 
      return players;
    }
  };

  Statistics stats;

  @Before
  public void setUp(){
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  @Test
  public void pelaajaLoytyy() {
    assertEquals(new Player("Semenko", "EDM", 4, 12).toString(), stats.search("Semenko").toString());
  }

  @Test
  public void pelaajaaEiLoydy() {
    assertEquals(null, stats.search("Teppo"));
  }

  @Test
  public void tiiminPelaajatLoytyy() {
    assertEquals(new Player("Kurri",   "EDM", 37, 53).toString(), stats.team("EDM").get(1).toString());
  }

  @Test
  public void huippuSkoraajatToimii() {
    assertEquals(new Player("Gretzky", "EDM", 35, 89).toString(), stats.topScorers(1).get(0).toString());
  }
}
