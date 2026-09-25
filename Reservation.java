package airline;

public class Reservation {
    private String bookingId;
    private String flightNumber;
    private String passengerId;
    private String passengerName;
    private String seatNumber;
    private double amountPaid;
    private String bookingDate;
    private String bookingStatus; // CONFIRMED, CANCELLED, PENDING

    private static int counter = 1000;

    public Reservation(String flightNumber, String passengerId,
                       String passengerName, String seatNumber,
                       double amountPaid, String bookingDate) {
        this.bookingId = "BK" + (++counter);
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.amountPaid = amountPaid;
        this.bookingDate = bookingDate;
        this.bookingStatus = "CONFIRMED";
    }

    // Getters
    public String getBookingId()      { return bookingId; }
    public String getFlightNumber()   { return flightNumber; }
    public String getPassengerId()    { return passengerId; }
    public String getPassengerName()  { return passengerName; }
    public String getSeatNumber()     { return seatNumber; }
    public double getAmountPaid()     { return amountPaid; }
    public String getBookingDate()    { return bookingDate; }
    public String getBookingStatus()  { return bookingStatus; }

    public void cancelBooking() {
        this.bookingStatus = "CANCELLED";
    }

    public void printTicket() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("         ✈  SKYLINE AIRLINES - E-TICKET  ✈");
        System.out.println("=".repeat(55));
        System.out.printf(" Booking ID    : %s%n", bookingId);
        System.out.printf(" Flight No     : %s%n", flightNumber);
        System.out.printf(" Passenger     : %s  (ID: %s)%n", passengerName, passengerId);
        System.out.printf(" Seat Number   : %s%n", seatNumber);
        System.out.printf(" Booking Date  : %s%n", bookingDate);
        System.out.printf(" Amount Paid   : Rs. %.2f%n", amountPaid);
        System.out.printf(" Status        : %s%n", bookingStatus);
        System.out.println("=".repeat(55));
        System.out.println("   Thank you for flying with Skyline Airlines!");
        System.out.println("=".repeat(55));
    }

    @Override
    public String toString() {
        return String.format("%-8s %-10s %-8s %-20s %-6s Rs.%-8.0f %-12s %s",
            bookingId, flightNumber, passengerId, passengerName,
            seatNumber, amountPaid, bookingDate, bookingStatus);
    }
}
