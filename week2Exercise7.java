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
        
        System.out.println("..........Welcome to British Airways..........");
        
        if (!wholePlaneCheck(seats)){
        
        while (true) {
            Scanner obj1 = new Scanner(System.in);
            System.out.println("Which class would you like to book?: ");
            String classV = obj1.nextLine();
            
            System.out.println(classV);
            
            if (classV.equals("Econemy")) {
                
                classCHECK(seats, "Econemy", "First Class", 1, 5);
            } else if (classV.equals("First Class")) {
                
                classCHECK(seats, "First Class", "Econemy", 6, 10);
            } else {
                System.out.println("you have entered an invalid class enter [First Class or Econemy]");
            }
        }
        }else{
            System.out.println("sorry that flight is fully booked the nxt flight is in 3 minutes");
        }

        // 1 array 10 seats / 1-5 ecoon , 6-10 first class / print out name , type of seat , seat number/
        // array set to all false = empty / if take set to true / - check if is taken(give list of empty in that class) / if all in econemy or first is full ask if they want other 
        //if all plane is full say next flight in 3 mins
        //functions needed - check if seat is avaible (a seat check for all seats )
        // - check if all seats in that class are taken
    }
    
    public static boolean[] seatCheck(boolean seats[], int start, int end) {
        while (true) {
            Scanner obj2 = new Scanner(System.in);
            System.out.println("Which seat would you like to book?(): ");
            String seatV = obj2.nextLine();
            
            Integer seatN = Integer.parseInt(seatV);
            
            if (seatN >= start && seatN <= end) {
                
                if (seats[seatN] == false) {
                    seats[seatN] = true;
                    System.out.println("seat booked succes fully"); //later have to add big thing -================================================= 8----3 --------
                    System.exit(0);
                    break;
                    
                } else {
                    System.out.println("that seat is already taken please select another seat ");
                }
                
            } else {
                System.out.println("a seat number in ecomeny has not been entered please try again");
            }
            
        }
        
        return seats;
    }
    
    public static boolean classSeatCheck(boolean seats[], int start, int end) {
        
        boolean isFull = false;
        
        for (int i = start; i < end; i++) {
            if (seats[i] == true) {
                isFull = true;
            }
        }
        return isFull;
    }
    
    public static boolean changeClass(String classC, String otherClass) {
        while (true) {
            Scanner obj1 = new Scanner(System.in);
            System.out.println("Sorry that all of " + classC + "seats were taken would you like to book into " + otherClass + "? ");
            String classV = obj1.nextLine();
            if (classV.equals("yes") || classV.equals("y")) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static void classCHECK(boolean[] seats, String className, String otherClass, int start, int end) {
        if (classSeatCheck(seats, start, end) == false) {
            
            seatCheck(seats, start, end);
        } else {
            System.out.println(className + " class is full would you like to book a " + otherClass + " ticket?");
            if (changeClass(className, otherClass)) {
                if (className.equals("First Class")) {
                    classCHECK(seats, otherClass, className, 6, 10);
                } else {
                    classCHECK(seats, otherClass, className, 1, 5);
                }
            } else {
                System.out.println("the next flight is in 3 minutes");
            }
        }
    }
    
    public static boolean wholePlaneCheck(boolean[] seats){
        boolean fullyBooked = true;
        
        for (int i = 0; i<seats.length; i++){
            if (seats[i] == false){
                fullyBooked = false;
                
            }
        }return fullyBooked;
    }
    
}
