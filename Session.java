/**
 * A single lab session containing name and number enrolled
 *
 * @author Darren Wu
 * @version November 17, 2021
 */
public class Session {
    private String name; // The name of the session
    private int enrollment; // The number of attendees in this session

    public Session(String name, int enrollment) {
        this.name = name;
        this.enrollment = enrollment;
    }

    public Session() {
        name = "";
        enrollment = 0;
    }

    public String getName() {
        return name;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String toString() {
        String sum; // Summary of obj as string

        sum = String.format("Session{Name - %s, Enrollment - %d}", name, enrollment);
        return sum;
    }
}