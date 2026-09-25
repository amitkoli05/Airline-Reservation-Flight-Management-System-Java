package airline;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private String date;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;
    private String status; // SCHEDULED, DELAYED, CANCELLED, COMPLETED

    public Flight(String flightNumber, String source, String destination,
                  String date, String departureTime, String arrivalTime,
                  int totalSeats, double ticketPrice) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.status = "SCHEDULED";
    }

    // Getters
    public String getFlightNumber()  { return flightNumber; }
    public String getSource()        { return source; }
    public String getDestination()   { return destination; }
    public String getDate()          { return date; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime()   { return arrivalTime; }
    public int getTotalSeats()       { return totalSeats; }
    public int getAvailableSeats()   { return availableSeats; }
    public double getTicketPrice()   { return ticketPrice; }
    public String getStatus()        { return status; }

    // Setters
    public void setStatus(String status)             { this.status = status; }
    public void setAvailableSeats(int seats)         { this.availableSeats = seats; }
    public void setTicketPrice(double ticketPrice)   { this.ticketPrice = ticketPrice; }
    public void setDepartureTime(String time)        { this.departureTime = time; }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    public boolean isFull() {
        return availableSeats == 0;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-12s %-12s %-12s %-8s %-8s %3d/%-3d  Rs.%-8.0f %s",
            flightNumber, source, destination, date,
            departureTime, arrivalTime,
            availableSeats, totalSeats, ticketPrice, status);
    }
}
