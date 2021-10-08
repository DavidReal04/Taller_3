package co.edu.unbosque.taller_3;

public class MetaData {
    private String pet;
    private String correo;
    private String fecha;
    private String img;

    //Metadata DAO
    public MetaData(String pet, String correo, String fecha, String img) {
        this.pet = pet;
        this.correo = correo;
        this.fecha = fecha;
        this.img = img;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
