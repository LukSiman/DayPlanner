package data;

import java.util.ArrayList;
import java.util.Scanner;

public final class Day {
    private final ArrayList<Task> taskList;
    private final int CANCELATION_THRESHOLD = 3;

    /**
     * Day constructor
     * Creates a new ArrayList for the tasks
     */
    public Day() {
        taskList = new ArrayList<>();
    }

    /**
     * Populates the taskList with Task objects
     *
     * @param task the task to add
     */
    @SuppressWarnings("SuspiciousMethodCalls")
    public final void taskPopulator(String task) {
        if (!taskList.contains(task)) {
            taskList.add(new Task(task));
        }
    }

    /**
     * Adds the task to the taskList
     *
     * @param scan the Scanner for the inputs
     */
    public final void addTask(Scanner scan) {
        System.out.println("\n" + "Please enter a task to add:");
        String taskText;
        int count = 0;

        while (true) {
            taskText = scan.nextLine();
            if (taskText.isBlank()) {
                System.out.println("You can't enter blank tasks, please try again.");
                count++;
                //If user does an invalid input enough times then they are asked
                //if they want to continue, if not then program goes back to the main menu
                if (count >= CANCELATION_THRESHOLD) {
                    if (operationCancelation(scan)) {
                        return;
                    }
                }
            } else {
                break;
            }
        }
        final Task task = new Task(taskText);
        taskList.add(task);
        System.out.println("\n" + "Task has been added" + "\n");
    }

    /**
     * Removes a specified task
     * @param scan the Scanner for user input
     */
    public final void removeTask(Scanner scan) {
        if (!taskList.isEmpty()) {
            System.out.println("Please enter task number to remove:");
            int index;
            int count = 0;

            while (true) {
                try {
                    index = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("\n" + "Invalid input, please enter numbers only!" + "\n");
                    count++;
                    if (count >= CANCELATION_THRESHOLD) {
                        if (operationCancelation(scan)) {
                            return;
                        }
                    }
                    continue;
                }
                if (index > taskList.size()) {
                    System.out.println("Invalid task number, please input a lower task number");
                } else if (index < 1) {
                    System.out.println("Invalid task number, please input a higher task number");
                } else {
                    break;
                }
            }

            taskList.remove(index - 1);
            System.out.println("\n" + "Task has been removed." + "\n");
        } else {
            System.out.println("\n" + "There are no tasks to remove." + "\n");
        }
    }

    //Returns a boolean value if the user wants to cancel the operation or no
    private boolean operationCancelation(Scanner scan) {
        boolean cancelationConfirmed = false;
        boolean operationSelected = false;
        while (!operationSelected) {
            System.out.println("\n" + "Do you want to cancel this operation? (Y/N)");
            String cancelAnswer = scan.nextLine();
            if (cancelAnswer.equalsIgnoreCase("Y")) {
                System.out.println();
                cancelationConfirmed = true;
                operationSelected = true;
            } else if (cancelAnswer.equalsIgnoreCase("N")) {
                operationSelected = true;
            } else {
                System.out.println("Invalid input");
            }
        }
        return cancelationConfirmed;
    }

    /**
     * Removes all tasks from the day
     */
    public final void removeAll() {
        taskList.clear();
        System.out.println("\n" + "All tasks have been removed." + "\n");
    }

    /**
     * Prints all tasks added to the day
     */
    public final void printAll() {
        if (!taskList.isEmpty()) {
            for (Task task : taskList) {
                System.out.println(task);
            }
        } else {
            System.out.println("There are no tasks to display.");
        }
        System.out.println();
    }

    /**
     * Returns an ArrayList with Task objects
     * @return taskList
     */
    public final ArrayList<Task> getTaskList() {
        return taskList;
    }
}