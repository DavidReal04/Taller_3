package co.edu.unbosque.taller_3;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "validationServlet", value = "/validation-servlet")
public class ValidationServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Ingreso exitoso";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}