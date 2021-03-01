package data;

import java.util.Scanner;
import java.util.TreeMap;

public final class MasterContainer {
    private final TreeMap<Integer, Year> yearContainer;

    /**
     * MasterContainer constructor
     * Creates a new TreeMap for Year objects
     */
    public MasterContainer() {
        yearContainer = new TreeMap<>();
    }

    /**
     * Populates TreeMap with Year objects
     *
     * @param yearNumber the key for the Year object
     */
    public final void yearPopulator(int yearNumber) {
        if (yearNumber >= 1) {
            if (!yearContainer.containsKey(yearNumber)) {
                yearContainer.put(yearNumber, new Year());
            }
        }
    }

    /**
     * Adds a task to the specified Year
     *
     * @param scanner the Scanner for user input
     */
    public final void addTaskToAnotherYear(Scanner scanner) {
        int yearNumber = inputChecker(scanner);
        yearPopulator(yearNumber);
        getYear(yearNumber).addTaskToAnotherMonth(scanner, yearNumber);
    }

    /**
     * Removes a task from a specific day
     *
     * @param scanner Scanner for user input
     * @param mode    differentiates whether to remove a single or all tasks
     */
    public final void removeTaskFromAnotherYear(Scanner scanner, String mode) {
        int yearNumber = inputChecker(scanner);
        if(getYear(yearNumber) != null) {
            getYear(yearNumber).removeTaskFromAnotherMonth(scanner, mode, yearNumber);
            if(getYear(yearNumber).getMonthMap().isEmpty()){
                yearContainer.remove(yearNumber);
            }
        } else {
            System.out.println("Please add tasks to this year first.");
        }
    }

    //checks user input
    private int inputChecker(Scanner scanner) {
        System.out.println("Please enter the year number:");
        int yearNumber;

        while (true) {
            try {
                yearNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!" + "\n");
                continue;
            }
            if (yearNumber < 1) {
                System.out.println("\n" + "Invalid year, please try again!");
                continue;
            }
            break;
        }
        return yearNumber;
    }

    /**
     * Return TreeMap with Year objects
     *
     * @return TreeMap
     */
    public final TreeMap<Integer, Year> getYearMap() {
        return yearContainer;
    }

    /**
     * Returns Year object
     *
     * @param yearNumber the key for the Year object
     * @return Year object
     */
    public final Year getYear(int yearNumber) {
        return yearContainer.get(yearNumber);
    }
}
