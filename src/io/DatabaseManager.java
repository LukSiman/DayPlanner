package io;

import data.MasterContainer;
import data.Task;

import java.io.*;
import java.time.DateTimeException;


public final class DatabaseManager {
    private final MasterContainer container;
    private final static int YEAR_INDEX = 0;
    private final static int MONTH_INDEX = 1;
    private final static int DAY_INDEX = 2;
    private final static int TASK_INDEX = 3;

    /**
     * DatabaseManager constructor
     *
     * @param container the MasterContainer to use
     */
    public DatabaseManager(MasterContainer container) {
        this.container = container;
    }

    /**
     * Checks if the database file exists
     * Creates a new one if it doesn't
     */
    public final void databaseChecker() {
        try {
            new FileReader("taskList.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Database not found, creating a new database" + "\n");
            databaseCreator();
        }
        databaseReader();
    }

    //creates a database file if it doesn't exist
    private void databaseCreator() {
        try {
            FileWriter writer = new FileWriter(new File("taskList.csv"));
            String headerLine = "year," + "month," + "day," + "task" + "\n";
            writer.write(headerLine);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //reads the file and loads data inside to code
    private void databaseReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("taskList.csv"));

            String row;
            boolean firstLine = true;

            while ((row = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    dataPopulator(row);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //writes new data to the database
    public void databaseWriter(MasterContainer container) {
        try {
            FileWriter writer = new FileWriter("taskList.csv");
            String headerLine = "year," + "month," + "day," + "task" + "\n";
            StringBuilder fullDatabase = new StringBuilder();
            fullDatabase.append(headerLine);

            for (int year : container.getYearMap().keySet()) {
                for (int month : container.getYearMap().get(year).getMonthMap().keySet()) {
                    for (int day : container.getYearMap().get(year).getMonth(month).getDayMap().keySet()) {
                        for (Task task : container.getYearMap().get(year).getMonth(month).getDay(day).getTaskList()) {
                            String taskData = year + "," + month + "," + day + "," + task.getTask() + "\n";
                            fullDatabase.append(taskData);
                        }
                    }
                }
            }
            writer.write(fullDatabase.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //populates year, month, day and task collections with data from the file
    private void dataPopulator(String entryLine) {
        String[] data = entryLine.split(",");
        int yearNumber = Integer.parseInt(data[YEAR_INDEX]);
        int monthNumber = Integer.parseInt(data[MONTH_INDEX]);
        int dayNumber = Integer.parseInt(data[DAY_INDEX]);
        String task = data[TASK_INDEX];

        try {
            container.yearPopulator(yearNumber);
            container.getYear(yearNumber).monthPopulator(monthNumber);
            container.getYear(yearNumber).getMonth(monthNumber).dayPopulator(yearNumber, monthNumber, dayNumber);
            container.getYear(yearNumber).getMonth(monthNumber).getDay(dayNumber).taskPopulator(task);
        } catch(NullPointerException | DateTimeException e){
            System.out.println("Part of the data file has been corrupted, please check for invalid year, month or day values or delete the data file to start fresh.");
        }
    }
}
