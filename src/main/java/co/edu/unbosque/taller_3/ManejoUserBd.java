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

public class ManejoUserBd {
    private ArrayList<Usuarios> usuarios;
    private CSVReader csvReader;
    private FileReader csvFile;
    private String archivodata;

    public ManejoUserBd() {
        usuarios = new ArrayList<>();
        archivodata = "";
    }

    public boolean uploadData() {
        try {
            csvFile = new FileReader(archivodata);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
            String[] fila = null;
            csvReader.readNext();
            while ((fila = csvReader.readNext()) != null) {
                usuarios.add(new Usuarios(fila[0],fila[1],fila[2]));
            }
        }catch (IOException e) {
            return false;
        } catch (CsvValidationException e) {

        }
        return true;
    }

    public String escribirArchivo() {
        File f = new File(this.archivodata);
        try {
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            String datosString="./UserDB";
            for(int i = 0;i<usuarios.size();i++) {
                String nombre = usuarios.get(i).getNombre();
                String correo = usuarios.get(i).getCorreo();
                String funcion = usuarios.get(i).getFuncion();
                datosString = nombre+";"+correo+";"+funcion;

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
