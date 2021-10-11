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
    private ArrayList<MetaData> meta;
    private CSVReader csvReader;
    private FileReader csvFile;
    private String archivodata;

    //object initialization
    public ManageFiles() {
        usuarios = new ArrayList<>();
        meta = new ArrayList<>();
        archivodata = "";
    }

    //reading csv file
    public boolean uploadData_users() {
        try {
            //open csv file and declare separator
            csvFile = new FileReader(archivodata);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
            String[] fila;
            csvReader.readNext();
            //adding objects of the csv to usuarios arraylist
            while ((fila = csvReader.readNext()) != null) {
                usuarios.add(new Usuarios(fila[0],fila[1],fila[2]));
            }
        }catch (IOException | CsvValidationException e) {
            return false;
        }
        return true;
    }

    //reading csv file
    public ArrayList uploadData_meta() {
        try {
            //open csv file and declare separator
            csvFile = new FileReader(archivodata);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
            String[] fila;
            //adding objects of the csv to usuarios arraylist
            while ((fila = csvReader.readNext()) != null) {
                meta.add(new MetaData(fila[0],fila[1],fila[2],fila[3]));
            }
        }catch (IOException | CsvValidationException e) {
            return null;
        }
        return meta;
    }

    //overwriting csv file
    public String escribirArchivo_meta() {
        //open csv file
        File f = new File(this.archivodata);
        try {
            //overwriting
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            String datosString = "";
            for (MetaData meta : meta) {
                String pet = meta.getPet();
                String correo = meta.getCorreo();
                String fecha = meta.getFecha();
                String img = meta.getImg();
                datosString = pet+";"+correo + ";" + fecha + ";" + img;

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

    public ArrayList<MetaData> getMeta() {
        return meta;
    }

    public void setMeta(ArrayList<MetaData> meta) {
        this.meta = meta;
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
