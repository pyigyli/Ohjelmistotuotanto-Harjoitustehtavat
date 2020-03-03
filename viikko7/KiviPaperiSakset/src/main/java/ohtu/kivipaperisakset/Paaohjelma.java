package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    while (true) {
      System.out.println(
        "\nValitse pelataanko" +
        "\n (a) ihmistä vastaan " +
        "\n (b) tekoälyä vastaan" +
        "\n (c) parannettua tekoälyä vastaan" +
        "\nmuilla valinnoilla lopetataan"
      );

      KPS peli;
      
      String vastaus = scanner.nextLine();
      if (vastaus.endsWith("a")) {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        peli = new KPSBuilder().ihmispelaaja().build();
      } else if (vastaus.endsWith("b")) {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        peli = new KPSBuilder().tekoaly().build();
      } else if (vastaus.endsWith("c")) {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        peli = new KPSBuilder().parempiTekoaly().build();
      } else {
        break;
      }

      peli.pelaa();
    }
  }
}
