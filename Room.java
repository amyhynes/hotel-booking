public class Room {
  private String type;
  private double price;
  private boolean availability;
  
  // room constructor
  public Room(String type) {
    if (type != "double" && type != "queen" && type != "king") {
      throw new IllegalArgumentException("That is not a valid room type!");
    } else {
      this.type = type;
      this.availability = true;
      if (type == "double") {
        this.price = 90;
      } else if (type == "queen") {
        this.price = 110;
      } else if (type == "king") {
        this.price = 150;
      }
    }
  }
  
  // returns the type of the room
  public String getType() {
    return type; 
  }
  
  // returns the price of the room
  public double getPrice() {
    return price; 
  }
  
  // returns the availability of the room
  public boolean getAvailability() {
    return availability; 
  }
  
  // changes the availability of the room
  public void changeAvailability() {
    boolean newAvailability = !this.availability;
    this.availability = newAvailability;
  }
  
  // finds the first available room in a given array
  public static Room findAvailableRoom(Room[] rooms, String type) {
    if (!(type).equals("double") && !(type).equals("queen") && !(type).equals("king")) {
      throw new IllegalArgumentException("That is not a valid room type!");
    }
    for (int i = 0; i < rooms.length; i++) {
      if(rooms[i].getAvailability() && (rooms[i].getType()).equals(type)) {
        return rooms[i]; 
      }
    }
    return null;
  }
}

