import java.util.Scanner; 
import java.util.Random; 

public class BookingSystem { 
  
  private static String[] typeOfRooms = {"double","queen","king"}; 
  private static Random r = new Random(123); 
  
  //returns a random String from the above array.  
  private static String getRandomType(){ 
    int index = r.nextInt(typeOfRooms.length); 
    return typeOfRooms[index]; 
  } 
  //returns a random number of rooms between 5 and 50. 
  private static int getRandomNumberOfRooms(){ 
    return r.nextInt(50)+1; 
  } 
  //End of provided code.  
  
  public static void main(String[] args){ 
    //Student Name: Amy Hynes
    //Student Number: 260716296
    
    // initializing a string of rooms
    int numberOfRooms = getRandomNumberOfRooms();
    Room[] rooms = new Room[numberOfRooms];
    for (int i = 0; i < numberOfRooms; i++) {
      String type = getRandomType();
      Room r = new Room(type);
      rooms[i] = r;
    }
    
    // creating a hotel
    Scanner read = new Scanner(System.in);
    System.out.println("Welcome to the COMP 202 booking system");
    System.out.println("Please enter the name of the hotel you'd like to book");
    String hotelName = read.nextLine();
    Hotel newHotel = new Hotel(hotelName, rooms);
    
    // displaying the first main menu
    printMenu(hotelName);
    int optionSelect = read.nextInt();
    read.nextLine();
    
    while (optionSelect < 5) {
      if (optionSelect == 1) {
        System.out.print("Please enter your name:");
        String name = read.nextLine();
        System.out.print("What type of room would you like to reserve?");
        String type = read.nextLine();
        newHotel.createReservation(name, type);
        printMenu(hotelName);
        optionSelect = read.nextInt();
        read.nextLine();
      }
      if (optionSelect == 2) {
        System.out.print("Please enter the name you used to make the reservation:");
        String name = read.nextLine();
        System.out.print("What type of room did you reserve?");
        String type = read.nextLine();
        newHotel.cancelReservation(name, type);
        printMenu(hotelName);
        optionSelect = read.nextInt();
        read.nextLine();
      }
      if (optionSelect == 3) {
        System.out.print("Please your name:");
        String name = read.nextLine();
        newHotel.printInvoice(name);
        printMenu(hotelName);
        optionSelect = read.nextInt();
        read.nextLine();
      }
      if (optionSelect == 4) {
        System.out.println(newHotel);
        printMenu(hotelName);
        optionSelect = read.nextInt();
        read.nextLine();
      }
    }
    if (optionSelect == 5) {
      System.out.println("It was a pleasure doing business with you!");
    }
  } 
  
  // prints the main menu
  private static void printMenu(String hotelName) {
    System.out.println("******************************************************************************");
    System.out.println("Welcome to " + hotelName + ". Choose one of the following options:");
    System.out.println("1) Make a reservation");
    System.out.println("2) Cancel a reservation");
    System.out.println("3) See an invoice");
    System.out.println("4) See hotel info");
    System.out.println("5) Exit the booking system");
    System.out.println("******************************************************************************");
    System.out.println();
  }
}


