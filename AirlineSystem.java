package airline;

import java.util.*;

/**
 * ============================================================
 *   SKYLINE AIRLINES - Reservation & Flight Management System
 *   Micro Project | Java Programming
 * ============================================================
 */
public class AirlineSystem {

    static Scanner sc = new Scanner(System.in);
    static FlightManager manager = new FlightManager();

    public static void main(String[] args) {
        printBanner();
        int choice;
        do {
            printMainMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> flightMenu();
                case 2 -> passengerMenu();
                case 3 -> reservationMenu();
                case 4 -> manager.generateReport();
                case 0 -> System.out.println("\n  Thank you for using Skyline Airlines. Bon Voyage! ✈\n");
                default -> System.out.println("  ✗ Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    // ─────────────── BANNERS & MENUS ───────────────
    static void printBanner() {
        System.out.println("\n" + "═".repeat(55));
        System.out.println("   ✈  SKYLINE AIRLINES MANAGEMENT SYSTEM  ✈");
        System.out.println("      Reservation & Flight Management");
        System.out.println("═".repeat(55));
    }

    static void printMainMenu() {
        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│           MAIN MENU             │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│  1. Flight Management           │");
        System.out.println("│  2. Passenger Management        │");
        System.out.println("│  3. Reservation Management      │");
        System.out.println("│  4. View Report                 │");
        System.out.println("│  0. Exit                        │");
        System.out.println("└─────────────────────────────────┘");
    }

    // ─────────────── FLIGHT MENU ───────────────
    static void flightMenu() {
        int choice;
        do {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│       FLIGHT MANAGEMENT         │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. View All Flights            │");
            System.out.println("│  2. Add New Flight              │");
            System.out.println("│  3. Search Flights              │");
            System.out.println("│  4. Update Flight Status        │");
            System.out.println("│  5. Remove Flight               │");
            System.out.println("│  0. Back                        │");
            System.out.println("└─────────────────────────────────┘");
            choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> manager.displayAllFlights();
                case 2 -> addFlight();
                case 3 -> searchFlights();
                case 4 -> updateFlightStatus();
                case 5 -> removeFlight();
                case 0 -> System.out.println("  Returning to main menu...");
                default -> System.out.println("  ✗ Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addFlight() {
        System.out.println("\n  ── Add New Flight ──");
        String fn  = readString("  Flight Number   : ").toUpperCase();
        String src = readString("  Source City     : ");
        String dst = readString("  Destination     : ");
        String dt  = readString("  Date (YYYY-MM-DD): ");
        String dep = readString("  Departure Time  : ");
        String arr = readString("  Arrival Time    : ");
        int seats  = readInt   ("  Total Seats     : ");
        double price = readDouble("  Ticket Price (Rs): ");

        Flight f = new Flight(fn, src, dst, dt, dep, arr, seats, price);
        manager.addFlight(f);
        System.out.println("  ✓ Flight " + fn + " added successfully!");
    }

    static void searchFlights() {
        System.out.println("\n  ── Search Flights ──");
        String src  = readString("  From            : ");
        String dst  = readString("  To              : ");
        String date = readString("  Date (YYYY-MM-DD): ");

        List<Flight> results = manager.searchFlights(src, dst, date);
        if (results.isEmpty()) {
            System.out.println("  ✗ No flights found for the given route/date.");
        } else {
            System.out.println("\n  Found " + results.size() + " flight(s):");
            System.out.println("─".repeat(100));
            System.out.printf("%-10s %-12s %-12s %-12s %-8s %-8s %-8s %-12s %s%n",
                "Flight", "From", "To", "Date", "Dep", "Arr", "Seats", "Price", "Status");
            System.out.println("─".repeat(100));
            for (Flight f : results) System.out.println(f);
            System.out.println("─".repeat(100));
        }
    }

    static void updateFlightStatus() {
        System.out.println("\n  ── Update Flight Status ──");
        String fn = readString("  Flight Number   : ").toUpperCase();
        System.out.println("  Status options: SCHEDULED / DELAYED / CANCELLED / COMPLETED");
        String status = readString("  New Status      : ").toUpperCase();
        if (manager.updateFlightStatus(fn, status)) {
            System.out.println("  ✓ Status updated to: " + status);
        } else {
            System.out.println("  ✗ Flight not found.");
        }
    }

    static void removeFlight() {
        System.out.println("\n  ── Remove Flight ──");
        String fn = readString("  Flight Number: ").toUpperCase();
        if (manager.removeFlight(fn)) {
            System.out.println("  ✓ Flight " + fn + " removed.");
        } else {
            System.out.println("  ✗ Flight not found.");
        }
    }

    // ─────────────── PASSENGER MENU ───────────────
    static void passengerMenu() {
        int choice;
        do {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│     PASSENGER MANAGEMENT        │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Register Passenger          │");
            System.out.println("│  2. View All Passengers         │");
            System.out.println("│  3. Search Passenger by ID      │");
            System.out.println("│  0. Back                        │");
            System.out.println("└─────────────────────────────────┘");
            choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> registerPassenger();
                case 2 -> manager.displayAllPassengers();
                case 3 -> searchPassenger();
                case 0 -> System.out.println("  Returning to main menu...");
                default -> System.out.println("  ✗ Invalid choice.");
            }
        } while (choice != 0);
    }

    static void registerPassenger() {
        System.out.println("\n  ── Register New Passenger ──");
        String name     = readString("  Full Name       : ");
        int age         = readInt   ("  Age             : ");
        String gender   = readString("  Gender (M/F)    : ").toUpperCase();
        String passport = readString("  Passport Number : ").toUpperCase();
        String contact  = readString("  Contact Number  : ");
        String email    = readString("  Email Address   : ");

        Passenger p = manager.addPassenger(name, age, gender, passport, contact, email);
        System.out.println("  ✓ Passenger registered!");
        System.out.println("  ✓ Your Passenger ID: " + p.getPassengerId() + " (Save this for booking)");
    }

    static void searchPassenger() {
        String pid = readString("  Enter Passenger ID: ").toUpperCase();
        Passenger p = manager.getPassenger(pid);
        if (p != null) {
            System.out.println("\n  Passenger Details:");
            System.out.println("  " + p);
        } else {
            System.out.println("  ✗ Passenger not found.");
        }
    }

    // ─────────────── RESERVATION MENU ───────────────
    static void reservationMenu() {
        int choice;
        do {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│    RESERVATION MANAGEMENT       │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  1. Book Ticket                 │");
            System.out.println("│  2. Cancel Booking              │");
            System.out.println("│  3. View All Reservations       │");
            System.out.println("│  4. View Booking by ID          │");
            System.out.println("│  5. View My Bookings (Pax ID)   │");
            System.out.println("│  0. Back                        │");
            System.out.println("└─────────────────────────────────┘");
            choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> bookTicket();
                case 2 -> cancelBooking();
                case 3 -> manager.displayAllReservations();
                case 4 -> viewBookingById();
                case 5 -> viewMyBookings();
                case 0 -> System.out.println("  Returning to main menu...");
                default -> System.out.println("  ✗ Invalid choice.");
            }
        } while (choice != 0);
    }

    static void bookTicket() {
        System.out.println("\n  ── Book Ticket ──");
        manager.displayAllFlights();
        String fn  = readString("  Enter Flight Number : ").toUpperCase();
        String pid = readString("  Enter Passenger ID  : ").toUpperCase();
        String dt  = readString("  Booking Date        : ");

        Reservation r = manager.bookTicket(fn, pid, dt);
        if (r != null) {
            System.out.println("  ✓ Ticket booked successfully!");
            r.printTicket();
        }
    }

    static void cancelBooking() {
        System.out.println("\n  ── Cancel Booking ──");
        String bid = readString("  Enter Booking ID: ").toUpperCase();
        if (manager.cancelBooking(bid)) {
            System.out.println("  ✓ Booking " + bid + " cancelled successfully.");
        } else {
            System.out.println("  ✗ Booking not found or already cancelled.");
        }
    }

    static void viewBookingById() {
        String bid = readString("  Enter Booking ID: ").toUpperCase();
        Reservation r = manager.findBooking(bid);
        if (r != null) {
            r.printTicket();
        } else {
            System.out.println("  ✗ Booking not found.");
        }
    }

    static void viewMyBookings() {
        String pid = readString("  Enter Passenger ID: ").toUpperCase();
        manager.displayPassengerBookings(pid);
    }

    // ─────────────── UTILITY HELPERS ───────────────
    static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Please enter a valid number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Please enter a valid amount.");
            }
        }
    }
}
