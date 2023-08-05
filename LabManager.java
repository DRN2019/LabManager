/**
 * A manager that can manage the user's interactions with up to 3 different labs
 *
 * @author Darren Wu
 * @version November 17, 2021
 */
public class LabManager {
    private Lab labOne; // The first lab of this LabManager
    private Lab labTwo; // The second lab of this LabManager
    private Lab labThree; // The third lab of this LabManager

    public LabManager(Lab labOne, Lab labTwo, Lab labThree) {
        this.labOne = labOne;
        this.labTwo = labTwo;
        this.labThree = labThree;
    }

    public Lab getLabOne() {
        return labOne;
    }

    public Lab getLabTwo() {
        return labTwo;
    }

    public Lab getLabThree() {
        return labThree;
    }

    public void setLabOne(Lab labOne) {
        this.labOne = labOne;
    }

    public void setLabTwo(Lab labTwo) {
        this.labTwo = labTwo;
    }

    public void setLabThree(Lab labThree) {
        this.labThree = labThree;
    }

    public int calculateTotalCapacity() {
        int l1Cap;
        int l2Cap;
        int l3Cap;

        l1Cap = labOne.getCapacity();
        l2Cap = labTwo.getCapacity();
        l3Cap = labThree.getCapacity();

        return l1Cap * 2 + l2Cap * 2 + l3Cap * 2;
    }

    public double calculateTotalUtilization() {
        double occupied = 0.00;
        double total = 0.00;
        double result;

        // Lab One
        occupied += labOne.getMorning().getEnrollment() + labOne.getAfternoon().getEnrollment();
        total += labOne.getCapacity() * 2;

        // Lab Two
        occupied += labTwo.getMorning().getEnrollment() + labTwo.getAfternoon().getEnrollment();
        total += labTwo.getCapacity() * 2;

        // Lab Three
        occupied += labThree.getMorning().getEnrollment() + labThree.getAfternoon().getEnrollment();
        total += labThree.getCapacity() * 2;

        result = occupied / total;
        return result;
    }

    public int calculateAvailableSeats() {
        int occupied = 0;
        int total = 0;
        int result;

        // Lab One
        occupied += labOne.getMorning().getEnrollment() + labOne.getAfternoon().getEnrollment();
        total += labOne.getCapacity() * 2;

        // Lab Two
        occupied += labTwo.getMorning().getEnrollment() + labTwo.getAfternoon().getEnrollment();
        total += labTwo.getCapacity() * 2;

        // Lab Three
        occupied += labThree.getMorning().getEnrollment() + labThree.getAfternoon().getEnrollment();
        total += labThree.getCapacity() * 2;

        result = total - occupied;
        return result;
    }

    public String listReservedLabs() {
        String result; // String containing all reservations of labs
        String l1Res = "Lab One\n"; // String with labOne reservations
        String l2Res = "Lab Two\n"; // String with labTwo reservations
        String l3Res = "Lab Three\n"; // String with labThree reservations

        // Lab One
        if (labOne.getMorning().getEnrollment() > 0) {
            l1Res = l1Res + "Morning: Reserved\n";
        }
        if (labOne.getAfternoon().getEnrollment() > 0) {
            l1Res = l1Res + "Afternoon: Reserved\n";
        }
        if (l1Res.length() == 8) {
            l1Res = l1Res + "No Reservations\n";
        }

        // Lab Two
        if (labTwo.getMorning().getEnrollment() > 0) {
            l2Res = l2Res + "Morning: Reserved\n";
        }
        if (labTwo.getAfternoon().getEnrollment() > 0) {
            l2Res = l2Res + "Afternoon: Reserved\n";
        }
        if (l2Res.length() == 8) {
            l2Res = l2Res + "No Reservations\n";
        }

        // Lab Three
        if (labThree.getMorning().getEnrollment() > 0) {
            l3Res = l3Res + "Morning: Reserved\n";
        }
        if (labThree.getAfternoon().getEnrollment() > 0) {
            l3Res = l3Res + "Afternoon: Reserved\n";
        }
        if (l3Res.length() == 10) {
            l3Res = l3Res + "No Reservations\n";
        }

        result = String.format("%s\n%s\n%s", l1Res, l2Res, l3Res);
        return result;
    }

    public String listAvailableLabs() {
        String result; // String containing all reservations of labs
        String l1Res = "Lab One\n"; // String with labOne reservations
        String l2Res = "Lab Two\n"; // String with labTwo reservations
        String l3Res = "Lab Three\n"; // String with labThree reservations

        // Lab One
        if (labOne.getMorning().getEnrollment() == 0) {
            l1Res = l1Res + "Morning: Available\n";
        }
        if (labOne.getAfternoon().getEnrollment() == 0) {
            l1Res = l1Res + "Afternoon: Available\n";
        }
        if (l1Res.length() == 8) {
            l1Res = l1Res + "No Availabilities\n";
        }

        // Lab Two
        if (labTwo.getMorning().getEnrollment() == 0) {
            l2Res = l2Res + "Morning: Available\n";
        }
        if (labTwo.getAfternoon().getEnrollment() == 0) {
            l2Res = l2Res + "Afternoon: Available\n";
        }
        if (l2Res.length() == 8) {
            l2Res = l2Res + "No Availabilities\n";
        }

        // Lab Three
        if (labThree.getMorning().getEnrollment() == 0) {
            l3Res = l3Res + "Morning: Available\n";
        }
        if (labThree.getAfternoon().getEnrollment() == 0) {
            l3Res = l3Res + "Afternoon: Available\n";
        }
        if (l3Res.length() == 10) {
            l3Res = l3Res + "No Availabilities\n";
        }

        result = String.format("%s\n%s\n%s", l1Res, l2Res, l3Res);
        return result;
    }

    public String addReservation(String location, String time, String name, int enrollment) {
        if (location.equals(labOne.getLocation())) {
            if (time.toLowerCase().equals("morning") && labOne.getMorning().getEnrollment() == 0) {
                if (enrollment <= labOne.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labOne.setMorning(morning);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labOne.getAfternoon().getEnrollment() == 0) {
                if (enrollment <= labOne.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labOne.setAfternoon(afternoon);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labTwo.getLocation())) {
            if (time.toLowerCase().equals("morning") && labTwo.getMorning().getEnrollment() == 0) {
                if (enrollment <= labTwo.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labTwo.setMorning(morning);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labTwo.getAfternoon().getEnrollment() == 0) {
                if (enrollment <= labTwo.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labTwo.setAfternoon(afternoon);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labThree.getLocation())) {
            if (time.toLowerCase().equals("morning") && labThree.getMorning().getEnrollment() == 0) {
                if (enrollment <= labThree.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labThree.setMorning(morning);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labThree.getAfternoon().getEnrollment() == 0) {
                if (enrollment <= labThree.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labThree.setAfternoon(afternoon);
                    return "Reservation added!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else {
            return "Error. Invalid location";
        }
    }

    public String removeReservation(String location, String time) {
        if (location.equals(labOne.getLocation())) {
            if (time.toLowerCase().equals("morning") && labOne.getMorning().getEnrollment() > 0) {
                labOne.setMorning(new Session());
                return "Reservation removed!";
            } else if (time.toLowerCase().equals("afternoon") && labOne.getAfternoon().getEnrollment() > 0) {
                labOne.setAfternoon(new Session());
                return "Reservation removed!";
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labTwo.getLocation())) {
            if (time.toLowerCase().equals("morning") && labTwo.getMorning().getEnrollment() > 0) {
                labTwo.setMorning(new Session());
                return "Reservation removed!";
            } else if (time.toLowerCase().equals("afternoon") && labTwo.getAfternoon().getEnrollment() > 0) {
                labTwo.setAfternoon(new Session());
                return "Reservation removed!";
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labThree.getLocation())) {
            if (time.toLowerCase().equals("morning") && labThree.getMorning().getEnrollment() > 0) {
                labThree.setMorning(new Session());
                return "Reservation removed!";
            } else if (time.toLowerCase().equals("afternoon") && labThree.getAfternoon().getEnrollment() > 0) {
                labThree.setAfternoon(new Session());
                return "Reservation removed!";
            } else {
                return "Error. Invalid time.";
            }
        } else {
            return "Error. Invalid location";
        }
    }

    public String modifyReservation(String location, String time, String name, int enrollment) {
        if (location.equals(labOne.getLocation())) {
            if (time.toLowerCase().equals("morning") && labOne.getMorning().getEnrollment() > 0) {
                if (enrollment <= labOne.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labOne.setMorning(morning);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labOne.getAfternoon().getEnrollment() > 0) {
                if (enrollment <= labOne.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labOne.setAfternoon(afternoon);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labTwo.getLocation())) {
            if (time.toLowerCase().equals("morning") && labTwo.getMorning().getEnrollment() > 0) {
                if (enrollment <= labTwo.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labTwo.setMorning(morning);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labTwo.getAfternoon().getEnrollment() > 0) {
                if (enrollment <= labTwo.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labTwo.setAfternoon(afternoon);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else if (location.equals(labThree.getLocation())) {
            if (time.toLowerCase().equals("morning") && labThree.getMorning().getEnrollment() > 0) {
                if (enrollment <= labThree.getCapacity() && enrollment >= 0) {
                    Session morning = new Session(name, enrollment);
                    labThree.setMorning(morning);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else if (time.toLowerCase().equals("afternoon") && labThree.getAfternoon().getEnrollment() > 0) {
                if (enrollment <= labThree.getCapacity() && enrollment >= 0) {
                    Session afternoon = new Session(name, enrollment);
                    labThree.setAfternoon(afternoon);
                    return "Reservation modified!";
                } else {
                    return "Error. Capacity exceeded";
                }
            } else {
                return "Error. Invalid time.";
            }
        } else {
            return "Error. Invalid location";
        }
    }

    public String toString() {
        return String.format("LabManager{%s, %s, %s}", labOne.toString(), labTwo.toString(), labThree.toString());
    }
}