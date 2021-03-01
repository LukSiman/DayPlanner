package logic;

import data.*;
import io.DatabaseManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class MainController {
    private final LocalDate date;
    private final DateTimeFormatter formater;
    private final MasterContainer container;
    private final DatabaseManager database;
    private final int todayYear;
    private final int todayMonth;
    private final int todayDay;
    private final int tomorrowYear;
    private final int tomorrowMonth;
    private final int tomorrowDay;
    private static final int NEXT_DAYS_AMOUNT_MIN = 7;
    private static final int NEXT_DAYS_AMOUNT_MAX = 30;


    /**
     * MainController constructor
     * Creates current date, formats it
     * Creates a new MasterContainer and DatabaseManager
     * Sets today's year, month and day
     */
    public MainController() {
        date = LocalDate.now();
        LocalDate tomorrowDate = date.plusDays(1);
        formater = DateTimeFormatter.ofPattern("yyyy-MMMM-dd");
        container = new MasterContainer();
        database = new DatabaseManager(container);
        todayYear = date.getYear();
        todayMonth = date.getMonthValue();
        todayDay = date.getDayOfMonth();
        tomorrowYear = tomorrowDate.getYear();
        tomorrowMonth = tomorrowDate.getMonthValue();
        tomorrowDay = tomorrowDate.getDayOfMonth();
    }

    /**
     * Prints current date and today's tasks
     * Runs method that fills the Maps
     */
    public final void start() {
        System.out.println("Current date:");
        System.out.println(date.format(formater));
        System.out.println();
        database.databaseChecker();
        todayTasks();
        commands();
    }

    //main menu
    private void commands() {
        Scanner scanner = new Scanner(System.in);

        int operation = 0;
        while (operation != 6) {
            System.out.println("Please input a command:");
            System.out.println("1. Add task.");
            System.out.println("2. Remove task.");
            System.out.println("3. Print all tasks for today.");
            System.out.println("4. Print all tasks for tomorrow.");
            System.out.println("5. More task printing options.");
            System.out.println("6. End program." + "\n");


            try {
                operation = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!" + "\n");
                continue;
            }

            switch (operation) {
                case 1:
                    addTaskOptions(scanner);
                    database.databaseWriter(container);
                    break;
                case 2:
                    removeTaskOptions(scanner);
                    database.databaseWriter(container);
                    break;
                case 3:
                    todayTasks();
                    break;
                case 4:
                    tomorrowTasks();
                    break;
                case 5:
                    printTaskOptions(scanner);
                    break;
                case 6:
                    System.out.println("Quiting the program.");
                    break;
                default:
                    System.out.println("\n" + "No such command exists!" + "\n");
                    break;
            }
        }
    }

    //main menu for adding tasks
    private void addTaskOptions(Scanner scanner) {
        int operation;
        while (true) {
            System.out.println("\n" + "1. Add task to today.");
            System.out.println("2. Add task to tomorrow.");
            System.out.println("3. Add task to another day this month.");
            System.out.println("4. Add task to another month.");
            System.out.println("5. Add task to another year." + "\n");

            try {
                operation = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }

            switch (operation) {
                case 1:
                    addTaskToToday(scanner);
                    break;
                case 2:
                    addTaskToTomorrow(scanner);
                    break;
                case 3:
                    addTaskToAnotherDay(scanner);
                    break;
                case 4:
                    addTaskToAnotherMonth(scanner);
                    break;
                case 5:
                    addTaskToAnotherYear(scanner);
                    break;
                default:
                    System.out.println("\n" + "No such command exists!");
                    continue;
            }
            break;
        }
    }

    //main menu for removing tasks
    private void removeTaskOptions(Scanner scanner) {
        int operation;
        while (true) {
            System.out.println("\n" + "1. Remove task from today.");
            System.out.println("2. Remove task from tomorrow.");
            System.out.println("3. Remove task from a specific day this month.");
            System.out.println("4. Remove task from a specific day this year.");
            System.out.println("5. Remove task from a specific day.");
            System.out.println("6. Remove all tasks from a specific day." + "\n");

            try {
                operation = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }

            switch (operation) {
                case 1:
                    removeTaskFromToday(scanner);
                    break;
                case 2:
                    removeTaskFromTomorrow(scanner);
                    break;
                case 3:
                    removeTaskFromThisMonth(scanner);
                    break;
                case 4:
                    removeTaskFromAnotherMonth(scanner);
                    break;
                case 5:
                    removeTaskFromSpecificDay(scanner);
                    break;
                case 6:
                    removeAllTasksFromSpecificDay(scanner);
                    break;
                default:
                    System.out.println("\n" + "No such command exists!");
                    continue;
            }
            break;
        }
    }

    //Adds tasks to today
    private void addTaskToToday(Scanner scanner) {
        nullCheckerDay(todayYear, todayMonth, todayDay);
        container.getYear(todayYear).getMonth(todayMonth).addTaskToTodayOrTomorrow(scanner, todayYear, todayMonth, todayDay);
    }

    //Adds tasks to tomorrow
    private void addTaskToTomorrow(Scanner scanner) {
        nullCheckerDay(tomorrowYear, tomorrowMonth, tomorrowDay);
        container.getYear(tomorrowYear).getMonth(tomorrowMonth).addTaskToTodayOrTomorrow(scanner, tomorrowYear, tomorrowMonth, tomorrowDay);
    }

    //Adds a task to another day this month
    private void addTaskToAnotherDay(Scanner scanner) {
        nullCheckerMonth(todayYear, todayMonth);
        container.getYear(todayYear).getMonth(todayMonth).addTaskToSpecificDay(scanner, todayYear, todayMonth);
    }

    //Adds a task to another month
    private void addTaskToAnotherMonth(Scanner scanner) {
        container.yearPopulator(todayYear);
        container.getYear(todayYear).addTaskToAnotherMonth(scanner, todayYear);
    }

    //Add task to specified year
    private void addTaskToAnotherYear(Scanner scanner) {
        container.addTaskToAnotherYear(scanner);
    }

    //Checks if the Day object exists, creates it if it doesn't
    private void nullCheckerDay(int yearNumber, int monthNumber, int dayNumber) {
        nullCheckerMonth(yearNumber, monthNumber);
        container.getYear(yearNumber).getMonth(monthNumber).dayPopulator(yearNumber, monthNumber, dayNumber);
    }

    //Checks if the Month object for the given yearNumber and monthNumber exists
    //Creates the Year and Month objects if it doesn't
    private void nullCheckerMonth(int yearNumber, int monthNumber) {
        container.yearPopulator(yearNumber);
        container.getYear(yearNumber).monthPopulator(monthNumber);
    }

    //removes a specified task from today
    private void removeTaskFromToday(Scanner scanner) {
        System.out.println();
        if (!todayTasks()) {
            container.getYear(todayYear).getMonth(todayMonth).removeTaskFromTodayOrTomorrow(scanner, todayDay);
        }
    }

    //removes a specified task from tomorrow
    private void removeTaskFromTomorrow(Scanner scanner) {
        System.out.println();
        if (!tomorrowTasks()) {
            container.getYear(tomorrowYear).getMonth(tomorrowMonth).removeTaskFromTodayOrTomorrow(scanner, tomorrowDay);
        }
    }

    //removes a specified task from a specific day this month
    private void removeTaskFromThisMonth(Scanner scanner) {
        System.out.println();
        try {
            container.getYear(todayYear).getMonth(todayMonth).removeTaskFromSpecificDay(scanner, todayYear, todayMonth);
        } catch (NullPointerException e) {
            System.out.println("\n" + "This day has no tasks." + "\n");
        }
    }

    //removes a specified task from a specific day another month
    private void removeTaskFromAnotherMonth(Scanner scanner) {
        System.out.println();
        try {
            container.getYear(todayYear).removeTaskFromAnotherMonth(scanner, "single", todayYear);
        } catch (NullPointerException e) {
            System.out.println("\n" + "This day has no tasks." + "\n");
        }
    }

    //remove a specified task from a specific day
    private void removeTaskFromSpecificDay(Scanner scanner) {
        try {
            container.removeTaskFromAnotherYear(scanner, "single");
        } catch (
                NullPointerException e) {
            System.out.println("\n" + "This day has no tasks." + "\n");
        }

    }

    //remove all tasks from a specified day
    private void removeAllTasksFromSpecificDay(Scanner scanner) {
        try {
            container.removeTaskFromAnotherYear(scanner, "all");
        } catch (NullPointerException e) {
            System.out.println("\n" + "This day has no tasks." + "\n");
        }
    }

    //prints today's tasks
    private boolean todayTasks() {
        boolean isNull = false;
        System.out.println("Today's tasks:");
        try {
            container.getYear(todayYear).getMonth(todayMonth).getDay(todayDay).printAll();
        } catch (NullPointerException e) {
            System.out.println("There are no tasks today." + "\n");
            isNull = true;
        }
        return isNull;
    }

    //print tomorrow's tasks
    private boolean tomorrowTasks() {
        boolean isNull = false;
        System.out.println("Tomorrow's tasks:");
        try {
            container.getYear(tomorrowYear).getMonth(tomorrowMonth).getDay(tomorrowDay).printAll();
        } catch (NullPointerException e) {
            System.out.println("There are no tasks for tomorrow." + "\n");
            isNull = true;
        }
        return isNull;
    }

    //menu for more task printing options
    private void printTaskOptions(Scanner scanner) {
        int commands;
        boolean isValidInput = false;
        do {
            System.out.println("\n" + "1. Print all tasks for the next " + NEXT_DAYS_AMOUNT_MIN + " days.");
            System.out.println("2. Print all tasks with tasks for the next " + NEXT_DAYS_AMOUNT_MAX + " days.");
            System.out.println("3. Print tasks for a specific day.");
            System.out.println("4. Print ALL days with tasks." + "\n");

            try {
                commands = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }

            switch (commands) {
                case 1:
                    printTasksForNextSpecifiedDays(NEXT_DAYS_AMOUNT_MIN);
                    break;
                case 2:
                    printTasksForNextSpecifiedDays(NEXT_DAYS_AMOUNT_MAX);
                    break;
                case 3:
                    printTasksFromSpecificDay(scanner);
                    break;
                case 4:
                    printAllDaysWithTasks();
                    break;
                default:
                    System.out.println("\n" + "No such command exists!");
                    continue;
            }
            isValidInput = true;
        } while (!isValidInput);
    }

    //prints a task from specified day
    private void printTasksFromSpecificDay(Scanner scanner) {
        int yearNumber = 0;
        int monthNumber = 0;
        int dayNumber = 0;
        boolean isValidInput = false;

        do {
            try {
                System.out.println("\n" + "Please input the year number:");
                yearNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }
            try {
                System.out.println("\n" + "Please input the month number:");
                monthNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }
            try {
                System.out.println("\n" + "Please input the day number:");
                dayNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n" + "Invalid input, please enter numbers only!");
                continue;
            }
            isValidInput = true;
        } while (!isValidInput);

        System.out.printf("%nTasks for %d-%02d-%02d:%n", yearNumber, monthNumber, dayNumber);
        try {
            container.getYear(yearNumber).getMonth(monthNumber).getDay(dayNumber).printAll();
        } catch (NullPointerException e) {
            System.out.println("There are no tasks added to this day." + "\n");
        }
    }

    //prints all tasks for the next specified number of days
    private void printTasksForNextSpecifiedDays(int days) {
        System.out.println();
        for (int i = 1; i <= days; i++) {
            LocalDate tempDate = date.plusDays(i);
            int tempYear = tempDate.getYear();
            int tempMonth = tempDate.getMonthValue();
            int tempDay = tempDate.getDayOfMonth();
            Day taskDay = null;
            try {
                taskDay = container.getYear(tempYear).getMonth(tempMonth).getDay(tempDay);
            } catch (NullPointerException ignore) {
            }
            if (taskDay != null) {
                System.out.printf("%-11s%s%n", tempDate, taskDay.getTaskList());
            }
        }
        System.out.println();
    }

    //prints all days that have tasks added to them
    private void printAllDaysWithTasks() {
        String headerFormat = "%-5s%-2s%-6s%-2s%-4s%n";
        String format = "%-5s%s%6s%2s%4s%n";
        if (!container.getYearMap().isEmpty()) {
            System.out.printf("%n" + headerFormat, "Year", "|", "Month", "|", "Day");
            for (int yearKey : container.getYearMap().keySet()) {
                for (int monthKey : container.getYear(yearKey).getMonthMap().keySet()) {
                    for (int dayKey : container.getYear(yearKey).getMonth(monthKey).getDayMap().keySet()) {
                        System.out.printf(format, yearKey, "|", monthKey, "|", dayKey);
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println("\n" + "Database is empty!" + "\n");
        }
    }
}