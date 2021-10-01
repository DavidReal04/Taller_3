package co.edu.unbosque.taller_3;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "validationServlet", value = "/validation-servlet")
public class ValidationServlet extends HttpServlet {
    private String message;
    private String UPLOAD_DIRECTORY = "DBfiles";

    public void init() {
        message = "Ingreso exitoso";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        String rol="";
        if(rol.equals("propietario")){
            response.sendRedirect(request.getContextPath() + "/propietario.html");
        }else if(rol.equals("funcionario")){
            response.sendRedirect(request.getContextPath() + "/admin.html");
        }
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message+ "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}