package data;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TaskTest {

    @Test
    void getTask() {
        Task task = new Task("Test");
        assertThat(task.getTask(), equalTo("Test"));
    }

    @Test
    void testToString() {
        Task task = new Task("Test");
        assertThat(task.getTask(), equalTo("Test"));
    }
}