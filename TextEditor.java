class TextState {
    String content;
    TextState next;
    TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditorTasks {
    private TextState head;
    private TextState tail;
    private TextState current;
    private int size;
    private final int MAX_SIZE = 10;

    public void addState(String content) {
        TextState newState = new TextState(content);
        if (head == null) {
            head = tail = current = newState;
        } else {
            if (current != null && current.next != null) {
                current.next.prev = null;
                current.next = null;
            }
            newState.prev = current;
            if (current != null) {
                current.next = newState;
            }
            current = newState;
            tail = newState;
        }
        size++;
        if (size > MAX_SIZE) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo operations available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo operations available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No content available.");
        }
    }
}

public class TextEditor {
    public static void main(String[] args) {
        TextEditorTasks editor = new TextEditorTasks();

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");

        System.out.println("Displaying current state:");
        editor.displayCurrentState();

        System.out.println("Performing Undo:");
        editor.undo();
        editor.displayCurrentState();

        System.out.println("Performing Undo:");
        editor.undo();
        editor.displayCurrentState();

        System.out.println("Performing Redo:");
        editor.redo();
        editor.displayCurrentState();
    }
}
