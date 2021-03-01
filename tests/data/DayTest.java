package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class DayTest {
    Day day;

    @BeforeEach
    void setUp() {
        day = new Day();
    }

    @Test
    void addOneTask() {
        ByteArrayInputStream in = new ByteArrayInputStream("First".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addEmptyTask() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addSpaceTask() {
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addTwoTasks() {
        ByteArrayInputStream in = new ByteArrayInputStream(("First" + System.lineSeparator() + "Second").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().get(0).toString(), equalTo("First"));
        day.addTask(scanner);
        assertThat(day.getTaskList().get(1).toString(), equalTo("Second"));
    }

    @Test
    void addTaskCancelationYes() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() + "y" + System.lineSeparator() + "6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void addTaskCancelationNo() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() +
                "n" + System.lineSeparator() + "" + System.lineSeparator() + "y" + System.lineSeparator() + "6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void addTaskCancelationNoAdd() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() +
                "n" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.addTask(scanner);
        assertThat(day.getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void removeTask() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTwoTasks() {
        addTwoTasks();
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(1));
        assertThat(day.getTaskList().get(0).toString(), equalTo("Second"));
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskTooHigh() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskTooLow() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("0" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskInvalidInput() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskEmptyInput() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskSpaceInput() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeTaskCancelationYes() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() + "y" + System.lineSeparator() + "6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(1));
    }

    @Test
    void removeTaskCancelationNo() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() +
                "n" + System.lineSeparator() + "" + System.lineSeparator() + "y" + System.lineSeparator() + "6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(1));
    }

    @Test
    void removeTaskCancelationNoRemove() {
        addOneTask();
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "" + System.lineSeparator() + "" + System.lineSeparator() +
                "n" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        day.removeTask(scanner);
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void removeAll() {
        addTwoTasks();
        assertThat(day.getTaskList().size(), equalTo(2));
        day.removeAll();
        assertThat(day.getTaskList().size(), equalTo(0));
    }

    @Test
    void printAll() {
        System.out.println("PRINT ALL START");
        addTwoTasks();
        day.printAll();
        assertThat(day.getTaskList().size(), equalTo(2));
        day.removeAll();
        assertThat(day.getTaskList().size(), equalTo(0));
        day.printAll();
        System.out.println("PRINT ALL END");
    }
}