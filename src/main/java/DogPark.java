import org.sql2o.*;
import java.util.List;

public class DogPark {
  private String name;
  private String address;
  private String notes;
  private int location_id;
  private int id;

  public DogPark(String name, String address, String notes, int location_id) {
    this.name = name;
    this.address = address;
    this.notes = notes;
    this.location_id = location_id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getNotes() {
    return notes;
  }

  public int getLocationId() {
    return location_id;
  }

  public int getId() {
    return id;
  }

  public static List<DogPark> all() {
    String sql = "SELECT id, name, address, notes, location_id FROM parks";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(DogPark.class);
    }
  }

  @Override
  public boolean equals(Object otherDogPark){
    if (!(otherDogPark instanceof DogPark)) {
      return false;
    } else {
      DogPark newDogPark = (DogPark) otherDogPark;
      return
      this.getName().equals(newDogPark.getName()) &&     this.getAddress().equals(newDogPark.getAddress()) && this.getNotes().equals(newDogPark.getNotes()) &&
      this.getLocationId() == newDogPark.getLocationId() &&
      this.getId() == newDogPark.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO parks (name, address, notes, location_id) VALUES (:name, :address, :notes, :location_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("address", this.address)
      .addParameter("notes", this.notes)
      .addParameter("location_id", this.location_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static DogPark find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM parks WHERE id=:id";
      DogPark dogPark = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(DogPark.class);
      return dogPark;
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE parks SET name=:name WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateAddress(String address) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE parks SET address=:address WHERE id=:id";
      con.createQuery(sql)
        .addParameter("address", address)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void updateNotes(String notes) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE parks SET notes=:notes WHERE id=:id";
      con.createQuery(sql)
        .addParameter("notes", notes)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
