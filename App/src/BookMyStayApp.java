public class BookMyStayApp {
    static void main() {
        abstract class Room {
            private String roomType;
            private int beds;
            private double pricePerNight;

            // Constructor
            public Room(String roomType, int beds, double pricePerNight) {
                this.roomType = roomType;
                this.beds = beds;
                this.pricePerNight = pricePerNight;
            }

            // Getters (Encapsulation)
            public String getRoomType() {
                return roomType;
            }

            public int getBeds() {
                return beds;
            }

            public double getPricePerNight() {
                return pricePerNight;
            }

            // Common method
            public void displayDetails() {
                System.out.println("Room Type: " + roomType);
                System.out.println("Beds: " + beds);
                System.out.println("Price per night: ₹" + pricePerNight);
            }
        }

// Single Room
        class SingleRoom extends Room {
            public SingleRoom() {
                super("Single Room", 1, 2000);
            }
        }

// Double Room
        class DoubleRoom extends Room {
            public DoubleRoom() {
                super("Double Room", 2, 3500);
            }
        }

// Suite Room
        class SuiteRoom extends Room {
            public SuiteRoom() {
                super("Suite Room", 3, 6000);
            }
        }

       class HotelBookingApp {
             {

                // Create Room Objects (Polymorphism)
                Room single = new SingleRoom();
                Room doubleRoom = new DoubleRoom();
                Room suite = new SuiteRoom();

                // Static Availability (No Data Structures Yet)
                int singleAvailable = 5;
                int doubleAvailable = 3;
                int suiteAvailable = 2;

                // Display Information
                System.out.println("===== Hotel Room Availability =====\n");

                single.displayDetails();
                System.out.println("Available: " + singleAvailable);
                System.out.println();

                doubleRoom.displayDetails();
                System.out.println("Available: " + doubleAvailable);
                System.out.println();

                suite.displayDetails();
                System.out.println("Available: " + suiteAvailable);

                System.out.println("\n===== End of Listing =====");
            }
        }
    }

}
