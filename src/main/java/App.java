import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //homepage
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //not sure how we want to navigate between homepage and add location
    //add location
    get("/locations/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/location-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location newLocation = new Location(request.queryParams("neighborhood"));
      newLocation.save();
      model.put("location", newLocation);
      model.put("template", "templates/location-success.vtl");  //maybe redirect to page with all locations or homepage?
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //view all locations
    get("/locations", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("locations", Location.all());
      model.put("template", "templates/locations.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //view location
    get("/locations/:location_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location location = Location.find(Integer.parseInt(request.params(":location_id")));
      model.put("location", location);
      model.put("template", "templates/location.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //add dog park
    get("/locations/:location_id/dogparks/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location location = Location.find(Integer.parseInt(request.params(":location_id")));
      model.put("location", location);
      model.put("template", "templates/dogpark-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/dogparks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Location location = Location.find(Integer.parseInt(request.queryParams("location_id")));
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String notes = request.queryParams("notes");
      DogPark newDogPark = new DogPark(name, address, notes, location.getId());
      newDogPark.save();
      // model.put("dogPark", newDogPark);
      model.put("location", location);
      model.put("template", "templates/dogpark-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/locations/:location_id/dogparks/:dogparks_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      DogPark newDogPark = DogPark.find(Integer.parseInt(request.params(":dogparks_id")));
      model.put("dogPark", newDogPark);
      model.put("template", "templates/dogpark.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/dogparks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("locations", Location.all());
      model.put("dogparks", DogPark.all());
      model.put("template", "templates/dogparks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/locations/:location_id/dogparks/:dogparks_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      DogPark newDogPark = DogPark.find(Integer.parseInt(request.params(":dogparks_id")));
      Location location = Location.find(newDogPark.getLocationId());
      newDogPark.delete();
      model.put("dogPark", newDogPark);
      model.put("location", location);
      model.put("template", "templates/location.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
