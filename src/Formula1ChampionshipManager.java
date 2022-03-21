import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {
    Gui gui=new Gui();
    private final ArrayList<Team> teams = new ArrayList<>(); // This ArrayList holds all the added teams
    private final ArrayList<Race> races = new ArrayList<>(); // This ArrayList holds all the added races
    private Scanner scanner = null;

    public void app() {
        displayMenu();
    }

    public void displayMenu() {
        boolean exitMenu = false;

        //Getting user inputs
        while (!exitMenu) {
            println("");
            println("****************************************");
            println("Formula 1 Championship Menu List");
            println("****************************************");
            println("Add driver (AD)");
            println("Change Driver (CD)");
            println("View Drivers Table (DT)");
            println("View Driver Stats (DS)");
            println("Add Team (AT)");
            println("View Teams (VT)");
            println("Remove Team (RT)");
            println("Add race (AR)");
            println("Save all Details (SF)");
            println("Read Files (RF)");
            println("GUI (G)");
            println("Exit (E)");
            println("****************************************");

            String input = getUserInput("Input your choice: ");
            if (input.equalsIgnoreCase("AD")) {
                addDriverToTeam();
            } else if (input.equalsIgnoreCase("CD")) {
                changeTeamDriver();
            } else if (input.equalsIgnoreCase("DT")) {
                displayDriversTable();
            } else if (input.equalsIgnoreCase("DS")) {
                viewDriverStats();
            } else if (input.equalsIgnoreCase("AT")) {
                addTeam();
            } else if (input.equalsIgnoreCase("VT")) {
                viewTeamDetails();
            } else if (input.equalsIgnoreCase("RT")) {
                removeTeam();
            } else if (input.equalsIgnoreCase("AR")) {
                addRaceResults();
            } else if (input.equalsIgnoreCase("SF")) {
                save();
            } else if (input.equalsIgnoreCase("RF")) {
                read();
            } else if (input.equalsIgnoreCase("G")) {
                gui.displayFrame(teams,races);
            } else if (input.equalsIgnoreCase("E")) {
                exitMenu = true;
                if (scanner != null) {
                    scanner.close();
                }
            } else {
                println("Invalid input.");
            }
        }
    }


    @Override
    public void addDriverToTeam() {
        String message = "Enter new team name or available team to add/change driver to: "; //Ternary Operator

        if (teams.isEmpty()) {
            message = "Enter team name: ";
        }

        viewTeamDetails(); //Prints existing Drivers list
        String teamName = getUserInput(message);
        String driverName = getUserInput("Enter driver name: ");
        String driverLocation = getUserInput("Enter driver location: ");

        Team team = findTeamByName(teamName); //Find Team by name

        if (team != null) {
            if (team.getDriver() != null) {         // If team is already exists user can replace it or add new team
                println(team.getDriver().getName());
                String userChoice = getUserInput("A driver already exists in that team. Replace? (Y/N): ");
                if (userChoice.equalsIgnoreCase("Y")) {
                    Formula1Driver driver = new Formula1Driver(driverName, driverLocation, team);
                    team.setDriver(driver);
                }
            } else {
                Formula1Driver driver = new Formula1Driver(driverName, driverLocation, team);
                team.setDriver(driver);
            }
        } else {
            team = new Team(teamName); //If new user add new team , team details are sorting  into "teams" array
            Formula1Driver driver = new Formula1Driver(driverName, driverLocation, team);
            team.setDriver(driver);
            teams.add(team);
        }
    }

    // Add race position and drivers by creating a Race object and adding it to the races List
    @Override
    public void addRaceResults() {
        try {
            System.out.println("Enter race Year");
            int year = scanner.nextInt();
            System.out.println("Enter race Month");
            int month = scanner.nextInt();
            System.out.println("Enter race Date");
            int date = scanner.nextInt();
            Race race = new Race(year, month, date);
            race = addPositionsToRace(race);
            races.add(race);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }

    }

    public Race addPositionsToRace(Race race) {
        int position = 1;
        int positions = teams.size();

        if (positions == 0) {
            println("No drivers found to add a race");
        } else {
            while ( position <= positions ) {
                viewDrivers();
                String driverName = getUserInput("Add driver to position: " + position + " (enter driver name): ");


                Formula1Driver driver = findDriverByName(driverName);

                if (driver != null) {
                    driver.addPoints(position);
                    race.addPosition(driver, position); //Race points will add to each driver from their positions
                    position++;         //while loop runs till it equals team array size
                } else {
                    println("Incorrect driver name");
                }

            }
        }

        return race;
    }

    // Adds a team to the teams list
    @Override
    public void addTeam() {
        viewTeamDetails(); // Displays available teams
        String teamName = getUserInput("Team name: ").trim(); //Trim functions used for removes whitespace from both ends of a string and returns a new string, without modifying the original string

        if (findTeamByName(teamName) != null) { // Checks if a team is already available in that name
            println("The team " + teamName + " already exists.");
        } else {
            teams.add(new Team(teamName));
        }
    }

    @Override
    public void changeTeamDriver() {
        viewTeamDetails();
        String teamName = getUserInput("Enter the team name you'd like to change the driver of: ");
        String driverName = getUserInput("Enter driver name: ");
        String driverLocation = getUserInput("Enter driver location: ");

        Team team = findTeamByName(teamName);

        if (team != null) {
            Formula1Driver driver = new Formula1Driver(driverName, driverLocation, team);
            team.setDriver(driver);
        } else {
            println("You've entered an incorrect team name.");
        }
    }

    // Displays drivers table using the teams list
    // Each team holds a driver and teams list has all the drivers
    @Override
    public void displayDriversTable() {
        //perform the bubble sort
        Team[] driversByPoints = new Team[teams.size()];
        Team temp;

        //loop to access each array element
        for (int i = 0; i < teams.size(); i++) {
            driversByPoints[i] = teams.get(i);
        }

        // This Bubble sort creates a sortable list
        //loop to compare array elements
        for (int j = 0; j < teams.size(); j++) {
            for (int k = j + 1; k < teams.size(); k++) {
                //sorting descending order
                if (driversByPoints[j].getDriver().getPoints() < driversByPoints[k].getDriver().getPoints()) {
                    //swap elements
                    temp = driversByPoints[j];
                    driversByPoints[j] = driversByPoints[k];
                    driversByPoints[k] = temp;
                } else if (driversByPoints[j].getDriver().getPoints() == driversByPoints[k].getDriver().getPoints()) {
                    if (driversByPoints[j].getDriver().getNumOfFirstPositions() < driversByPoints[k].getDriver().getNumOfFirstPositions()) {
                        temp = driversByPoints[j];
                        driversByPoints[j] = driversByPoints[k];
                        driversByPoints[k] = temp;
                    }
                }

            }
        }

        println("Driver\t\tTeam\t\tPoints");
        println("=======================================");
        //// driversByPoints
        for (Team driver : driversByPoints) {
            System.out.println(
                    driver.getDriver().getName() + "\t\t\t" +
                            driver.getDriver().getTeam().getName() + "\t\t\t" +
                            driver.getDriver().getPoints()
            );
        }
        println("=======================================");
    }




    // Finds a driver by the given name
    // This filters the teams list and compares the set driver names
    private Formula1Driver findDriverByName(String name) {
        Team driverTeam = teams.stream()
                .filter(team -> name.equalsIgnoreCase(team.getDriver().getName()))
                .findAny()
                .orElse(null);

        return driverTeam != null ? driverTeam.getDriver() : null;
    }

    // Finds a team by the given name
    // This filters the teams list and compares the team name
    private Team findTeamByName(String name) {
        return teams.stream()
                .filter(team -> name.equals(team.getName()))
                .findAny()
                .orElse(null);
    }


    // Removes a team by the name entered by the user
    @Override
    public void removeTeam() {
        viewTeamDetails();
        String teamName = getUserInput("Enter the name of the team to be removed: ");
        Team team = findTeamByName(teamName);

        if (team != null) {
            teams.remove(team);
            println("Team " + teamName + " has been removed");
        } else {
            println("Team " + teamName + " is not available");
        }
    }

    //this method displays existing drivers list
    @Override
    public void viewDrivers() {
        println("Driver name");
        println("============");

        for (Team team : teams) {
            println(team.getDriver().getName());
        }
        println("=============");
    }
    @Override
    public void viewDriverStats() {
        String driverName = getUserInput("Enter the driver name: ");
        Formula1Driver driver = findDriverByName(driverName);

        if (driver != null) {
            println("Drivers table");
            println("=============");
            //formatter used to write a formatted string to this object's destination
            System.out.printf("%11s %21s %21s %22s %25s %21s ","Name","Team","Points","First Positions","Second Positions","Third Positions");
            System.out.println();
            System.out.format("%12s %20s %20s %20d %20d %20d",driver.getName(),driver.getTeam().getName(),driver.getPoints(),driver.getNumOfFirstPositions(),driver.getNumOfSecondPositions(),driver.getNumOfThirdPositions());
            System.out.println();
        } else {
            println("Driver is not available");
        }
    }
    @Override
    public void viewTeamDetails() {
        println("========= Available Teams details =======");

        if (teams.isEmpty()) {
            println("\t\tNo teams available");
        } else {
            for (Team team : teams) {
                print(team.getName());

                if (team.getDriver() != null) {
                    println(" - " + team.getDriver().getName());
                } else {
                    println("");
                }
            }
        }

        println("==========================================");
    }

    // Common method to get user "String" input in one line
    public String getUserInput(String message) {
        scanner = new Scanner(System.in);
        println(message);
        return scanner.next();
    }

    // This shortens the command
    // Print message with a newline.
    private void println(String message) {
        System.out.println(message);
    }

    // This shortens the command
    // Print message without a newline
    private void print(String message) {
        System.out.print(message);
    }

    @Override
    public void save() {
        String formulaString = "--TEAMS--\n"; //This section save team array details in text file
        for (Team team : teams) {
            Formula1Driver driver = team.getDriver();
            // Add all room details in roomString variable that needs to be stored in the file
            formulaString += team.getName() + "," + driver.getName() + "," + driver.getLocation();
            formulaString += "\n"; // adds a new line instead of a separator so that it can be easily read as a line when reading
        }

        formulaString += "--RACES--\n"; //This section save races array details in text file
        for (Race race : races) {
            formulaString += race.getYear() + ":"+ race.getMonth()+":"+ race.getDate()+":";

            int currentPosition = 1;
            for (Position position : race.getPositions()) {
                formulaString += position.getDriver().getTeam().getName();
                if (currentPosition < race.getPositions().size()) {
                    formulaString += ",";
                }
                currentPosition++;
            }

            formulaString += "\n"; // adds a new line instead of a separator so that it can be easily read as a line when reading
        }

        store(formulaString);
        System.out.println("File creation successful.");
    }
    @Override
    public void store(String roomString) {
        String filename = "./formula.txt"; //Create text file to save details
        File file = new File(filename);
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(file, false);
            pw = new PrintWriter(fw, true);
            pw.println(roomString);
        } catch (FileNotFoundException e) {
            System.out.println("File not found please check the file"); //Error handling
        } catch (IOException e) {
            System.out.println("Something wrong with IO please check the file");
        } finally {
            try {
                fw.close();
                pw.close();
            } catch (IOException e) {
            }
        }

    }
    @Override
    public void read() {
        String fileName = "./formula.txt";

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            teams.clear();
            races.clear();

            String section = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!line.isEmpty()) {
                    if (line.equalsIgnoreCase("--TEAMS--")) {
                        section = "teams"; //
                        continue;
                    } else if (line.equalsIgnoreCase("--RACES--")) {
                        section = "races";
                        continue;
                    }

                    if (section.equalsIgnoreCase("teams")) {
                        String[] teamDetails = line.split(","); //split function is used for split and read element by element from saved file
                        String teamName = teamDetails[0]; //read team name from text file and store it in temporary array named "team details"
                        String driverName = teamDetails[1];
                        String driverLocation = teamDetails[2];

                        Team newTeam = new Team(teamName);
                        Formula1Driver driver = new Formula1Driver(driverName, driverLocation, newTeam); //pass Formula1Driver object  to the constructor.

                        newTeam.setDriver(driver);
                        teams.add(newTeam);
                    } else if (section.equalsIgnoreCase("races")) {
                        String[] raceDetails = line.split(":");
                        int raceYear = Integer.parseInt(raceDetails[0]);
                        int raceMonth=Integer.parseInt(raceDetails[1]);
                        int raceDate=Integer.parseInt(raceDetails[2]);
                        String[] racePositions = raceDetails[3].split(",");

                        int position = 1;
                        Race race = new Race(raceYear,raceMonth,raceDate);

                        for (String teamName : racePositions) {
                            Team team = findTeamByName(teamName);
                            Formula1Driver driver = team.getDriver();

                            driver.addPoints(position);
                            race.addPosition(driver, position);

                            position++;
                        }

                        races.add(race);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found . Try again");
        }

        System.out.println("File Read Completed");
    }


}


