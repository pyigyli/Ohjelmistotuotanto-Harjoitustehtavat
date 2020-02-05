package ohtu;

public class Player {

  private String name;
  private String team;
  private String nationality;
  private int goals;
  private int assists;
  private int penalties;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public String getTeam() {
    return this.team;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getNationality() {
    return this.nationality;
  }

  public void setGoals(int goals) {
    this.goals = goals;
  }

  public int getGoals() {
    return this.goals;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }

  public int getAssists() {
    return this.assists;
  }

  public void setPenalties(int penalties) {
    this.penalties = penalties;
  }

  public int getPenalties() {
    return this.penalties;
  }

  @Override
  public String toString() {
    return this.name + " team " + this.team + " nationality " + this.nationality + " goals " + this.goals + " assists " + this.assists;
  }
}
