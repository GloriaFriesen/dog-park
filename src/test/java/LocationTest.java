import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class LocationTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dog_park_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM locations *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Location_returnsInstanceOfLocation_true(){
    Location newLocation = new Location("Pearl");
    assertEquals(true, newLocation instanceof Location);
  }

  @Test
  public void getNeighborhood_returnsInstanceOfNeighborhood_pearl(){
    Location newLocation = new Location("Pearl");
    assertEquals("Pearl", newLocation.getNeighborhood());
  }

  @Test
  public void getId_returnsInstanceOfId_1(){
    Location newLocation = new Location("Pearl");
    newLocation.save();
    assertTrue(newLocation.getId() > 0);
  }

  @Test
  public void all_getsAllLocations_true() {
    Location firstLocation = new Location("Pearl");
    firstLocation.save();
    Location secondLocation = new Location("NW");
    secondLocation.save();
    assertEquals(true, Location.all().get(0).equals(firstLocation));
    assertEquals(true, Location.all().get(1).equals(secondLocation));
  }

  @Test
  public void equals_returnsTrueIfNeighborhoodIsTheSame() {
    Location firstLocation = new Location("Pearl");
    Location secondLocation = new Location("Pearl");
    assertTrue(firstLocation.equals(secondLocation));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Location newLocation = new Location("Pearl");
    newLocation.save();
    assertTrue(Location.all().get(0).equals(newLocation));
  }

  @Test
  public void save_assignsIdToLocation() {
    Location newLocation = new Location("Pearl");
    newLocation.save();
    Location savedLocation = Location.all().get(0);
    assertEquals(newLocation.getId(), savedLocation.getId());
  }

  @Test
  public void find_returnsLocationWithSameId_secondLocation() {
    Location firstLocation = new Location("Pearl");
    firstLocation.save();
    Location secondLocation = new Location("NW");
    secondLocation.save();
    assertEquals(Location.find(secondLocation.getId()), secondLocation);
  }



}
