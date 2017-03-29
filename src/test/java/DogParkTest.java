import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class DogParkTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dog_park_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String deleteLocationsQuery = "DELETE FROM locations *;";
      String deleteDogParkQuery = "DELETE FROM parks *";
      con.createQuery(deleteLocationsQuery).executeUpdate();
      con.createQuery(deleteDogParkQuery).executeUpdate();
    }
  }

  @Test
  public void DogPark_instantiatesCorrectly_true() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    assertEquals(true, newDogPark instanceof DogPark);
  }

  @Test
  public void getName_getsNameValue_name() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    assertEquals("Wallace", newDogPark.getName());
  }

  @Test
  public void getAddress_getsAddressValue_address() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    assertEquals("1600 NW 25th Ave", newDogPark.getAddress());
  }

  @Test
  public void getNotes_getsNotesValue_notes() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    assertEquals("cool park", newDogPark.getNotes());
  }

  @Test
  public void getId_returnsInstanceOfId_1() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    assertTrue(newDogPark.getId() > 0);
  }

  @Test
  public void all_getsAllDogParks_true() {
    DogPark firstDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    firstDogPark.save();
    DogPark secondDogPark = new DogPark("Overlook", "1599 N Fremont St", "best park", 2);
    secondDogPark.save();
    assertEquals(true, DogPark.all().get(0).equals(firstDogPark));
    assertEquals(true, DogPark.all().get(1).equals(secondDogPark));
  }

  @Test
  public void equals_returnsTrueifNameIsTheSame() {
    DogPark firstDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    DogPark secondDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    assertTrue(firstDogPark.equals(secondDogPark));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    assertTrue(DogPark.all().get(0).equals(newDogPark));
  }

  @Test
  public void save_assignsIdToDogPark() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    DogPark savedDogPark = DogPark.all().get(0);
    assertEquals(newDogPark.getId(), savedDogPark.getId());
  }

  @Test
  public void find_returnsDogParkWithSameId_secondDogPark() {
    DogPark firstDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    firstDogPark.save();
    DogPark secondDogPark = new DogPark("Overlook", "1599 N Fremont St", "best park", 2);
    secondDogPark.save();
    assertEquals(DogPark.find(secondDogPark.getId()), secondDogPark);
  }

  @Test
  public void updateName_updatesDogPark_true() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    newDogPark.updateName("Couch");
    assertEquals("Couch", newDogPark.find(newDogPark.getId()).getName());
  }

  @Test
  public void updateAddress_updatesDogPark_true() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    newDogPark.updateAddress("3417 NE 7th Ave");
    assertEquals("3417 NE 7th Ave", newDogPark.find(newDogPark.getId()).getAddress());
  }

  @Test
  public void updateNotes_updatesDogPark_true() {
    DogPark newDogPark = new DogPark("Wallace", "1600 NW 25th Ave", "cool park", 1);
    newDogPark.save();
    newDogPark.updateNotes("not a cool park");
    assertEquals("not a cool park", newDogPark.find(newDogPark.getId()).getNotes());
  }
}
