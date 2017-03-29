import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
//
// public class App {
//   public static void main(String[] args) {
//     staticFileLocation("/public");
//     String layout = "templates/layout.vtl";
//
//     //homepage
//     get("/", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       model.put("template", "templates/index.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     //not sure how we want to navigate between homepage and add location
//     //add location
//     get("locations/new", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       model.put("template", "templates/location-form.vtl");
//     }, new VelocityTemplateEngine());
//
//     post("/locations", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       Location newLocation = new Location(request.queryParams("neighborhood"));
//       newLocation.save();
//       model.put("template", "templates/location-success.vtl");  //maybe redirect to page with all locations or homepage?
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     //view all locations
//     get("/locations", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       model.put("locations", Location.all());
//       model.put("template", "templates/locations.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     //view location
//     get("/locations/:location_id", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//       Location location = Location.find(Integer.parseInt(request.params(":location_id")));
//       model.put("location", location);
//       model.put("template", "template/location.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     //add dog park
//     get("/locations/:location_id/dogparks/new", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("/locations/:location_id/dogparks/:dogpark_id", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();
//
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//   }
// }
