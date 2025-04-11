import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFiles.location("/public");  // Serve static files

        // GET /hello: Show the HTML page
        get("/hello", (req, res) -> {
            System.out.println("Redirecting to /hello.html");
            res.redirect("/hello.html");
            res.status(302); // Set HTTP status code for redirection
            return null; // No content to return
        });

        // POST /greet: Handle form submission
        post("/greet", (req, res) -> {
            String name = req.queryParams("name"); // Read input from form
            System.out.println("Received name: " + name); // Log the name to console
            res.type("text/html");
            return "<h1>Hello, " + name + "!</h1><p>Glad to have you here.</p>";
        });

        // GET /about: Show the about page
        get("/about", (req, res) -> {
            res.redirect("/about.html");
            return null; // No content to return
        });
    }
}
