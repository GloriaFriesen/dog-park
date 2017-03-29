import org.sql2o.*;

public class DogPark {
  private String name;
  private String address;
  private String notes;
  private int locationId;

  public DogPark(String name, String address, String notes, int locationId) {
    this.name = name;
    this.address = address;
    this.notes = notes;
    this.locationId = locationId;
  }

}
