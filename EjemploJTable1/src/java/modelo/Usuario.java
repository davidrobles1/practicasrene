package modelo;

public class Usuario {

    int IdUsuario;
    String Usuario;
    int edad;
    String correo ;
    String telefono;
    String apellidos;
    public Usuario() {
    } 

    public Usuario(int Id, String nombre, int edad, String correo, String telefono, String apellidos) {
        this.IdUsuario = Id;
        this.Usuario = nombre;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.apellidos = apellidos;
    }
     public Usuario( String nombre, int edad, String correo, String telefono, String apellidos) {
        this.Usuario = nombre;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.apellidos = apellidos;
   }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    @Override
    public String toString() {
        return "PeopleTableContainer{" + "idusuario    =" + IdUsuario + ", nombre=" + Usuario + ", edad=" + edad + ", correo =" + correo +"telefono=" + telefono + ", apellidos="+ apellidos+ '}';
    }

}
