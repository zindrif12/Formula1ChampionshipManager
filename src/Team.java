// This class has a driver and a team name
public class Team {
  private Formula1Driver driver;
  private final String name;

  public Team(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public Formula1Driver getDriver() {
    return this.driver;
  }

  public void setDriver(Formula1Driver driver) {
    this.driver = driver;
  }
}
