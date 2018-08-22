import java.util.NoSuchElementException;

public class Hotel {
  private String name;
  private Room[] rooms;
  private Reservation[] reservations;
  
  // hotel constructor
  public Hotel(String name, Room[] rooms) {
    this.name = name;
    this.rooms = new Room[rooms.length];
    for (int i = 0; i < rooms.length; i++) {
      this.rooms[i] = rooms[i]; 
    }
  }
  
  // adds the reservation to the reservation array of the hotel
  private void addReservation(Reservation newReservation) {
    Reservation[] newArray;
    if (this.reservations == null) {
      newArray = new Reservation[1];
    } else {
      newArray = new Reservation[reservations.length + 1];
    }
    for (int i = 0; i < newArray.length-1; i++) {
      newArray[i] = reservations[i]; 
    }
    newArray[newArray.length - 1] = newReservation;
    this.reservations = newArray;
  }
  
  
  // removes the reservation from the reservation array of the hotel
  private void removeReservation(String name, String type) {
    if (!(type).equals("double") && !(type).equals("queen") && !(type).equals("king")) {
      throw new IllegalArgumentException("That is not a valid room type!");
    }
    if (this.reservations == null) {
      throw new NoSuchElementException("There's no reservation for a " + type + " room under the name of " + name + ".");
    } 
    Reservation[] newReservations = new Reservation[this.reservations.length-1];
    // finding the reservation to remove
    int index = -1;
    for (int i = 0; i < this.reservations.length; i++) {
      if ((reservations[i].getName()).equals(name)) {
        Room reserved = reservations[i].getRoom();
        if ((reserved.getType()).equals(type)) {
          Reservation remove = reservations[i];
          index = i;
        }
        reserved.changeAvailability();
      }
    }
    if (index == -1) {
      throw new NoSuchElementException("There's no reservation for a " + type + " room under the name of " + name + "."); 
    } else {
      for (int i = 0; i < newReservations.length; i++) {
        if (i < index) {
          newReservations[i] = this.reservations[i];
        } else {
          newReservations[i] = this.reservations[i+1];
        }
      }
      this.reservations = newReservations;
    }
  }
  
// creates a new reservation of a given type for a given name
  public void createReservation(String name, String type) {
    try {
      if (Room.findAvailableRoom(this.rooms, type) == null) {
        System.out.println("Sorry " + name + ", we have no available rooms of the desired type.");
      } else {
        Room availableRoom = Room.findAvailableRoom(this.rooms, type);
        Reservation newReservation = new Reservation(availableRoom, name);
        addReservation(newReservation); 
        availableRoom.changeAvailability();
        System.out.println("You have successfully reserved a " + type + " room under the name of " + name + ". We look forward to having you at " + this.name +"!");
      }
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
  
// cancels the first reservation in the array for the given name and type
  public void cancelReservation(String name, String type) {
    try {
      removeReservation(name, type);
      System.out.println(name + ", your reservation for a " + type + " room has been successfully cancelled.");
    } catch (NoSuchElementException e) {
      System.err.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
  
// prints the total cost of all reservations under a given name
  public void printInvoice(String name) {
    double cost = 0.0;
    if (this.reservations != null) {
      for (int i = 0; i < reservations.length; i++) {
        if ((reservations[i].getName()).equals(name)) {
          Room reserved = reservations[i].getRoom();
          double price = reserved.getPrice();
          cost = cost + price;
        }
      }
    }
    System.out.println("The invoice for " + name + " is of $" + cost);
  }
  
// a toString method for printing the hotel's information
  public String toString() {
    String first = ("Here is the hotel info \n");
    String name = ("Hotel name: " + this.name + '\n');
    // finding the number of available rooms
    int availableDoubleRooms = 0;
    int availableQueenRooms = 0;
    int availableKingRooms = 0;
    for (int i = 0; i < rooms.length; i++) {
      if (rooms[i].getAvailability()) {
        String type = rooms[i].getType();
        if (type == "double") {
          availableDoubleRooms++;
        } else if (type == "queen") {
          availableQueenRooms++;
        } else {
          availableKingRooms++;
        }
      }
    }
    String rooms = ("Available Rooms: " + availableDoubleRooms + " double, " + availableQueenRooms + " queen, " + availableKingRooms + " king.");
    return first + name + rooms;
  }
}

