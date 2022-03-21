// This class has position and driver that is added to each position in a race
public class Position {
    private final Formula1Driver driver;
    private final int position;

    public Position(Formula1Driver driver, int position) {
        this.driver = driver;
        this.position = position;
    }

    public Formula1Driver getDriver() {
        return this.driver;
    }

    public int getPosition() {
        return this.position;
    }
}
