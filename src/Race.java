import java.util.ArrayList;

// This class has a date and a list of position to represent each race
public class Race {

  public int year;
  public int month;
  public int date;
  public ArrayList<Position> positions = new ArrayList<>();

  public Race(int year, int month, int date) {
    this.year = year;
    this.month = month;
    this.date = date;
  }



  public void addPosition(Formula1Driver driver, int position) {
    Position driverPosition = new Position(driver, position);
    positions.add(driverPosition);
  }

  public ArrayList<Position> getPositions() {
    return positions;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDate() {
    return date;
  }
}
