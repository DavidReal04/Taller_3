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

    public void init() {
        database = new ManageFiles();
        i =0;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

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


        String pet= request.getParameter("petname");
        Cookie[] cookies = request.getCookies();
        String correo ="";
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Email")) {
                     correo = cookie.getValue();

                }
            }
        }

        String fecha= new Date()+"";
        i++;
        String archivo = fileName;
        database.getMeta().add(new MetaData(pet,correo,fecha,archivo));
        String uploadPath2 = getServletContext().getRealPath("/DBfiles/MetaBD");
        database.setArchivodata(uploadPath2);
        database.escribirArchivo_meta();

        response.sendRedirect(request.getContextPath() + "/propietario.html");


    }

    public void destroy() {
    }
}
