package airline;

import java.util.*;

public class FlightManager {
    private Map<String, Flight> flights;
    private Map<String, Passenger> passengers;
    private List<Reservation> reservations;
    private int passengerCounter;
    private int seatCounter;

    public FlightManager() {
        flights = new LinkedHashMap<>();
        passengers = new LinkedHashMap<>();
        reservations = new ArrayList<>();
        passengerCounter = 100;
        seatCounter = 1;
        loadSampleData();
    }

    // ─────────────── SAMPLE DATA ───────────────
    private void loadSampleData() {
        addFlight(new Flight("AI101", "Mumbai",    "Delhi",     "2024-06-10", "06:00", "08:15", 10, 4500));
        addFlight(new Flight("AI202", "Delhi",     "Bangalore", "2024-06-10", "09:30", "12:00", 8,  5200));
        addFlight(new Flight("AI303", "Bangalore", "Chennai",   "2024-06-11", "11:00", "12:15", 6,  2800));
        addFlight(new Flight("AI404", "Mumbai",    "Kolkata",   "2024-06-11", "14:00", "17:30", 10, 6100));
        addFlight(new Flight("AI505", "Hyderabad", "Mumbai",    "2024-06-12", "08:45", "10:30", 8,  3900));
        addFlight(new Flight("AI606", "Delhi",     "Pune",      "2024-06-12", "15:00", "17:00", 6,  3300));
    }

    // ─────────────── FLIGHT OPERATIONS ───────────────
    public void addFlight(Flight f) {
        flights.put(f.getFlightNumber(), f);
    }

    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber.toUpperCase());
    }

    public boolean removeFlight(String flightNumber) {
        return flights.remove(flightNumber.toUpperCase()) != null;
    }

    public void displayAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("  No flights available.");
            return;
        }
        System.out.println("\n" + "─".repeat(100));
        System.out.printf("%-10s %-12s %-12s %-12s %-8s %-8s %-8s %-12s %s%n",
            "Flight", "From", "To", "Date", "Dep", "Arr", "Seats", "Price", "Status");
        System.out.println("─".repeat(100));
        for (Flight f : flights.values()) {
            System.out.println(f);
        }
        System.out.println("─".repeat(100));
    }

    public List<Flight> searchFlights(String source, String destination, String date) {
        List<Flight> results = new ArrayList<>();
        for (Flight f : flights.values()) {
            if (f.getSource().equalsIgnoreCase(source) &&
                f.getDestination().equalsIgnoreCase(destination) &&
                f.getDate().equals(date) &&
                !f.getStatus().equals("CANCELLED")) {
                results.add(f);
            }
        }
        return results;
    }

    public boolean updateFlightStatus(String flightNumber, String status) {
        Flight f = getFlight(flightNumber);
        if (f != null) {
            f.setStatus(status);
            return true;
        }
        return false;
    }

    // ─────────────── PASSENGER OPERATIONS ───────────────
    public Passenger addPassenger(String name, int age, String gender,
                                  String passport, String contact, String email) {
        String pid = "P" + (++passengerCounter);
        Passenger p = new Passenger(pid, name, age, gender, passport, contact, email);
        passengers.put(pid, p);
        return p;
    }

    public Passenger getPassenger(String id) {
        return passengers.get(id.toUpperCase());
    }

    public void displayAllPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("  No passengers registered.");
            return;
        }
        System.out.println("\n" + "─".repeat(95));
        for (Passenger p : passengers.values()) {
            System.out.println(p);
        }
        System.out.println("─".repeat(95));
    }

    // ─────────────── RESERVATION OPERATIONS ───────────────
    public Reservation bookTicket(String flightNumber, String passengerId, String date) {
        Flight f = getFlight(flightNumber);
        Passenger p = getPassenger(passengerId);

        if (f == null)  { System.out.println("  ✗ Flight not found.");    return null; }
        if (p == null)  { System.out.println("  ✗ Passenger not found."); return null; }
        if (f.isFull()) { System.out.println("  ✗ Flight is fully booked."); return null; }
        if (f.getStatus().equals("CANCELLED")) {
            System.out.println("  ✗ Flight is cancelled."); return null;
        }

        boolean booked = f.bookSeat();
        if (!booked) { System.out.println("  ✗ Booking failed."); return null; }

        String seat = "S" + String.format("%02d", seatCounter++);
        Reservation r = new Reservation(flightNumber, passengerId, p.getName(),
                                        seat, f.getTicketPrice(), date);
        reservations.add(r);
        return r;
    }

    public boolean cancelBooking(String bookingId) {
        for (Reservation r : reservations) {
            if (r.getBookingId().equalsIgnoreCase(bookingId) &&
                r.getBookingStatus().equals("CONFIRMED")) {
                r.cancelBooking();
                Flight f = getFlight(r.getFlightNumber());
                if (f != null) f.cancelSeat();
                return true;
            }
        }
        return false;
    }

    public Reservation findBooking(String bookingId) {
        for (Reservation r : reservations) {
            if (r.getBookingId().equalsIgnoreCase(bookingId)) return r;
        }
        return null;
    }

    public void displayAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("  No reservations found.");
            return;
        }
        System.out.println("\n" + "─".repeat(100));
        System.out.printf("%-8s %-10s %-8s %-20s %-6s %-12s %-12s %s%n",
            "BookingID", "Flight", "PaxID", "Name", "Seat", "Amount", "Date", "Status");
        System.out.println("─".repeat(100));
        for (Reservation r : reservations) {
            System.out.println(r);
        }
        System.out.println("─".repeat(100));
    }

    public void displayPassengerBookings(String passengerId) {
        boolean found = false;
        for (Reservation r : reservations) {
            if (r.getPassengerId().equalsIgnoreCase(passengerId)) {
                if (!found) {
                    System.out.println("\n─".repeat(50));
                    found = true;
                }
                r.printTicket();
            }
        }
        if (!found) System.out.println("  No bookings found for passenger: " + passengerId);
    }

    // ─────────────── REPORT ───────────────
    public void generateReport() {
        int confirmed = 0, cancelled = 0;
        double totalRevenue = 0;
        for (Reservation r : reservations) {
            if (r.getBookingStatus().equals("CONFIRMED")) {
                confirmed++;
                totalRevenue += r.getAmountPaid();
            } else {
                cancelled++;
            }
        }
        System.out.println("\n" + "=".repeat(45));
        System.out.println("         SKYLINE AIRLINES - REPORT");
        System.out.println("=".repeat(45));
        System.out.printf("  Total Flights     : %d%n", flights.size());
        System.out.printf("  Total Passengers  : %d%n", passengers.size());
        System.out.printf("  Total Bookings    : %d%n", reservations.size());
        System.out.printf("  Confirmed         : %d%n", confirmed);
        System.out.printf("  Cancelled         : %d%n", cancelled);
        System.out.printf("  Total Revenue     : Rs. %.2f%n", totalRevenue);
        System.out.println("=".repeat(45));
    }
}
