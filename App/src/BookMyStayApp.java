import java.util.HashMap;

public class BookMyStayApp {

    public static void main(String[] args) {

        // Centralized Inventory (State Holder)
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable
        inventory.put("Suite Room", 2);

        // Room Details (Domain Model - separated from inventory)
        HashMap<String, Integer> price = new HashMap<>();
        price.put("Single Room", 2000);
        price.put("Double Room", 3500);
        price.put("Suite Room", 6000);

        HashMap<String, Integer> beds = new HashMap<>();
        beds.put("Single Room", 1);
        beds.put("Double Room", 2);
        beds.put("Suite Room", 3);

        // 🔍 SEARCH OPERATION (READ-ONLY)
        System.out.println("===== Available Rooms =====");

        for (String roomType : inventory.keySet()) {

            int available = inventory.get(roomType);

            // Defensive Check → show only available rooms
            if (available > 0) {

                System.out.println("Room Type: " + roomType);
                System.out.println("Beds: " + beds.get(roomType));
                System.out.println("Price: ₹" + price.get(roomType));
                System.out.println("Available: " + available);
                System.out.println();
            }
        }

        System.out.println("===== End of Search =====");
    }
}