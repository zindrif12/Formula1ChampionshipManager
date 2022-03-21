public abstract class Driver {

  private String location;
  private String name;
  private Team team;

  public Driver(String name, String location, Team team) {
    this.name = name;
    this.team = team;
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

}
