import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

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

}
