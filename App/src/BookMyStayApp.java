import java.util.*;

public class BookMyStayApp {

    public static void main(String[] args) {

        // 🔹 Inventory (Room Type → Available Count)
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        // 🔹 Booking Request Queue (FIFO)
        Queue<String> bookingQueue = new LinkedList<>();
        bookingQueue.add("Single Room");
        bookingQueue.add("Double Room");
        bookingQueue.add("Single Room");
        bookingQueue.add("Suite Room");
        bookingQueue.add("Single Room"); // extra request (to test limit)

        // 🔹 Allocated Room Tracking
        Set<String> allocatedRoomIds = new HashSet<>();

        // 🔹 Room Type → Assigned Room IDs
        HashMap<String, Set<String>> allocationMap = new HashMap<>();

        System.out.println("===== Processing Bookings =====\n");

        int roomCounter = 1;

        // 🔹 Process Queue (FIFO)
        while (!bookingQueue.isEmpty()) {

            String roomType = bookingQueue.poll();
            int available = inventory.getOrDefault(roomType, 0);

            System.out.println("Processing request for: " + roomType);

            // Check availability
            if (available > 0) {

                // Generate unique room ID
                String roomId = roomType.substring(0, 2).toUpperCase() + roomCounter++;

                // Ensure uniqueness using Set
                if (!allocatedRoomIds.contains(roomId)) {

                    allocatedRoomIds.add(roomId);

                    // Store allocation per room type
                    allocationMap.putIfAbsent(roomType, new HashSet<>());
                    allocationMap.get(roomType).add(roomId);

                    // Decrement inventory immediately
                    inventory.put(roomType, available - 1);

                    System.out.println("✅ Booking Confirmed | Room ID: " + roomId);
                }

            } else {
                System.out.println("❌ Booking Failed | No rooms available");
            }

            System.out.println();
        }

        // 🔹 Final Allocation Summary
        System.out.println("===== Allocation Summary =====");
        for (String type : allocationMap.keySet()) {
            System.out.println(type + " → " + allocationMap.get(type));
        }

        // 🔹 Remaining Inventory
        System.out.println("\n===== Remaining Inventory =====");
        for (String type : inventory.keySet()) {
            System.out.println(type + " → " + inventory.get(type));
        }
    }
}