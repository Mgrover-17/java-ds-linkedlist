class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobin {
    private Process head;
    private Process tail;
    private int timeQuantum;

    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    public void removeProcess(int processId) {
        if (head == null) return;
        Process current = head, prev = null;
        do {
            if (current.processId == processId) {
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

    public void simulateScheduling() {
        if (head == null) return;
        Process current = head;
        int totalWaitingTime = 0, totalTurnaroundTime = 0, processCount = 0;

        System.out.println("Simulating Round Robin Scheduling with Time Quantum: " + timeQuantum);
        while (head != null) {
            System.out.println("Processing: " + current.processId + " | Burst Time Left: " + current.burstTime);
            if (current.burstTime > timeQuantum) {
                current.burstTime -= timeQuantum;
                current = current.next;
            } else {
                totalTurnaroundTime += current.burstTime;
                totalWaitingTime += totalTurnaroundTime - current.burstTime;
                System.out.println("Process " + current.processId + " completed.");
                removeProcess(current.processId);
            }
            processCount++;
            displayProcesses();
        }
        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processCount);
    }

    public void displayProcesses() {
        if (head == null) return;
        Process temp = head;
        do {
            System.out.println(temp.processId + " | Burst Time: " + temp.burstTime + " | Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        RoundRobin scheduler = new RoundRobin(4);
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        System.out.println("Initial Processes:");
        scheduler.displayProcesses();

        scheduler.simulateScheduling();
    }
}
