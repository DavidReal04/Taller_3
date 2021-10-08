package co.edu.unbosque.taller_3;

public class Usuarios {
    private String email;
    private String password;
    private String funcion;

    //Users DAO
    public Usuarios(String email, String password, String funcion) {
        this.email = email;
        this.password = password;
        this.funcion = funcion;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
}
