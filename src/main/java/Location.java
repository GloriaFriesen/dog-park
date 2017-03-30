import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Location {
private String neighborhood;
private int id;

  public Location(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public int getId() {
    return id;
  }

  public static List<Location> all() {
    String sql = "SELECT id, neighborhood FROM locations";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Location.class);
    }
  }

  @Override
  public boolean equals(Object otherLocation){
    if (!(otherLocation instanceof Location)) {
      return false;
    } else {
      Location newLocation = (Location) otherLocation;
      return this.getNeighborhood().equals(newLocation.getNeighborhood()) && this.getId() == newLocation.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO locations (neighborhood) VALUES (:neighborhood)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("neighborhood", this.neighborhood)
      .executeUpdate()
      .getKey();
    }
  }

  public static Location find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id = :id";
      Location location = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
      return location;
    }
  }

  public List<DogPark> getDogParks() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM parks WHERE location_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(DogPark.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM locations WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }


}
