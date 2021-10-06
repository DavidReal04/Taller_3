package co.edu.unbosque.taller_3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class ManageFiles {
    private ArrayList<Usuarios> usuarios;
    private CSVReader csvReader;
    private FileReader csvFile;
    private String archivodata;

    public ManageFiles() {
        usuarios = new ArrayList<>();
        archivodata = "";
    }

    public boolean uploadData() {
        try {
            csvFile = new FileReader(archivodata);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
            String[] fila;
            csvReader.readNext();
            while ((fila = csvReader.readNext()) != null) {
                usuarios.add(new Usuarios(fila[0],fila[1],fila[2]));
            }
        }catch (IOException | CsvValidationException e) {
            return false;
        }
        return true;
    }

    public String escribirArchivo( ) {
        File f = new File(this.archivodata);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            String datosString = "";
            for (Usuarios usuario : usuarios) {
                String correo = usuario.getEmail();
                String password = usuario.getPassword();
                String funcion = usuario.getFuncion();
                datosString = correo + ";" + password + ";" + funcion;

                pw.println(datosString);
            }
            fw.close();
        } catch (IOException e) {
            return "Registro no exitoso";
        }
        return "Registro exitoso";
    }

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public CSVReader getCsvReader() {
        return csvReader;
    }

    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public FileReader getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(FileReader csvFile) {
        this.csvFile = csvFile;
    }

    public String getArchivodata() {
        return archivodata;
    }

    public void setArchivodata(String archivodata) {
        this.archivodata = archivodata;
    }
}
