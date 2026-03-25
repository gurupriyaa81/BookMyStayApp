import java.util.*;

public class BookMyStayApp {

    public static void main(String[] args) {

        // 🔹 Booking Request Queue (FIFO)
        Queue<String> bookingQueue = new LinkedList<>();

        // Guest booking requests (NO inventory update here)
        System.out.println("Adding booking requests...\n");

        bookingQueue.add("Request 1: Single Room");
        bookingQueue.add("Request 2: Double Room");
        bookingQueue.add("Request 3: Suite Room");
        bookingQueue.add("Request 4: Single Room");

        // Display queue (arrival order preserved)
        System.out.println("===== Booking Request Queue =====");
        for (String request : bookingQueue) {
            System.out.println(request);
        }

        // Peek first request (who will be served first)
        System.out.println("\nNext to process (FIFO): " + bookingQueue.peek());

        // Important: NO removal or inventory update here
        System.out.println("\nRequests are waiting for allocation...");
    }
}