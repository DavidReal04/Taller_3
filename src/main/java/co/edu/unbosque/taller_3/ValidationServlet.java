package co.edu.unbosque.taller_3;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "validationServlet", value = "/validation-servlet")
public class ValidationServlet extends HttpServlet {

    private ManageFiles database;

    public void init() {
        database = new ManageFiles();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        //Login submitted by the user
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        //Get DataBase Path & set path on ManageFiles
        String uploadPath = getServletContext().getRealPath("/DBfiles/UserDB");
        database.setArchivodata(uploadPath);
        database.uploadData();
        ArrayList<Usuarios> users = database.getUsuarios();

        //Validate Login with DataBase info
        for (Usuarios user : users) {
            if (email.equals(user.getEmail()) && pass.equals(user.getPassword())) {
                String rol = user.getFuncion();
                if (rol.equals("propietario")) {
                    //Set login cookies & redirect
                    Cookie emailCookie = new Cookie("Email", email);
                    emailCookie.setMaxAge(3600);
                    Cookie rolCookie = new Cookie("Rol", rol);
                    rolCookie.setMaxAge(3600);
                    response.addCookie(emailCookie);
                    response.addCookie(rolCookie);
                    response.sendRedirect(request.getContextPath() + "/propietario.html");
                } else if (rol.equals("funcionario")) {
                    //Set login cookies & redirect
                    Cookie emailCookie = new Cookie("Email", email);
                    emailCookie.setMaxAge(3600);
                    Cookie rolCookie = new Cookie("Rol", rol);
                    rolCookie.setMaxAge(3600);
                    response.addCookie(emailCookie);
                    response.addCookie(rolCookie);
                    response.sendRedirect(request.getContextPath() + "/funcionario.html");
                }
            }
        }
    }

    public void destroy() {
    }
}