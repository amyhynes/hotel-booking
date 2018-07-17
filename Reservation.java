public class Reservation {
  private String name;
  private Room roomReserved;

  // reservation constructor
  public Reservation(Room roomReserved, String name) {
    this.roomReserved = roomReserved;
    this.name = name;
  }
  
  // returns the name of the person who made the reservation
  public String getName() {
    return this.name; 
  }
  
  // returns the room that has been reserved
  public Room getRoom() {
    return this.roomReserved; 
  }
}