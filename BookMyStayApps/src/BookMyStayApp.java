import java.util.*;

// Custom exception for invalid cancellation
class InvalidCancellationException extends Exception {
    public InvalidCancellationException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;
    private String roomID;

    public Reservation(String guestName, String roomType, String roomID) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomID = roomID;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomID() {
        return roomID;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room: " + roomType + ", ID: " + roomID;
    }
}

// Booking system with cancellation and rollback
class BookingSystem {
    private List<Reservation> reservations;
    private Map<String, Integer> inventory; // room type -> count
    private Stack<String> cancelledRoomIDs; // tracks last cancelled rooms

    public BookingSystem(int standard, int deluxe, int suite) {
        reservations = new ArrayList<>();
        inventory = new HashMap<>();
        inventory.put("Standard", standard);
        inventory.put("Deluxe", deluxe);
        inventory.put("Suite", suite);
        cancelledRoomIDs = new Stack<>();
    }

    // Add a reservation
    public void addReservation(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            System.out.println("No " + roomType + " rooms available!");
            return;
        }

        // Generate a simple room ID
        String roomID = roomType.substring(0, 1).toUpperCase() + (available);
        Reservation res = new Reservation(guestName, roomType, roomID);
        reservations.add(res);
        inventory.put(roomType, available - 1);

        System.out.println("Booking confirmed: " + res);
    }

    // Cancel a reservation
    public void cancelReservation(String guestName, String roomID) {
        try {
            Reservation toCancel = null;
            for (Reservation r : reservations) {
                if (r.getGuestName().equalsIgnoreCase(guestName) &&
                        r.getRoomID().equalsIgnoreCase(roomID)) {
                    toCancel = r;
                    break;
                }
            }

            if (toCancel == null) {
                throw new InvalidCancellationException(
                        "Cancellation failed: No matching reservation found for " + guestName + " with ID " + roomID
                );
            }

            // Perform rollback
            reservations.remove(toCancel);
            cancelledRoomIDs.push(toCancel.getRoomID());

            // Restore inventory
            inventory.put(toCancel.getRoomType(), inventory.get(toCancel.getRoomType()) + 1);

            System.out.println("Booking cancelled successfully: " + toCancel);
        } catch (InvalidCancellationException e) {
            System.out.println(e.getMessage());
        }
    }

    // Print current bookings
    public void printReservations() {
        System.out.println("\n--- Current Bookings ---");
        if (reservations.isEmpty()) {
            System.out.println("No active bookings.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }

    // Print cancelled room stack
    public void printCancelledRooms() {
        System.out.println("\n--- Recently Cancelled Room IDs ---");
        if (cancelledRoomIDs.isEmpty()) {
            System.out.println("No cancellations yet.");
        } else {
            for (String id : cancelledRoomIDs) {
                System.out.println(id);
            }
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem(2, 1, 1);

        // Add some reservations
        system.addReservation("Alice", "Deluxe");
        system.addReservation("Bob", "Standard");
        system.addReservation("Charlie", "Standard");

        // Cancel a reservation
        system.cancelReservation("Bob", "S2"); // Undo last Standard booking

        // Attempt invalid cancellation
        system.cancelReservation("Dave", "S3"); // No such booking

        system.printReservations();
        system.printCancelledRooms();
    }
}