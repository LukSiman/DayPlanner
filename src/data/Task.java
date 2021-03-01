package data;

public final class Task {
    private final String task;

    /**
     * Task constructor
     * @param task the task to add
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Return task
     * @return task
     */
    public String getTask(){
        return task;
    }

    /**
     * Overrides toString to return task in readable String form
     * @return the task
     */
    @Override
    public String toString() {
        return "" + task;
    }
}
