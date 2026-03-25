import java.util.*;

public class BookMyStayApp {

    public static void main(String[] args) {

        // 🔹 Existing Reservations (from UC6 simulation)
        List<String> reservations = new ArrayList<>();
        reservations.add("SI1");
        reservations.add("DO2");
        reservations.add("SU3");

        // 🔹 Add-On Services (Service → Cost)
        HashMap<String, Integer> serviceCatalog = new HashMap<>();
        serviceCatalog.put("Breakfast", 500);
        serviceCatalog.put("WiFi", 200);
        serviceCatalog.put("Airport Pickup", 1000);

        // 🔹 Reservation → List of Services
        HashMap<String, List<String>> reservationServices = new HashMap<>();

        System.out.println("===== Add-On Service Selection =====\n");

        // 🔹 Guest selects services
        reservationServices.put("SI1", new ArrayList<>(Arrays.asList("Breakfast", "WiFi")));
        reservationServices.put("DO2", new ArrayList<>(Arrays.asList("Airport Pickup")));
        reservationServices.put("SU3", new ArrayList<>(Arrays.asList("Breakfast", "Airport Pickup")));

        // 🔹 Display services + calculate cost
        for (String reservationId : reservationServices.keySet()) {

            List<String> services = reservationServices.get(reservationId);
            int totalCost = 0;

            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Selected Services:");

            for (String service : services) {
                int cost = serviceCatalog.get(service);
                totalCost += cost;

                System.out.println("- " + service + " (₹" + cost + ")");
            }

            System.out.println("Total Add-On Cost: ₹" + totalCost);
            System.out.println();
        }

        System.out.println("===== End of Add-On Services =====");
    }
}