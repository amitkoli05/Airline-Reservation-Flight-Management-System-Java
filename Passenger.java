package airline;

public class Passenger {
    private String passengerId;
    private String name;
    private int age;
    private String gender;
    private String passportNumber;
    private String contactNumber;
    private String email;

    public Passenger(String passengerId, String name, int age, String gender,
                     String passportNumber, String contactNumber, String email) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.passportNumber = passportNumber;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getters
    public String getPassengerId()    { return passengerId; }
    public String getName()           { return name; }
    public int getAge()               { return age; }
    public String getGender()         { return gender; }
    public String getPassportNumber() { return passportNumber; }
    public String getContactNumber()  { return contactNumber; }
    public String getEmail()          { return email; }

    @Override
    public String toString() {
        return String.format("ID: %-6s | Name: %-20s | Age: %-3d | Gender: %-6s | Passport: %-12s | Contact: %s",
            passengerId, name, age, gender, passportNumber, contactNumber);
    }
}
