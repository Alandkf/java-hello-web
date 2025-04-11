import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Version;

public class App {
    public static void main(String[] args) {
        // Serve static files from /public
        staticFiles.location("/public");

        // Create a custom FreeMarker configuration that loads templates from /templates
        Configuration freeMarkerConfig = new Configuration(new Version(2, 3, 31));
        freeMarkerConfig.setClassForTemplateLoading(App.class, "/templates");

        // Log details about the template loader
        System.out.println("Template loader: " + freeMarkerConfig.getTemplateLoader());

        // Create a FreeMarkerEngine with the custom configuration
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(freeMarkerConfig);

        // GET handler for /hello redirecting to static html file
        get("/hello", (req, res) -> {
            System.out.println("Received GET request for /hello");
            res.redirect("/hello.html");
            return null;
        });

        // Handle form submission and render greeting page
        post("/greet", (req, res) -> {
            System.out.println("Received form submission");
            String name = req.queryParams("name");
            System.out.println("Name: " + name);

            Map<String, Object> model = new HashMap<>();
            model.put("name", name);
            System.out.println("Model: " + model);

            // Check if the template exists by using the FreeMarker template loader
            try {
                Object templateSource = freeMarkerConfig.getTemplateLoader().findTemplateSource("greeting.ftl");
                if (templateSource != null) {
                    System.out.println("Template greeting.ftl found.");
                } else {
                    System.out.println("Template greeting.ftl not found. Please check your /templates folder.");
                }
            } catch (Exception e) {
                System.err.println("Error accessing template source: " + e.getMessage());
            }

            // Render the greeting.ftl template using the model data
            try {
                return freeMarkerEngine.render(new ModelAndView(model, "greeting.ftl"));
            } catch (Exception e) {
                System.err.println("Error rendering template: " + e.getMessage());
                e.printStackTrace();
                res.status(500);
                return "Internal Server Error";
            }
        });
    }
}
