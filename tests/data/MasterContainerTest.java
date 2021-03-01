package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class MasterContainerTest {
    private MasterContainer masterContainer;

    @BeforeEach
    void setUp() {
        masterContainer = new MasterContainer();
    }

    @Test
    void yearPopulatorZero() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        int yearNumber = 0;
        masterContainer.yearPopulator(yearNumber);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
    }

    @Test()
    void yearPopulatorNegative() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        int yearNumber = -1;
        masterContainer.yearPopulator(yearNumber);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
    }

    @Test
    void yearPopulatorPositiveMin() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        int yearNumber = 1;
        masterContainer.yearPopulator(yearNumber);
        assertTrue(masterContainer.getYearMap().containsKey(1));
    }

    @Test
    void yearPopulatorPositive() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        int yearNumber = 2021;
        masterContainer.yearPopulator(yearNumber);
        assertTrue(masterContainer.getYearMap().containsKey(2021));
    }

    @Test
    void addTaskToAnotherYearInvalidInput() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> masterContainer.addTaskToAnotherYear(scanner));
    }

    @Test
    void addTaskToAnotherYearInvalidInputZero() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> masterContainer.addTaskToAnotherYear(scanner));
    }

    @Test
    void addTaskToAnotherYearInvalidInputEmpty() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> masterContainer.addTaskToAnotherYear(scanner));
    }

    @Test
    void addTaskToAnotherYearInvalidInputBlank() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream((" " + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        assertThrows(NoSuchElementException.class, () -> masterContainer.addTaskToAnotherYear(scanner));
    }

    @Test
    void addTaskToAnotherYearValidInput() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("2021" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        masterContainer.addTaskToAnotherYear(scanner);
        assertTrue(masterContainer.getYearMap().containsKey(2021));
    }

    @Test
    void addTaskToAnotherYearValidInputMin() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        masterContainer.addTaskToAnotherYear(scanner);
        assertTrue(masterContainer.getYearMap().containsKey(1));
    }

    @Test
    void addTaskToAnotherYearValidInputVeryHigh() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        ByteArrayInputStream in = new ByteArrayInputStream(("9999" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "First").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        masterContainer.addTaskToAnotherYear(scanner);
        assertTrue(masterContainer.getYearMap().containsKey(9999));
    }

    @Test
    void removeTaskFromAnotherYearBlank() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream((" ").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> masterContainer.removeTaskFromAnotherYear(scanner,"single"));
    }

    @Test
    void removeTaskFromAnotherYearEmpty() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("" + System.lineSeparator()).getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> masterContainer.removeTaskFromAnotherYear(scanner,"single"));
    }

    @Test
    void removeTaskFromAnotherYearZero() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("0").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> masterContainer.removeTaskFromAnotherYear(scanner,"single"));
    }

    @Test
    void removeTaskFromAnotherYearInvalidInput() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("a").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        assertThrows(NoSuchElementException.class, () -> masterContainer.removeTaskFromAnotherYear(scanner,"single"));
    }

    @Test
    void removeTaskFromAnotherYear() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("2021" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        masterContainer.removeTaskFromAnotherYear(scanner,"single");
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherYearMin() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInputMin();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        masterContainer.removeTaskFromAnotherYear(scanner,"single");
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherYearVeryHigh() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInputVeryHigh();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("9999" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        masterContainer.removeTaskFromAnotherYear(scanner,"single");
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
    }

    @Test
    void removeTaskFromAnotherYearTooLow() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("1500").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        masterContainer.removeTaskFromAnotherYear(scanner,"single");
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
    }

    @Test
    void removeTaskFromAnotherYearTooHigh() {
        assertThat(masterContainer.getYearMap().size(), equalTo(0));
        addTaskToAnotherYearValidInput();
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
        ByteArrayInputStream in = new ByteArrayInputStream(("2030").getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        masterContainer.removeTaskFromAnotherYear(scanner,"single");
        assertThat(masterContainer.getYearMap().size(), equalTo(1));
    }
}