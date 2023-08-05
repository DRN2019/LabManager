import java.util.Scanner;

/**
 * The interface in which the user can add, modify, or remove reservations for labs
 *
 * @author Darren Wu
 * @version November 17, 2021
 */
public class TimeKeeper {

    private static String welcomePrompt = "Welcome to the TimeKeeper application!";
    private static String invalidInput = "Invalid input. Please try again.";
    private static String enterLabCapacity = "Enter the capacity for Lab ";
    private static String enterLabLocation = "Enter the location for Lab ";
    private static String labLocationPrompt = "Enter the location of the lab:";
    private static String reservationTimePrompt = "Enter the time of the reservation:";
    private static String reservationNamePrompt = "Enter the name of the reservation:";
    private static String reservationEnrollmentPrompt = "Enter the expected enrollment:";
    private static String reservationNameUpdate = "Enter the updated name of the reservation:";
    private static String reservationEnrollmentUpdate = "Enter the updated enrollment:";
    private static String totalCapacity = "Total Capacity: ";
    private static String totalUtilization = "Total Utilization: ";
    private static String availableSeats = "Available seats: ";


    private static String initializeMenu = "1. Initialize Application\n" +
            "2. Exit";
    private static String ongoingMenu = "1. View Current Lab Schedule\n" +
            "2. List Labs by Availability\n" +
            "3. List Labs by Reservation\n" +
            "4. Add a Reservation\n" +
            "5. Remove a Reservation\n" +
            "6. Modify a Reservation\n" +
            "7. Calculate Statistics\n" +
            "8. Exit";
    private static String statisticsMenu = "1. Total Capacity\n" +
            "2. Total Utilization\n" +
            "3. Available seats\n" +
            "4. Return to main menu";
    private static String exitMessage = "Thank you for using TimeKeeper!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(welcomePrompt);
        int choiceOne = -1;
        do {
            System.out.println(initializeMenu);
            choiceOne = sc.nextInt();
            sc.nextLine();
            if (choiceOne < 1 || choiceOne > 2) {
                System.out.println(invalidInput);
            }
        } while (choiceOne < 1 || choiceOne > 2);

        if (choiceOne == 1) {
            int capacity;
            String location;
            Lab[] labs = new Lab[3];
            for (int i = 1; i <= 3; i++) {
                System.out.println(enterLabCapacity + i + ":");
                capacity = sc.nextInt();
                sc.nextLine();
                System.out.println(enterLabLocation + i + ":");
                location = sc.nextLine();

                labs[i - 1] = new Lab(new Session(), new Session(), capacity, location);
            }

            LabManager lm = new LabManager(labs[0], labs[1], labs[2]);
            int choiceTwo;
            do {
                boolean valid = true;
                System.out.println(ongoingMenu);
                choiceTwo = sc.nextInt();
                sc.nextLine();

                String loc;
                String time;
                String name;
                int enroll;

                switch (choiceTwo) {
                    case 1:
                        String l1 = lm.getLabOne().toString();
                        String l2 = lm.getLabTwo().toString();
                        String l3 = lm.getLabThree().toString();
                        System.out.println("\n" + l1 + "\n" + l2 + "\n" + l3);
                        break;
                    case 2:
                        System.out.println(lm.listAvailableLabs());
                        break;
                    case 3:
                        System.out.println(lm.listReservedLabs());
                        break;
                    case 4:
                        System.out.println(labLocationPrompt);
                        loc = sc.nextLine();
                        System.out.println(reservationTimePrompt);
                        time = sc.nextLine();
                        System.out.println(reservationNamePrompt);
                        name = sc.nextLine();
                        System.out.println(reservationEnrollmentPrompt);
                        enroll = sc.nextInt();
                        sc.nextLine();

                        System.out.println(lm.addReservation(loc, time, name, enroll));
                        break;
                    case 5:
                        System.out.println(labLocationPrompt);
                        loc = sc.nextLine();
                        System.out.println(reservationTimePrompt);
                        time = sc.nextLine();

                        System.out.println(lm.removeReservation(loc, time));
                        break;
                    case 6:
                        System.out.println(labLocationPrompt);
                        loc = sc.nextLine();
                        System.out.println(reservationTimePrompt);
                        time = sc.nextLine();
                        System.out.println(reservationNameUpdate);
                        name = sc.nextLine();
                        System.out.println(reservationEnrollmentUpdate);
                        enroll = sc.nextInt();
                        sc.nextLine();

                        System.out.println(lm.modifyReservation(loc, time, name, enroll));
                        break;
                    case 7:
                        int choiceThree;
                        do {
                            System.out.println(statisticsMenu);
                            choiceThree = sc.nextInt();
                            sc.nextLine();

                            String statOutput;
                            switch (choiceThree) {
                                case 1:
                                    int cap = lm.calculateTotalCapacity();
                                    statOutput = String.format("Total Capacity: %d", cap);
                                    System.out.println(statOutput);
                                    break;
                                case 2:
                                    double util = lm.calculateTotalUtilization();
                                    statOutput = String.format("Total Utilization: %.2f", util);
                                    System.out.println(statOutput);
                                    break;
                                case 3:
                                    int seats = lm.calculateAvailableSeats();
                                    statOutput = String.format("Available seats: %d", seats);
                                    System.out.println(statOutput);
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println(invalidInput);
                                    break;
                            }
                        } while (choiceThree != 4);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println(invalidInput);
                        break;
                }
            } while (choiceTwo != 8);
        }

        System.out.println(exitMessage);
    }
}