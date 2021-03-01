package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class YearTest {
    private Year year;
    private final LocalDate date = LocalDate.now();
    private final int yearNumber = date.getYear();

    @BeforeEach
    void setUp() {
        year = new Year();
    }

    @Test
    void monthPopulatorZero() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        int monthNumber = 0;
        year.monthPopulator(monthNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test()
    void monthPopulatorNegative() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        int monthNumber = -1;
        year.monthPopulator(monthNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void monthPopulatorPositiveMin() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        int monthNumber = 1;
        year.monthPopulator(monthNumber);
        assertTrue(year.getMonthMap().containsKey(1));
    }

    @Test
    void monthPopulatorPositive() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        int monthNumber = 6;
        year.monthPopulator(monthNumber);
        assertTrue(year.getMonthMap().containsKey(6));
    }

    @Test
    void monthPopulatorPositiveAboveMax() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        int monthNumber = 13;
        year.monthPopulator(monthNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void addTaskToAnotherMonthInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream(("a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> year.addTaskToAnotherMonth(scanner, yearNumber));
    }

    @Test
    void addTaskToAnotherMonthInvalidInputZero() {
        ByteArrayInputStream in = new ByteArrayInputStream(("0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> year.addTaskToAnotherMonth(scanner, yearNumber));
    }

    @Test
    void addTaskToAnotherMonthInvalidInputEmpty() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> year.addTaskToAnotherMonth(scanner, yearNumber));
    }

    @Test
    void addTaskToAnotherMonthInvalidInputBlank() {
        ByteArrayInputStream in = new ByteArrayInputStream((" ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> year.addTaskToAnotherMonth(scanner, yearNumber));
    }

    @Test
    void addTaskToAnotherMonthValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        year.addTaskToAnotherMonth(scanner, yearNumber);
        assertTrue(year.getMonthMap().containsKey(5));
    }

    @Test
    void addTwoTasksToAnotherMonthValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.lineSeparator() + "1" + System.lineSeparator() + "First" + System.lineSeparator() + "5" + System.lineSeparator() + "1" + System.lineSeparator() + "Second").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        year.addTaskToAnotherMonth(scanner, yearNumber);
        assertTrue(year.getMonthMap().containsKey(5));
        year.addTaskToAnotherMonth(scanner, yearNumber);
        assertEquals(year.getMonth(5).getDay(1).getTaskList().size(), 2);
    }

    @Test
    void addTaskToAnotherMonthValidInputMin() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        year.addTaskToAnotherMonth(scanner, yearNumber);
        assertTrue(year.getMonthMap().containsKey(1));
    }

    @Test
    void addTaskToAnotherMonthValidInputMax() {
        ByteArrayInputStream in = new ByteArrayInputStream(("12" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        year.addTaskToAnotherMonth(scanner, yearNumber);
        assertTrue(year.getMonthMap().containsKey(12));
    }

    @Test
    void addTaskToAnotherMonthValidInputAboveMax() {
        ByteArrayInputStream in = new ByteArrayInputStream(("13").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(year.getMonthMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> year.addTaskToAnotherMonth(scanner, yearNumber));
    }

    @Test
    void removeTaskFromAnotherMonthBlank() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream((" ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "single", yearNumber));
    }

    @Test
    void removeTaskFromAnotherMonthEmpty() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "single", yearNumber));
    }

    @Test
    void removeTaskFromAnotherMonthZero() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "single", yearNumber));
    }

    @Test
    void removeTaskFromAnotherMonthInvalidInput() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "single", yearNumber));
    }

    @Test
    void removeTaskFromAnotherMonth() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "single", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherMonthMin() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInputMin();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "single", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherMonthMax() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInputMax();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("12" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "single", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherMonthTooLow() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("4").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "single", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(1));
    }

    @Test
    void removeTaskFromAnotherMonthTooHigh() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "single", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(1));
    }

    @Test
    void removeTaskFromAnotherMonthAboveMax() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTaskToAnotherMonthValidInput();
        assertThat(year.getMonthMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("13").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "single", yearNumber));
    }

    @Test
    void removeAllTasksFromAnotherMonth() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("5" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "all", yearNumber);
        assertThat(year.getMonthMap().size(), equalTo(0));
    }

    @Test
    void removeAllTasksFromAnotherMonthEmpty() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "all", yearNumber));
    }

    @Test
    void removeAllTasksFromAnotherMonthBlank() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream((" ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "all", yearNumber));
    }

    @Test
    void removeAllTasksFromAnotherMonthZero() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "all", yearNumber));
    }

    @Test
    void removeAllTasksFromAnotherMonthInvalidInput() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "all", yearNumber));
    }

    @Test
    void removeAllTasksFromAnotherMonthTooLow() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("4").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "all", yearNumber);
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
    }

    @Test
    void removeAllTasksFromAnotherMonthTooHigh() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("6").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        year.removeTaskFromAnotherMonth(scanner, "all", yearNumber);
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
    }

    @Test
    void removeAllTasksFromAnotherMonthAboveMax() {
        assertThat(year.getMonthMap().size(), equalTo(0));
        addTwoTasksToAnotherMonthValidInput();
        assertThat(year.getMonth(5).getDay(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("13").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> year.removeTaskFromAnotherMonth(scanner, "all", yearNumber));
    }
}