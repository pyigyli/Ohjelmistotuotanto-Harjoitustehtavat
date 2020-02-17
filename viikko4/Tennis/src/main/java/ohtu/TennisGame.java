package ohtu;

public class TennisGame {

  private int player1Score = 0;
  private int player2Score = 0;
  private String player1Name;
  private String player2Name;

  public TennisGame(String player1Name, String player2Name) {
    this.player1Name = player1Name;
    this.player2Name = player2Name;
  }

  public void wonPoint(String playerName) {
    if (playerName == this.player1Name) this.player1Score += 1;
    if (playerName == this.player2Name) this.player2Score += 1;
  }

  public String getScore() {
    if (this.player1Score == this.player2Score)
      return this.getScoreWhenDraw();
    if (this.player1Score >= 4 || this.player2Score >= 4)
      return this.getScoreWhenScoreAboveThree();
    return this.scoreToWord(this.player1Score) + "-" + this.scoreToWord(this.player2Score);
  }

  private String getScoreWhenDraw() {
    switch (this.player1Score) {
      case 0:
        return "Love-All";
      case 1:
        return "Fifteen-All";
      case 2:
        return "Thirty-All";
      case 3:
        return "Forty-All";
      default:
        return "Deuce";
    }
  }

  private String getScoreWhenScoreAboveThree() {
    int scoreDifference = this.player1Score - this.player2Score;
    if (scoreDifference == 1)
      return "Advantage " + this.player1Name;
    else if (scoreDifference == -1)
      return "Advantage " + this.player2Name;
    else if (scoreDifference >= 2)
      return "Win for " + this.player1Name;
    else
      return "Win for " + this.player2Name;
  }

  private String scoreToWord(int score) {
    switch (score) {
      case 0:
        return "Love";
      case 1:
        return "Fifteen";
      case 2:
        return "Thirty";
      case 3:
        return "Forty";
      default:
        return "";
    }
  }
}
