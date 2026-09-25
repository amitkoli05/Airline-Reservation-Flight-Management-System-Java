============================================================
  SKYLINE AIRLINES - Reservation & Flight Management System
  Micro Project | Java Programming
============================================================

FILES:
  src/airline/AirlineSystem.java  - Main class (entry point, menu UI)
  src/airline/Flight.java         - Flight entity class
  src/airline/Passenger.java      - Passenger entity class
  src/airline/Reservation.java    - Booking/Reservation class
  src/airline/FlightManager.java  - Core business logic

HOW TO COMPILE & RUN:
  1. Open terminal in the AirlineSystem folder
  2. Compile:
       javac -d out src/airline/*.java
  3. Run:
       java -cp out airline.AirlineSystem

FEATURES:
  ✈ Flight Management
      - Add / View / Search / Update / Remove flights
      - Status tracking: SCHEDULED, DELAYED, CANCELLED, COMPLETED

  ✈ Passenger Management
      - Register passengers with full details
      - View all passengers / search by ID

  ✈ Reservation Management
      - Book ticket with auto seat assignment
      - Cancel booking
      - View all reservations
      - Print E-Ticket
      - View bookings by passenger ID

  ✈ Reports
      - Total flights, passengers, bookings
      - Revenue summary

SAMPLE DATA (preloaded):
  AI101  Mumbai    → Delhi      10-Jun  06:00  Rs.4500
  AI202  Delhi     → Bangalore  10-Jun  09:30  Rs.5200
  AI303  Bangalore → Chennai    11-Jun  11:00  Rs.2800
  AI404  Mumbai    → Kolkata    11-Jun  14:00  Rs.6100
  AI505  Hyderabad → Mumbai     12-Jun  08:45  Rs.3900
  AI606  Delhi     → Pune       12-Jun  15:00  Rs.3300
