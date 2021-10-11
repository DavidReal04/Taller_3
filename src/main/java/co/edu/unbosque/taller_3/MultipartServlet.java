package co.edu.unbosque.taller_3;

import java.io.*;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "multiPartServlet", value = "/multiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class MultipartServlet extends HttpServlet {
    private ManageFiles database;
    private String UPLOAD_DIRECTORY = "uploads";
    private int i;

    //object initialization
    public void init() {
        database = new ManageFiles();
        i =0;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specifying the content type for the response
        response.setContentType("text/html");

        // Getting the file from the propietarios.html and separating it into parts
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        //validating or creating folder
        if (!uploadDir.exists()) uploadDir.mkdir();

        //adding file to the folder
        String fileName = "";
        try {
            for (Part part : request.getParts()) {
                String firstname =part.getSubmittedFileName();

                fileName = fileName.hashCode()+"."+FilenameUtils.getExtension(firstname);
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Getting the info from the propietarios.html
        String pet= request.getParameter("petname");

        // Getting cookie from the browser
        Cookie[] cookies = request.getCookies();
        String correo ="";
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Email")) {
                     correo = cookie.getValue();

                }
            }
        }

        // Getting date from the browser
        String fecha= new Date()+"";
        i++;
        String archivo = fileName;
        //adding info to the meta database
        database.getMeta().add(new MetaData(pet,correo,fecha,archivo));
        String uploadPath2 = getServletContext().getRealPath("/DBfiles/MetaBD");
        database.setArchivodata(uploadPath2);
        database.escribirArchivo_meta();

        response.sendRedirect(request.getContextPath() + "/propietario.html");


    }

    public void destroy() {
    }
}
