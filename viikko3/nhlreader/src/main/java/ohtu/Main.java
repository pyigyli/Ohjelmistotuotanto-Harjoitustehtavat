package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) throws IOException {
    String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
    String bodyText = Request.Get(url).execute().returnContent().asString();
    // System.out.println("json-muotoinen data:");
    // System.out.println(bodyText);
    Gson mapper = new Gson();
    Player[] players = mapper.fromJson(bodyText, Player[].class);
    // System.out.println("Oliot:");
    // for (Player player : players) {
    //   System.out.println(player);
    // }
    System.out.println("Suomalaiset pelaajat:");
    Player[] finnishPlayers = Arrays.stream(players).filter(player -> player.getNationality().contains("FIN")).toArray(Player[]::new);
    Arrays.sort(finnishPlayers, (Player a, Player b) -> b.getGoals() + b.getAssists() - a.getGoals() - a.getAssists());
    for (Player player : finnishPlayers) {
      System.out.println(player);
    }
  }
}
