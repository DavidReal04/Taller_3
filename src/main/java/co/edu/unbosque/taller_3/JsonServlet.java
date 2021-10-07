package co.edu.unbosque.taller_3;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "jsonServlet", value = "/jsonServlet")
public class JsonServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Specifying the content type for the response
        response.setContentType("application/json");

        // Dynamically building the data to be returned in json format
        ManageFiles file = new ManageFiles();
        String uploadPath = getServletContext().getRealPath("/DBfiles/MetaBD");
        file.setArchivodata(uploadPath);
        file.uploadData_meta();
        ArrayList meta = file.getMeta();

        log(meta.toString());

        // Adding the data to response, parsing it to json using Gson library
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(file.getMeta()));

    }

}