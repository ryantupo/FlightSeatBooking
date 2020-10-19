/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ryan's PC not(zuzza)
 */
import java.util.*;

public class week2Exercise7 {

    public week2Exercise7() {

    }

    public static void main(String[] args) {

        boolean[] seats = new boolean[10];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = false;
        }
        System.out.println(Arrays.toString(seats));

        System.out.println("..........Welcome to British Airways..........");

        while (!wholePlaneCheck(seats)) {

            while (true) {
                Scanner obj1 = new Scanner(System.in);
                System.out.println("Which class would you like to book?: ");
                String classV = obj1.nextLine();

                switch (classV) {
                    case "Econemy" -> classCHECK(seats, "Econemy", "First Class", 0, 4);
                    case "First Class" -> classCHECK(seats, "First Class", "Econemy", 5, 9);
                    default -> System.out.println("you have entered an invalid class enter [First Class or Econemy]");
                   
                }

            }
        }
    }

    // 1 array 10 seats / 1-5 ecoon , 6-10 first class / print out name , type of seat , seat number/
    // array set to all false = empty / if take set to true / - check if is taken(give list of empty in that class) / if all in econemy or first is full ask if they want other 
    //if all plane is full say next flight in 3 mins
    //functions needed - check if seat is avaible (a seat check for all seats )
    // - check if all seats in that class are taken
    public static boolean[] seatCheck(boolean seats[], int start, int end, String className, String otherClass) {
        while (true) {
            if (!classSeatCheck(seats, start, end)) {
                Scanner obj2 = new Scanner(System.in);
                System.out.println("Which seat would you like to book?(): ");
                String seatV = obj2.nextLine();

                Integer seatN = (Integer.parseInt(seatV) - 1);

                System.out.println(Arrays.toString(seats));

                if (seatN >= start && seatN <= end) {

                    if (seats[seatN] == false) {
                        seats[seatN] = true;
                        System.out.println("-------------------------------------");
                        System.out.println("---------" + className + " Ticket----------");
                        System.out.println("-------------------------------------");
                        System.out.println("----------------Seat " + seatN + "---------------");
                        System.out.println("------------18:00 flight-------------");
                        System.out.println("-------------------------------------");

                        Scanner obj3 = new Scanner(System.in);
                        System.out.println("Would you like to book another seat?: ");
                        String continueC = obj3.nextLine();
                        if (!continueC.equals("yes")) {
                            System.exit(0);
                            break;
                        }
                    } else {
                        System.out.println("that seat is already taken please select another seat ");
                    }

                } else {
                    System.out.println("a seat number in " + className + " has not been entered please try again");
                }

            } else {
                classCHECK(seats, className, otherClass, start, end);

            }

        }
        return seats;
    }

    public static boolean classSeatCheck(boolean seats[], int start, int end) {

        boolean isFull = true;

        for (int i = start; i <= end; i++) { // 0 , 1, 2, 3, 4
            if (seats[i] == false) {
                isFull = false;
                System.out.println(i);
            }
        }
        return isFull;
    }

    public static boolean wholePlaneCheck(boolean[] seats) {
        boolean fullyBooked = true;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == false) {
                fullyBooked = false;

            }
        }
        return fullyBooked;
    }

    public static boolean changeClass(String classC, String otherClass) {
        while (true) {
            Scanner obj1 = new Scanner(System.in);
            System.out.println("Sorry that all of " + classC + " seats were taken would you like to book into " + otherClass + "? ");
            String classV = obj1.nextLine();
            return classV.equals("yes") || classV.equals("y");
        }
    }

    public static void classCHECK(boolean[] seats, String className, String otherClass, int start, int end) {
        if (!wholePlaneCheck(seats)) {
            if (!classSeatCheck(seats, start, end)) {

                seatCheck(seats, start, end, className, otherClass);
                
            } else {
                System.out.println(className + " class is full would you like to book a " + otherClass + " ticket?");
                if (changeClass(className, otherClass)) {
                    if (className.equals("First Class")) {
                        classCHECK(seats, otherClass, className, 5, 9);
                    } else {
                        classCHECK(seats, otherClass, className, 0, 4);
                    }
                } else {
                    System.out.println("the next flight is in 3 hours");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("sorry the whole plane is booked please wait for the next flight");
            System.exit(0);
        }

    }

}