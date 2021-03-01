package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MonthTest {
    private Month month;
    private final LocalDate date = LocalDate.now();
    private final int yearNumber = date.getYear();
    private final int monthNumber = date.getMonthValue();

    @BeforeEach
    void setUp() {
        month = new Month();
    }

    @Test
    void dayPopulatorZero() {
        int yearNumber = 2021;
        int monthNumber = 2;
        int dayNumber = 0;
        assertThrows(DateTimeException.class, () -> month.dayPopulator(yearNumber, monthNumber, dayNumber));
    }

    @Test()
    void dayPopulatorNegative() {
        int yearNumber = 2021;
        int monthNumber = 2;
        int dayNumber = -1;
        assertThrows(DateTimeException.class, () -> month.dayPopulator(yearNumber, monthNumber, dayNumber));
    }

    @Test
    void dayPopulatorPositiveMin() {
        int yearNumber = 2021;
        int monthNumber = 2;
        int dayNumber = 1;
        month.dayPopulator(yearNumber, monthNumber, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(1));
    }

    @Test
    void dayPopulatorPositive() {
        int yearNumber = 2021;
        int monthNumber = 2;
        int dayNumber = 15;
        month.dayPopulator(yearNumber, monthNumber, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(1));
    }

    @Test
    void dayPopulatorPositiveAboveMax() {
        int yearNumber = 2021;
        int monthNumber = 2;
        int dayNumber = 40;
        assertThrows(DateTimeException.class, () -> month.dayPopulator(yearNumber, monthNumber, dayNumber));
    }

    @Test
    void addTaskToToday() {
        ByteArrayInputStream in = new ByteArrayInputStream(("First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.addTaskToTodayOrTomorrow(scanner, yearNumber, monthNumber, dayNumber);
        assertThat(month.getDay(dayNumber).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addTaskToTodayEmpty() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.addTaskToTodayOrTomorrow(scanner, yearNumber, monthNumber, dayNumber);
        assertThat(month.getDay(dayNumber).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addTaskToTodayBlank() {
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.addTaskToTodayOrTomorrow(scanner, yearNumber, monthNumber, dayNumber);
        assertThat(month.getDay(dayNumber).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addTaskToTomorrow() {
        ByteArrayInputStream in = new ByteArrayInputStream(("First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.plusDays(1).getDayOfMonth();
        month.addTaskToTodayOrTomorrow(scanner, yearNumber, monthNumber, dayNumber);
        assertThat(month.getDay(dayNumber).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayNegative() {
        ByteArrayInputStream in = new ByteArrayInputStream(("-1" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayZero() {
        ByteArrayInputStream in = new ByteArrayInputStream(("0" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayPositiveMin() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayPositive() {
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(16).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayPositiveAboveMax() {
        ByteArrayInputStream in = new ByteArrayInputStream(("40" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDayEmpty() {
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addOneTaskToSpecificDaySpace() {
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
    }

    @Test
    void addTwoTasksToDay() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "First" + System.lineSeparator() + "1" + System.lineSeparator() + "Second").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(1).toString(), equalTo("Second"));
    }

    @Test
    void addTasksToTwoDays() {
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "First" + System.lineSeparator() + "2" + System.lineSeparator() + "Second").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().get(0).toString(), equalTo("First"));
        month.addTaskToSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(2).getTaskList().get(0).toString(), equalTo("Second"));
    }

    @Test
    void removeTaskFromToday() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTodayBlank() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTodayEmpty() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTodayInvalidInput() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }


    @Test
    void removeTaskFromTodayTooHigh() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("2" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTodayTooLow() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("0" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTodayNegative() {
        addTaskToToday();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("-1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromTomorrow() {
        addTaskToTomorrow();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        int dayNumber = date.plusDays(1).getDayOfMonth();
        month.removeTaskFromTodayOrTomorrow(scanner, dayNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromSpecificDayEmptyDay() {
        assertThat(month.getDayMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NullPointerException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayBlank() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + " ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayEmpty() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayEmptyInvalidInput() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayTooLow() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayTooHigh() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "99").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayNegative() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("16" + System.lineSeparator() + "-1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayDayTooLow() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("15").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NullPointerException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayDayTooHigh() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("17").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NullPointerException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayDayEmpty() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTaskFromSpecificDayDayBlank() {
        addOneTaskToSpecificDayPositive();
        assertThat(month.getDayMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream((" ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber));
    }

    @Test
    void removeTwoTasks() {
        addTwoTasksToDay();
        assertThat(month.getDayMap().get(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "2" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().get(1).getTaskList().size(), equalTo(1));
        month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeTasksFromTwoDays() {
        addTasksToTwoDays();
        assertThat(month.getDayMap().size(), equalTo(2));
        assertThat(month.getDayMap().get(1).getTaskList().size(), equalTo(1));
        assertThat(month.getDayMap().get(2).getTaskList().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "2" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(1));
        month.removeTaskFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeAllTasksFromDay() {
        addTwoTasksToDay();
        assertThat(month.getDayMap().size(), equalTo(1));
        assertThat(month.getDayMap().get(1).getTaskList().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.removeAllTasksFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }

    @Test
    void removeAllTasksFromTwoDays() {
        addTasksToTwoDays();
        assertThat(month.getDayMap().size(), equalTo(2));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "2").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        month.removeAllTasksFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(1));
        month.removeAllTasksFromSpecificDay(scanner, yearNumber, monthNumber);
        assertThat(month.getDayMap().size(), equalTo(0));
    }
}
