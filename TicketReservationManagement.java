class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head;
    private Ticket tail;
    private int totalTickets;

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        totalTickets++;
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;
        Ticket current = head, prev = null;
        do {
            if (current.ticketId == ticketId) {
                if (current == head) {
                    tail.next = head.next;
                    head = head.next;
                } else if (current == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }
                totalTickets--;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void displayTickets() {
        if (head == null) return;
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + " | Customer: " + temp.customerName +
                    " | Movie: " + temp.movieName + " | Seat: " + temp.seatNumber + " | Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicketByCustomer(String customerName) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket Found: " + temp.ticketId + " | Movie: " + temp.movieName + " | Seat: " + temp.seatNumber);
            }
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicketByMovie(String movieName) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket Found: " + temp.ticketId + " | Customer: " + temp.customerName + " | Seat: " + temp.seatNumber);
            }
            temp = temp.next;
        } while (temp != head);
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}

public class TicketReservationManagement {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(1, "John Doe", "Avengers", "A1", "18:00");
        system.addTicket(2, "Jane Smith", "Batman", "B2", "20:00");
        system.addTicket(3, "Alice Brown", "Avengers", "A2", "18:00");

        System.out.println("Current Reservations:");
        system.displayTickets();

        System.out.println("Searching for tickets by customer 'John Doe':");
        system.searchTicketByCustomer("John Doe");

        System.out.println("Searching for tickets by movie 'Avengers':");
        system.searchTicketByMovie("Avengers");

        System.out.println("Total tickets booked: " + system.getTotalTickets());

        System.out.println("Removing Ticket ID 2...");
        system.removeTicket(2);

        System.out.println("Updated Reservations:");
        system.displayTickets();
    }
}
