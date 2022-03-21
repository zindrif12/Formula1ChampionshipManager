public class Formula1Driver extends Driver {

  private int numOfFirstPositions;
  private int numOfSecondPositions;
  private int numOfThirdPositions;
  private int points = 0;
  private int numOfRaces;

  public Formula1Driver(String name, String location, Team team) {
    super(name, location, team);
  }

  public int getNumOfFirstPositions() {
    return numOfFirstPositions;
  }

  public int getNumOfSecondPositions() {
    return numOfSecondPositions;
  }

  public int getNumOfThirdPositions() {
    return numOfThirdPositions;
  }

  public int addPoints(int position) {
    numOfRaces++;
    addToPosition(position);
    points += pointsToAdd(position);
    return points;
  }

  public int getNumOfPositions() {
    return numOfFirstPositions + numOfSecondPositions + numOfThirdPositions;
  }

  public int getPoints() {
    return points;
  }

  public int getNumOfRaces() {
    return numOfRaces;
  }

  public void setNumOfRaces(int numOfRaces) {
    this.numOfRaces = numOfRaces;
  }

  private int pointsToAdd(int position) {
    int toAdd = 0;

    switch (position) {
      case 1:
        toAdd = 25;
        break;
      case 2:
        toAdd = 18;
        break;
      case 3:
        toAdd = 15;
        break;
      case 4:
        toAdd = 12;
        break;
      case 5:
        toAdd = 10;
        break;
      case 6:
        toAdd = 8;
        break;
      case 7:
        toAdd = 6;
        break;
      case 8:
        toAdd = 4;
        break;
      case 9:
        toAdd = 2;
        break;
      case 10:
        toAdd=1;
        break;
    }

    return toAdd;
  }

  public void addToPosition(int position) {
    switch (position) {
      case 1:
        numOfFirstPositions++;
        break;
      case 2:
        numOfSecondPositions++;
        break;
      case 3:
        numOfThirdPositions++;
        break;
    }
  }
}
