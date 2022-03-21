import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Gui {
    JFrame formula = new JFrame("Formula Championship"); //Make new Java swing frame
    JTextField search = new JTextField(); //Text field for search
    //Setting arrays lists as class property
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<Race> races = new ArrayList<>();

    void displayFrame(ArrayList<Team> teams, ArrayList<Race> races) {
        this.teams = teams;
        this.races = races;


        JButton first = new JButton("Search");
        JButton second = new JButton("Descending Order");
        JButton third = new JButton("Ascending Order");
        JButton fourth = new JButton("First position order");
        JButton fifth = new JButton("Random race generator");
        JButton seventh = new JButton("Ascending order of date placed");
        formula.getContentPane().setLayout(new FlowLayout());




        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchD();
            }
        });

        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstTable();

            }
        });

        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondTable();
            }
        });

        fourth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdTable();
            }
        });
        fifth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRandomRace();
            }
        });

        seventh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seventhTable();
            }
        });
        formula.add(search);
        search.setBounds(150,25,150,25); //Button Bounds

        first.setBounds(300, 25, 100, 25);
        formula.add(first);

        second.setBounds(100, 100, 150, 40);
        formula.add(second);

        third.setBounds(100, 175, 150, 40);
        formula.add(third);

        fourth.setBounds(300, 100, 175, 40);
        formula.add(fourth);

        fifth.setBounds(300, 175, 175, 40);
        formula.add(fifth);

        seventh.setBounds(160, 250, 250, 40);
        formula.add(seventh);


        formula.setSize(600, 400);
        formula.setLayout(null);
        formula.setVisible(true);


    }

    private void searchD() {
        formula = new JFrame();
        String drivername = search.getText();  // Getting String value which is user input in the text field

        String data[][] = new String[races.size()][3];

        for (int i = 0; i < races.size(); i++) {
            Race race = races.get(i);

            for (int j = 0; j < race.getPositions().size(); j++) {
                Position position = race.getPositions().get(j);
                Formula1Driver driver = position.getDriver();

                if (driver.getName().equalsIgnoreCase(drivername)) {
                    data[i][0] = race.getYear()+"-"+ race.getMonth()+"-"+race.getDate();
                    data[i][1] = driver.getName();
                    data[i][2] = position.getPosition() + "";
                }
            }
        }

        String column[] = {"Date", "Driver Name", "Position"};

        JTable table1 = new JTable(data, column);
        table1.setBounds(300, 300, 300, 300);
        JScrollPane sp = new JScrollPane(table1);
        formula.add(sp);
        formula.setVisible(true);


    }


    private void firstTable() {
        formula = new JFrame();

        //perform the bubble sort
        Team[] driversByPoints = new Team[teams.size()];
        Team temp;

        //loop to access each array element
        for (int i = 0; i < teams.size(); i++) {
            driversByPoints[i] = teams.get(i);
        }

        //loop to compare array elements
        for (int j = 0; j < teams.size(); j++) {
            for (int k = j + 1; k < teams.size(); k++) {
                //sorting descending order
                if (driversByPoints[j].getDriver().getPoints() < driversByPoints[k].getDriver().getPoints()) {
                    //swap elements
                    temp = driversByPoints[j];
                    driversByPoints[j] = driversByPoints[k];
                    driversByPoints[k] = temp;
                }
            }
        }
        String data[][] = new String[teams.size()][6]; //2d array used to store data in the datable

        for (int i = 0; i < teams.size(); i++) {
            data[i][0] = driversByPoints[i].getDriver().getName();
            data[i][1] = driversByPoints[i].getDriver().getTeam().getName();
            data[i][2] = String.valueOf(driversByPoints[i].getDriver().getPoints());
            data[i][3] = String.valueOf(driversByPoints[i].getDriver().getNumOfFirstPositions());
            data[i][4] = String.valueOf(driversByPoints[i].getDriver().getNumOfSecondPositions());
            data[i][5] = String.valueOf(driversByPoints[i].getDriver().getNumOfThirdPositions());

            System.out.println("");
        }

        String column[] = {"Driver Name", "Team", "Points", "First Positions", "Second Positions", "Third Positions"};

        JTable table1 = new JTable(data, column);
        table1.setBounds(300, 300, 300, 300);
        JScrollPane sp = new JScrollPane(table1);
        formula.add(sp);
        formula.setVisible(true);


    }

    private void secondTable() {
        formula = new JFrame();


        Team[] driversByPoints = new Team[teams.size()];
        Team temp;

        for (int i = 0; i < teams.size(); i++) {
            driversByPoints[i] = teams.get(i);
        }


        for (int j = 0; j < teams.size(); j++) {
            for (int k = j + 1; k < teams.size(); k++) {
                if (driversByPoints[j].getDriver().getPoints() > driversByPoints[k].getDriver().getPoints()) {
                    temp = driversByPoints[j];
                    driversByPoints[j] = driversByPoints[k];
                    driversByPoints[k] = temp;
                } else if (driversByPoints[j].getDriver().getPoints() == driversByPoints[j].getDriver().getPoints()) {
                    if (driversByPoints[j].getDriver().getNumOfFirstPositions() > driversByPoints[k].getDriver().getNumOfFirstPositions()) {
                        temp = driversByPoints[j];
                        driversByPoints[j] = driversByPoints[k];
                        driversByPoints[k] = temp;
                    }
                }

            }
        }
        String data[][] = new String[teams.size()][6];

        for (int i = 0; i < teams.size(); i++) {
            data[i][0] = driversByPoints[i].getDriver().getName();
            data[i][1] = driversByPoints[i].getDriver().getTeam().getName();
            data[i][2] = String.valueOf(driversByPoints[i].getDriver().getPoints());
            data[i][3] = String.valueOf(driversByPoints[i].getDriver().getNumOfFirstPositions());
            data[i][4] = String.valueOf(driversByPoints[i].getDriver().getNumOfSecondPositions());
            data[i][5] = String.valueOf(driversByPoints[i].getDriver().getNumOfThirdPositions());

            System.out.println("");
        }

        String column[] = {"Driver Name", "Team", "Points", "First Positions", "Second Positions", "Third Positions"};

        JTable table1 = new JTable(data, column);
        table1.setBounds(300, 300, 300, 300);
        JScrollPane sp = new JScrollPane(table1);
        formula.add(sp);
        formula.setVisible(true);



    }

    private void thirdTable() {
        formula = new JFrame();


        Team[] driversByPoints = new Team[teams.size()];
        Team temp;

        for (int i = 0; i < teams.size(); i++) {
            driversByPoints[i] = teams.get(i);
        }

        for (int j = 0; j < teams.size(); j++) {
            for (int k = j + 1; k < teams.size(); k++) {
                if (driversByPoints[j].getDriver().getNumOfFirstPositions() < driversByPoints[k].getDriver().getNumOfFirstPositions()) {
                    temp = driversByPoints[j];
                    driversByPoints[j] = driversByPoints[k];
                    driversByPoints[k] = temp;
                }
            }
        }
        String data[][] = new String[teams.size()][6];

        for (int i = 0; i < teams.size(); i++) {
            data[i][0] = driversByPoints[i].getDriver().getName();
            data[i][1] = driversByPoints[i].getDriver().getTeam().getName();
            data[i][2] = String.valueOf(driversByPoints[i].getDriver().getPoints());
            data[i][3] = String.valueOf(driversByPoints[i].getDriver().getNumOfFirstPositions());
            data[i][4] = String.valueOf(driversByPoints[i].getDriver().getNumOfSecondPositions());
            data[i][5] = String.valueOf(driversByPoints[i].getDriver().getNumOfThirdPositions());

            System.out.println("");
        }

        String column[] = {"Driver Name", "Team", "Points", "First Positions", "Second Positions", "Third Positions"};

        JTable table1 = new JTable(data, column);
        table1.setBounds(300, 300, 300, 300);
        JScrollPane sp = new JScrollPane(table1);
        formula.add(sp);
        formula.setVisible(true);


    }

    private void addRandomRace() {
        List<Integer> randomRacePositions = randomPosition();
         //278-288 show Random Date generation code
        GregorianCalendar gc = new GregorianCalendar();    //https://stackoverflow.com/questions/3985392/generate-random-date-of-birth/3985644

        int year = 2021;

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        Race race=new Race(gc.get(gc.YEAR),gc.get(gc.MONTH)+1,gc.get(gc.DAY_OF_MONTH));


        int teamPosition = 1;

        while (teamPosition <= teams.size()) {
            int indexOfRandomPosition = randomRacePositions.indexOf(teamPosition);
            Formula1Driver driver = teams.get(indexOfRandomPosition).getDriver();

            driver.addPoints(teamPosition);
            race.addPosition(driver, teamPosition);
            teamPosition++;
        }

        races.add(race);

        List<Position> positions = race.getPositions();
        String[][] data = new String[positions.size()][4];

        data[0][0] =race.getYear()+"-"+race.getMonth()+"-"+race.getDate();

        boolean printPositions = true;
        int positionNumber = 1;

        while(printPositions) {
            for (Position position: positions) {
                if (position.getPosition() == positionNumber) {
                    Formula1Driver driver = position.getDriver();
                    data[positionNumber - 1][1] = position.getPosition() + "";
                    data[positionNumber - 1][2] = driver.getName();
                    data[positionNumber - 1][3] = driver.getTeam().getName();
                }
            }

            if (positionNumber <= positions.size()) {
                positionNumber++;
            } else {
                printPositions = false;
            }
        }

        String[] columns = {"Date", "Position", "Driver", "Team"};

        JTable jTable1 = new JTable(data, columns);
        jTable1.setBounds(100, 100, 100, 100);
        JScrollPane sp = new JScrollPane(jTable1);
        formula.setTitle("Random Race");
        formula.add(sp);
        formula.setSize(800, 400);
        formula.setVisible(true);



    }
    public List<Integer> randomPosition() {
        formula = new JFrame();
        List<Integer> randomPositions = new ArrayList<>(); // store random positions for available teams

        for (int i = 1; i <= teams.size(); i++) {
            randomPositions.add(i);
        }

        Collections.shuffle(randomPositions); //this function was used for shuffle randomPositions arraylis

        return randomPositions;
    }
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


    private void seventhTable() {
        formula = new JFrame();

        //perform the bubble sort
        Race[] date = new Race[races.size()];
        Race temp;

        //loop to access each array element
        for (int i = 0; i < races.size(); i++) {

            date[i] = races.get(i);
        }

        //loop to compare array elements
        for (int j = 0; j < races.size(); j++) {
            //sorting date in ascending order
            for (int k = j + 1; k < races.size(); k++) {

                if (date[j].getMonth() > date[k].getMonth()) {
                    //swap elements
                    temp = date[j];
                    date[j] = date[k];
                    date[k] = temp;


                } else if (date[j].getMonth() == date[k].getMonth()) {

                    if (date[j].getDate() > date[k].getDate()) {

                        temp = date[j];
                        date[j] = date[k];
                        date[k] = temp;
                    }
                }
            }
        }


        String data[][] = new String[races.size()][11];

        for (int j = 0; j < races.size(); j++) {

            String rdate = date[j].getYear() + " - " + date[j].getMonth() + " - " + date[j].getDate();
            data[j][0] = rdate;
            if (date[j].getPositions().size() > 10) {
                for (int k = 0; k < 10; k++) {
                    data[j][k + 1] = date[j].getPositions().get(k).getDriver().getName();
                }
            } else {
                for (int k = 0; k < date[j].getPositions().size(); k++) {
                    data[j][k + 1] = date[j].getPositions().get(k).getDriver().getName();
                }
            }
            System.out.println("");
        }


        String column[] = {"Date", "First Position", "Second Position", "Third Position", "Fourth Positions", "Fifth Positions", "Sixth Positions", "Seventh Positions", "Eighth Positions", "Ninth Positions", "Tenth Positions"};

        JTable table1 = new JTable(data, column);
        table1.setBounds(300, 300, 300, 300);
        JScrollPane sp = new JScrollPane(table1);
        formula.add(sp);
        formula.setVisible(true);


    }



}
