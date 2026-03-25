import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) throws InvalidBookingException {
        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty!");
        }
        if (!roomType.equalsIgnoreCase("Standard") &&
                !roomType.equalsIgnoreCase("Deluxe") &&
                !roomType.equalsIgnoreCase("Suite")) {
            throw new InvalidBookingException("Invalid room type! Choose Standard, Deluxe, or Suite.");
        }
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// Booking system with error handling
class BookingSystem {
    private List<Reservation> reservations;
    private int standardRooms;
    private int deluxeRooms;
    private int suiteRooms;

    public BookingSystem(int standardRooms, int deluxeRooms, int suiteRooms) {
        reservations = new ArrayList<>();
        this.standardRooms = standardRooms;
        this.deluxeRooms = deluxeRooms;
        this.suiteRooms = suiteRooms;
    }

    public void addReservation(String guestName, String roomType) {
        try {
            // Validate inventory
            if (roomType.equalsIgnoreCase("Standard") && standardRooms <= 0) {
                throw new InvalidBookingException("No Standard rooms available!");
            }
            if (roomType.equalsIgnoreCase("Deluxe") && deluxeRooms <= 0) {
                throw new InvalidBookingException("No Deluxe rooms available!");
            }
            if (roomType.equalsIgnoreCase("Suite") && suiteRooms <= 0) {
                throw new InvalidBookingException("No Suite rooms available!");
            }

            // Create reservation (validates guest and room type)
            Reservation reservation = new Reservation(guestName, roomType);
            reservations.add(reservation);

            // Reduce inventory
            if (roomType.equalsIgnoreCase("Standard")) standardRooms--;
            if (roomType.equalsIgnoreCase("Deluxe")) deluxeRooms--;
            if (roomType.equalsIgnoreCase("Suite")) suiteRooms--;

            System.out.println("Booking confirmed: " + reservation);

        } catch (InvalidBookingException e) {
            // Graceful error handling
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    public void printReservations() {
        System.out.println("\n--- Current Bookings ---");
        if (reservations.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem(2, 1, 1); // Sample inventory

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Book My Stay App");

        // Example bookings
        system.addReservation("Alice", "Deluxe");
        system.addReservation("", "Standard"); // Invalid guest name
        system.addReservation("Bob", "Premium"); // Invalid room type
        system.addReservation("Charlie", "Standard");
        system.addReservation("Dave", "Standard"); // Should fail, inventory exhausted

        system.printReservations();
    }
}