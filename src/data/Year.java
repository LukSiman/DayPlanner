package data;

import java.util.Scanner;
import java.util.TreeMap;

public final class Year {
    private final TreeMap<Integer, Month> monthContainer;

    /**
     * Year constructor
     * Creates new TreeMap for Month objects
     */
    public Year() {
        monthContainer = new TreeMap<>();
    }

    /**
     * Populates TreeMap with Month objects
     *
     * @param monthNumber the number of the month
     */
    public final void monthPopulator(int monthNumber) {
        if (monthNumber >= 1 && monthNumber <= 12) {
            if (!monthContainer.containsKey(monthNumber)) {
                monthContainer.put(monthNumber, new Month());
            }
        }
    }

    /**
     * Adds task to specified month
     *
     * @param scanner the Scanner for the user input
     */
    public final void addTaskToAnotherMonth(Scanner scanner, int yearNumber) {
        int monthNumber = inputChecker(scanner);
        monthPopulator(monthNumber);
        getMonth(monthNumber).addTaskToSpecificDay(scanner, yearNumber, monthNumber);
    }

    /**
     * Remove task from the specified day in the specified month
     *
     * @param scanner the Scanner for the user input
     * @param mode    differentiates whether to remove a single or all tasks
     */
    public final void removeTaskFromAnotherMonth(Scanner scanner, String mode, int yearNumber) {
        int monthNumber = inputChecker(scanner);
        if (getMonth(monthNumber) != null) {
            if (mode.equalsIgnoreCase("single")) {
                getMonth(monthNumber).removeTaskFromSpecificDay(scanner, yearNumber, monthNumber);
            } else {
                getMonth(monthNumber).removeAllTasksFromSpecificDay(scanner, yearNumber, monthNumber);
            }
            if (getMonth(monthNumber).getDayMap().isEmpty()) {
                monthContainer.remove(monthNumber);
            }
        } else {
            System.out.println("Please add tasks to this month first." + "\n");
        }
    }

    //checks user input
    private int inputChecker(Scanner scanner) {
        System.out.println("Please enter the month number:");
        int monthNumber;

        while (true) {
            try {
                monthNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!" + "\n");
                continue;
            }
            if (monthNumber < 1 || monthNumber > 12) {
                System.out.println("\n" + "Invalid month, please try again!");
                continue;
            }
            break;
        }
        return monthNumber;
    }

    /**
     * Returns TreeMap with Month objects
     *
     * @return TreeMap
     */
    public final TreeMap<Integer, Month> getMonthMap() {
        return monthContainer;
    }

    /**
     * Returns specified Month object
     *
     * @param monthNumber the key for the Month
     * @return Month object
     */
    public final Month getMonth(int monthNumber) {
        return monthContainer.get(monthNumber);
    }
}
