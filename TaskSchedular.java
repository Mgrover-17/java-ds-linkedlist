class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskSchedulerTasks {
    private Task head;
    private Task tail;
    private Task current;

    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addAtPosition(int taskId, String taskName, int priority, String dueDate, int position) {
        if (position <= 1) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }

        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task current = head;
        for (int i = 1; current != null && i < position - 1; i++) {
            current = current.next;
            if (current == head) return;
        }

        newTask.next = current.next;
        current.next = newTask;
    }

    public void removeByTaskId(int taskId) {
        if (head == null) return;

        Task current = head, prev = null;
        do {
            if (current.taskId == taskId) {
                if (current == head) {
                    tail.next = head.next;
                    head = head.next;
                } else if (current == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void viewCurrentTask() {
        if (current == null) current = head;
        if (current != null) {
            System.out.println(current.taskId + " - " + current.taskName + " - Priority: " + current.priority + " - Due: " + current.dueDate);
            current = current.next;
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void displayTasks() {
        if (head == null) return;
        Task temp = head;
        do {
            System.out.println(temp.taskId + " | " + temp.taskName + " | Priority: " + temp.priority + " | Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) return;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println(temp.taskId + " - " + temp.taskName + " - Due: " + temp.dueDate);
            }
            temp = temp.next;
        } while (temp != head);
    }
}

public class TaskSchedular {
    public static void main(String[] args) {
        TaskSchedulerTasks scheduler = new TaskSchedulerTasks();
        scheduler.addAtBeginning(1, "Design UI", 2, "2025-03-25");
        scheduler.addAtEnd(2, "Develop Backend", 1, "2025-03-30");
        scheduler.addAtPosition(3, "Write Documentation", 3, "2025-04-05", 2);

        System.out.println("All Tasks:");
        scheduler.displayTasks();

        System.out.println("\nViewing Current Task:");
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();

        System.out.println("\nSearching for Priority 2 Tasks:");
        scheduler.searchByPriority(2);

        System.out.println("\nRemoving Task ID 1:");
        scheduler.removeByTaskId(1);
        scheduler.displayTasks();
    }
}
