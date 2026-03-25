import java.util.HashMap;

public class BookMyStayApp {

    static void main() {

        // Centralized Inventory
        HashMap<String, Integer> inventory = new HashMap<>();

        // Initialize room types
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);

        // Display initial inventory
        System.out.println("===== Room Inventory =====");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " → Available: " + inventory.get(roomType));
        }

        // Simulate booking
        System.out.println("\nBooking 2 Single Rooms...");
        int singleRooms = inventory.get("Single Room");
        if (singleRooms >= 2) {
            inventory.put("Single Room", singleRooms - 2);
        } else {
            System.out.println("Not enough Single Rooms available!");
        }

        // Simulate adding rooms
        System.out.println("Adding 1 Suite Room...");
        inventory.put("Suite Room", inventory.get("Suite Room") + 1);

        // Display updated inventory
        System.out.println("\n===== Updated Inventory =====");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " → Available: " + inventory.get(roomType));
        }
    }
}