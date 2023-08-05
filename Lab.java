/**
 * A lab with a morning and afternoon Session, but also a max capacity and location
 *
 * @author Darren Wu
 * @version November 17, 2021
 */
public class Lab {
    private Session morning; //
    private Session afternoon; //
    private int capacity; //
    private String location; //

    public Lab(Session morning, Session afternoon, int capacity, String location) {
        this.morning = morning;
        this.afternoon = afternoon;
        this.capacity = capacity;
        this.location = location;
    }

    public Lab(int capacity, String location) {
        morning = new Session();
        afternoon = new Session();
        this.capacity = capacity;
        this.location = location;
    }

    public Session getMorning() {
        return morning;
    }

    public Session getAfternoon() {
        return afternoon;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setMorning(Session morning) {
        this.morning = morning;
    }

    public void setAfternoon(Session afternoon) {
        this.afternoon = afternoon;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String listAvailabilities() {
        String result = ""; // String containing which sessions are available

        if (morning.getEnrollment() == 0) {
            result = result + "Morning: Available\n";
        }

        if (afternoon.getEnrollment() == 0) {
            result = result + "Afternoon: Available\n";
        }

        if (result.length() == 0) {
            result = "No Availabilities";
        }
        return result;
    }

    public String listReservations() {
        String result = ""; // String containing which sessions are reserved

        if (morning.getEnrollment() > 0) {
            result = result + "Morning: Reserved\n";
        }

        if (afternoon.getEnrollment() > 0) {
            result = result + "Afternoon: Reserved\n";
        }

        if (result.length() == 0) {
            result = "No Reservations";
        }
        return result;
    }

    public String toString() {
        String sum = ""; // Summary of the object to be returned
        String morningStr;
        String afternoonStr;

        if (morning.getEnrollment() == 0) {
            morningStr = "Available";
        } else {
            morningStr = morning.toString();
        }

        if (afternoon.getEnrollment() == 0) {
            afternoonStr = "Available";
        } else {
            afternoonStr = afternoon.toString();
        }

        sum = String.format("Lab{Capacity - %d, Location - %s,", capacity, location);
        sum = sum + String.format("Morning: %s, Afternoon: %s}", morningStr, afternoonStr);
        return sum;
    }
}