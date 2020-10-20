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

    // main function
    public static void main(String[] args) {

        //create an array of length 10
        boolean[] seats = new boolean[10];

        //loop through the array and set all values to false (meaning not taken)
        for (int i = 0; i < seats.length; i++) {
            seats[i] = false;
        }

        //view seats (for error detectioon)
        System.out.println(Arrays.toString(seats));

        //welcome message
        System.out.println("..........Welcome to British Airways..........");

        //while the whole plane isnt reserved
        while (!wholePlaneCheck(seats)) {

            //a constant while loop
            while (true) {

                //input - what class they would like to book into
                Scanner obj1 = new Scanner(System.in);
                System.out.println("Which class would you like to book?: ");
                String classV = obj1.nextLine();
                
                //switch class to do what is needed for each class
                switch (classV) {
                    case "Econemy" ->
                        classCHECK(seats, "Econemy", "First Class", 0, 4); // class Econemy is seats 1 - 5 
                    case "First Class" ->
                        classCHECK(seats, "First Class", "Econemy", 5, 9); // class First Class is seats 6 - 10 
                    default -> //error detection for invalid entry
                        System.out.println("you have entered an invalid class enter [First Class or Econemy]");

                }

            }
        }
    }

    // 1 array 10 seats / 1-5 ecoon , 6-10 first class / print out name , type of seat , seat number/
    // array set to all false = empty / if take set to true / - check if is taken(give list of empty in that class) / if all in econemy or first is full ask if they want other 
    //if all plane is full say next flight in 3 mins
    //functions needed - check if seat is avaible (a seat check for all seats )
    // - check if all seats in that class are taken
    
    //function to check if a seat is taken - and if so book ticket 
    public static boolean[] seatCheck(boolean seats[], int start, int end, String className, String otherClass) {
        
        //constant while loop
        while (true) {
            //if the class is not fully booked
            if (!classSeatCheck(seats, start, end)) {
                //input what seat they would like to book
                Scanner obj2 = new Scanner(System.in);
                System.out.println("Which seat would you like to book?(): ");
                String seatV = obj2.nextLine();
                
                //parse the input into an int
                Integer seatN = (Integer.parseInt(seatV) - 1);
                
                //output for debugging
                System.out.println(Arrays.toString(seats));
                
                //error detection to check number entered is in the valid options
                if (seatN >= start && seatN <= end) {
                    
                    //if seat isnt taken
                    if (seats[seatN] == false) {
                        //set seat to be taken
                        seats[seatN] = true;
                        //output function to show ticket
                        System.out.println("-------------------------------------");
                        System.out.println("---------" + className + " Ticket----------");
                        System.out.println("-------------------------------------");
                        System.out.println("----------------Seat " + seatN + "---------------");
                        System.out.println("------------18:00 flight-------------");
                        System.out.println("-------------------------------------");
                        
                        //input if they would like to book another ticket?
                        Scanner obj3 = new Scanner(System.in);
                        System.out.println("Would you like to book another seat?: ");
                        String continueC = obj3.nextLine();
                        
                        //if anything other than yes - end program
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
                //loop the code and start booking another ticket
                classCHECK(seats, className, otherClass, start, end);

            }

        }
        //return updated seats array
        return seats;
    }

    
    //checked if a full class is booked
    public static boolean classSeatCheck(boolean seats[], int start, int end) {

        boolean isFull = true;

        for (int i = start; i <= end; i++) { // 0 , 1, 2, 3, 4
            if (seats[i] == false) {
                isFull = false;
                System.out.println(i);
            }
        }
        //returns boolean value if full or not
        return isFull;
    }
    
    //checks if the whole plane is booked
    public static boolean wholePlaneCheck(boolean[] seats) {
        boolean fullyBooked = true;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == false) {
                fullyBooked = false;

            }
        }
       //returns boolean value if full or not
        return fullyBooked;
    }
    
    
    //fucntion to ask whether they want to change class
    public static boolean changeClass(String classC, String otherClass) {

        Scanner obj1 = new Scanner(System.in);
        System.out.println("Sorry that all of " + classC + " seats were taken would you like to book into " + otherClass + "? ");
        String classV = obj1.nextLine();
        //returns a boolean if they want to or not
        return classV.equals("yes") || classV.equals("y");

    }
    
    //main function loop for booking
    public static void classCHECK(boolean[] seats, String className, String otherClass, int start, int end) {
        //while whole plane isnt fully booked
        if (!wholePlaneCheck(seats)) {
            //while whole class isnt fully booked
            if (!classSeatCheck(seats, start, end)) {
                
                //check if seat is taken if not book seat
                seatCheck(seats, start, end, className, otherClass);

            } else {
                //if class full try change class 
                if (changeClass(className, otherClass)) {
                    if (className.equals("First Class")) {
                        // wanna change class and used to be in first class rerun fucntion with switch varibles
                        classCHECK(seats, otherClass, className, 5, 9);
                    } else {
                        // wanna change class and used to be in Econemy rerun fucntion with switch varibles
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
