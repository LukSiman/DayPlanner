package data;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public final class Month {
    private final TreeMap<Integer, Day> dayContainer;

    /**
     * Month constructor
     * Creates a new TreeMap for days
     */
    public Month() {
        dayContainer = new TreeMap<>();
    }

    /**
     * Populates TreeMap with Day objects
     *
     * @param dayNumber the key for the Day to add to the container
     */
    public final void dayPopulator(int yearNumber, int monthNumber, int dayNumber) {
        LocalDate date = LocalDate.of(yearNumber, monthNumber, dayNumber);
        if (dayNumber > 0 && dayNumber <= date.getMonth().length(date.isLeapYear())) {
            if (!dayContainer.containsKey(dayNumber)) {
                dayContainer.put(dayNumber, new Day());
            }
        }
    }

    /**
     * Adds Day object for today or tomorrow to the dayContainer
     * Calls a method to add a task to the Day object
     *
     * @param scanner the Scanner for the input
     */
    public final void addTaskToTodayOrTomorrow(Scanner scanner, int yearNumber, int monthNumber, int dayNumber) {
        dayPopulator(yearNumber, monthNumber, dayNumber);
        getDay(dayNumber).addTask(scanner);
    }

    /**
     * Adds Day object to the dayContainer
     * Calls a method to add a task to the Day object
     *
     * @param scanner the Scanner for the input
     */
    public final void addTaskToSpecificDay(Scanner scanner, int yearNumber, int monthNumber) {
        int dayNumber = inputChecker(scanner, yearNumber, monthNumber);
        dayPopulator(yearNumber, monthNumber, dayNumber);
        getDay(dayNumber).addTask(scanner);
    }

    /**
     * Removes tasks from today or tomorrow
     *
     * @param scanner   Scanner for user input
     * @param dayNumber today or tomorrow day number
     */
    public final void removeTaskFromTodayOrTomorrow(Scanner scanner, int dayNumber) {
        getDay(dayNumber).removeTask(scanner);
        removeEmptyDay(dayNumber);
    }

    /**
     * Calls a method to remove a task from the Day object
     *
     * @param scanner the Scanner for the input
     */
    public final void removeTaskFromSpecificDay(Scanner scanner, int yearNumber, int monthNumber) {
        int dayNumber = inputChecker(scanner, yearNumber, monthNumber);
        getDay(dayNumber).removeTask(scanner);
        removeEmptyDay(dayNumber);
    }

    /**
     * Calls a method to remove all tasks from specified day
     *
     * @param scanner the Scanner for user input
     */
    public final void removeAllTasksFromSpecificDay(Scanner scanner, int yearNumber, int monthNumber) {
        int dayNumber = inputChecker(scanner, yearNumber, monthNumber);
        getDay(dayNumber).removeAll();
        removeEmptyDay(dayNumber);
    }

    //checks if user input is valid
    private int inputChecker(Scanner scanner, int yearNumber, int monthNumber) {
        System.out.println("Please enter the day number:");
        int dayNumber;
        LocalDate date;

        while (true) {
            try {
                dayNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!" + "\n");
                continue;
            }

            date = LocalDate.of(yearNumber, monthNumber, 1);
            if (dayNumber < 1 || dayNumber > date.getMonth().length(date.isLeapYear())) {
                System.out.println("\n" + "Invalid day, please try again!");
                continue;
            }
            break;
        }
        return dayNumber;
    }

    //removes Day object if there are no tasks
    private void removeEmptyDay(int dayNumber) {
        if (getDay(dayNumber).getTaskList().isEmpty()) {
            dayContainer.remove(dayNumber);
        }
    }

    /**
     * Returns TreeMap with Day objects
     *
     * @return TreeMap
     */
    public final Map<Integer, Day> getDayMap() {
        return dayContainer;
    }

    /**
     * Returns Day object
     *
     * @param dayNumber key of the Day object
     * @return Day object
     */
    public final Day getDay(int dayNumber) {
        return dayContainer.get(dayNumber);
    }
}