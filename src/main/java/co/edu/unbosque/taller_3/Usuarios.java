package co.edu.unbosque.taller_3;

public class Usuarios {
    private String nombre;
    private String correo;
    private String funcion;

    public Usuarios(String nombre,String correo,String funcion) {
        this.nombre=nombre;
        this.correo=correo;
        this.funcion=funcion;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
}
